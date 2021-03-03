package com.example.bottomsheetexample

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomsheetexample.databinding.BottomsheetItemBinding

class BottomSheetAdapter() : RecyclerView.Adapter<BottomSheetAdapter.BottomSheetItemViewHolder>() {

    var data: List<Item> = emptyList()
    var offset: Float = 0.0F

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = BottomsheetItemBinding.inflate(layoutInflater, parent, false)

        return BottomSheetItemViewHolder(itemView)
    }

    fun loadData(newData: List<Item>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BottomSheetItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class BottomSheetItemViewHolder(
        private val binding: BottomsheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.imgItem.setImageResource(item.id)
            binding.textField.text = item.text
        }

        fun changeHeight(slideOffset: Float) {
            val imgLp = binding.imgItem.layoutParams as RelativeLayout.LayoutParams
            val imgHeight = 100
            val actualHeight = (slideOffset * imgHeight).toInt()
            imgLp.height = actualHeight.fromDpToPx().toInt()
            binding.imgItem.layoutParams = imgLp
        }
    }
}

fun Int.fromDpToPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}
