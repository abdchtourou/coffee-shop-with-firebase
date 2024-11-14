package com.example.coffeshop.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ViewholderSizeBinding

class SizeAdapter(val itmes: MutableList<String>) :
    RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    inner class SizeViewHolder(val binding: ViewholderSizeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        context = parent.context
        val binding =
            ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeViewHolder(binding)


    }

    override fun getItemCount(): Int = itmes.size

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }
        if (selectedPosition == position)
            holder.binding.img.setBackgroundResource(R.drawable.orange_bg)
        else
            holder.binding.img.setBackgroundResource(R.drawable.size_bg)
        val imageSize = when (position) {
            0 -> 45.dpToPx(context)

            1 -> 50.dpToPx(context)
            2 -> 55.dpToPx(context)
            3 -> 65.dpToPx(context)
            else -> 70.dpToPx(context)
        }
        val layoutParams = holder.binding.img.layoutParams
        layoutParams.height = imageSize
        layoutParams.width = imageSize
        holder.binding.img.layoutParams = layoutParams

    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}