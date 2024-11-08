package com.example.coffeshop.adatper

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeshop.databinding.ViewholderOfferBinding
import com.example.coffeshop.model.OfferModel

class OfferAdapter(private val items: MutableList<OfferModel>) :
    RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {
    private lateinit var context: Context

    inner class OfferViewHolder(val binding: ViewholderOfferBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        context = parent.context
        val binding =
            ViewholderOfferBinding.inflate(android.view.LayoutInflater.from(context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title
        Log.d("TAG", "onBindViewHolder: ${item.picUrl}")
        holder.binding.price.text = "$" + item.price
        Glide.with(context)
            .load(item.picUrl[0])
            .into(holder.binding.image)


    }

}