package mx.aplazo.themeal.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.aplazo.themeal.R
import mx.aplazo.themeal.Tools.showImage
import mx.aplazo.themeal.data.model.MealFilter
import mx.aplazo.themeal.databinding.ItemRecipeFoodBinding

class RecipeFoodAdapter(
    private val mOnSelectListener: OnSelectFilterListener
) : RecyclerView.Adapter<RecipeFoodAdapter.RecipeFoodHolder>() {
    private val dataRec = mutableListOf<MealFilter>()

    fun setData(items: MutableList<MealFilter>) {
        dataRec.clear()
        dataRec.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeFoodHolder =
        RecipeFoodHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recipe_food, parent, false)
        )

    override fun getItemCount(): Int = dataRec.size

    override fun onBindViewHolder(holder: RecipeFoodHolder, position: Int) {
        val data = dataRec[position]
        with(holder) {
            itemView.context.showImage(data.strMealThumb, binding.imgThumb)
            binding.tvMeal.text = data.strMeal
            binding.cvFilterMeal.setOnClickListener {
                mOnSelectListener.onClickMeal(mealFilter = data)
            }
        }
    }

    inner class RecipeFoodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRecipeFoodBinding.bind(itemView)
    }

}

interface OnSelectFilterListener {
    fun onClickMeal(mealFilter: MealFilter)
}