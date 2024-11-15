package com.example.coffeshop.adatper

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.databinding.ViewholdreCartBinding
import com.example.coffeshop.helper.ChangeNumberItemsListener
import com.example.coffeshop.model.ItemsModel
import com.example.project1762.Helper.ManagementCart

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ViewholdreCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val managementCart = ManagementCart(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholdreCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return listItemSelected.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.apply {
            title.text = item.title
            feeEachItem.text = "$${item.price}"
            totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
            numberItemTxt.text = item.numberInCart.toString()
            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .apply(RequestOptions().transform(CenterCrop()))
                .into(holder.binding.picCart)

            plusCartBtn.setOnClickListener {
                managementCart.plusItem(
                    listItemSelected,
                    position,
                    object : ChangeNumberItemsListener {


                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener?.onChanged()
                        }
                    })

            }
            minusCartBtn.setOnClickListener {
                managementCart.minusItem(
                    listItemSelected,
                    position,
                    object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener?.onChanged()
                        }
                    })
                    }


        }


    }

}