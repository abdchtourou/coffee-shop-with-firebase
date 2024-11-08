package com.example.coffeshop.adatper

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.databinding.ViewholderPopularBinding
import com.example.coffeshop.model.ItemsModel

class ItemAdapter(private val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private lateinit var context: Context

    inner class ItemViewHolder(val binding: ViewholderPopularBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.binding.title.text = item.title
        holder.binding.description.text = item.description
        holder.binding.price.text = "$" + item.price
        holder.binding.ratingBar.rating = item.rating.toFloat()
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.pic)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()

        }


    }
}