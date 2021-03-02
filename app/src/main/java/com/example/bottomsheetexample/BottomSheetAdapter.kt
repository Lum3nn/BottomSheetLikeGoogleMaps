package com.example.bottomsheetexample

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomsheetexample.databinding.BottomsheetItemBinding

class BottomSheetAdapter() :
    RecyclerView.Adapter<BottomSheetAdapter.BaseSheetViewHolder>() {

    var data: List<Base> = emptyList()
    var offset: Float = 0.0F

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSheetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = BottomsheetItemBinding.inflate(layoutInflater, parent, false)

        return BaseSheetViewHolder(itemView)
    }

    fun loadData(newData: List<Base>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseSheetViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class BaseSheetViewHolder(
        private val binding: BottomsheetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(baseObject: Base) {
            binding.imgItem.setImageResource(baseObject.id)
            binding.textField.text = baseObject.text
        }

        fun changeHeight(slideOffset: Float) {
            val imgLp = binding.imgItem.layoutParams as RelativeLayout.LayoutParams
            val pos = (slideOffset * 100).toInt()
            imgLp.height = pos.fromDpToPx().toInt()
            binding.imgItem.layoutParams = imgLp
        }
    }
}

fun Int.fromDpToPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}
