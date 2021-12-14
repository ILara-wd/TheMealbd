package mx.aplazo.themeal.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.aplazo.themeal.R
import mx.aplazo.themeal.utils.Tools.showImage
import mx.aplazo.themeal.data.model.MealDetail
import mx.aplazo.themeal.databinding.MealItemRandomBinding

class MealRecipeAdapter(
    private val mOnSelectListener: OnSelectListener
) : RecyclerView.Adapter<MealRecipeAdapter.ListViewHolder>() {
    private val dataRec = mutableListOf<MealDetail>()

    fun setData(items: MutableList<MealDetail>) {
        dataRec.clear()
        dataRec.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = MealItemRandomBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.meal_item_random, parent, false)
        )

    override fun getItemCount(): Int = dataRec.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataRec[position]
        with(holder) {
            binding.tvNameMeal.text = data.strMeal
            binding.tvArea.text = data.strArea
            binding.tvCategory.text = data.strCategory
            itemView.context.showImage(data.strMealThumb.orEmpty(), binding.ivMeal)
            binding.contentItem.setOnClickListener {
                mOnSelectListener.onClickMeal(mealDetail = data)
            }
        }
    }
}

interface OnSelectListener {
    fun onClickMeal(mealDetail: MealDetail)
}