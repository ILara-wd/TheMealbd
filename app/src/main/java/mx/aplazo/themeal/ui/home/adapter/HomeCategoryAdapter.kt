package mx.aplazo.themeal.ui.home.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.aplazo.themeal.R
import mx.aplazo.themeal.Tools.showImage
import mx.aplazo.themeal.data.model.Category
import mx.aplazo.themeal.databinding.ItemCategoriesBinding

class CategoryAdapter(
    private val mActivity: Activity,
    private val onCategorySelectListener: OnCategorySelectListener
) : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {
    private val dataCategories = mutableListOf<Category>()

    fun setData(items: MutableList<Category>) {
        dataCategories.clear()
        dataCategories.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCategoriesBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories, parent, false)
        )

    override fun getItemCount(): Int = dataCategories.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataCategories[position]
        with(holder) {
            binding.tvCategory.text = data.strCategory
            itemView.context.showImage(data.strCategoryThumb, binding.ivCategory)
            binding.contentItem.layoutParams.width = getSizeDisplay()
            binding.contentItem.setOnClickListener {
                onCategorySelectListener.onClickCategory(category = data)
            }
        }
    }

    private fun getSizeDisplay(): Int {
        val displayMetrics = DisplayMetrics()
        mActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels - 48
        return width / 2
    }
}

interface OnCategorySelectListener {
    fun onClickCategory(category: Category)
}