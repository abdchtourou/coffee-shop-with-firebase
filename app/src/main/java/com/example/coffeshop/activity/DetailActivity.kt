package com.example.coffeshop.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffeshop.adatper.SizeAdapter
import com.example.coffeshop.databinding.ActivityDetailBinding
import com.example.coffeshop.model.ItemsModel
import com.example.project1762.Helper.ManagementCart

class DetailActivity : BaseActivity() {
    lateinit var binding: ActivityDetailBinding;
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managementCart = ManagementCart(this)
        bundle()
        initSizeList()


    }

    @SuppressLint("CheckResult")
    private fun initSizeList() {
        val SizeList = ArrayList<String>()
        SizeList.add("S")
        SizeList.add("M")
        SizeList.add("L")
        SizeList.add("XL")
        SizeList.add("XXL")
        binding.sizeList.adapter = SizeAdapter(SizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)

        }
        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(binding.picMain)


    }

    @SuppressLint("SetTextI18n")
    private fun bundle() {

        binding.apply {

            item = intent.getParcelableExtra("object")!!
            titleTxt.text = item.title
            descTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingBar.rating = item.rating.toFloat()
            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(numberItemTxt.text.toString())
                managementCart.insertItems(item)
            }
            backBtn.setOnClickListener {
                startActivity(
                    Intent(this@DetailActivity, MainActivity::class.java)
                )
            }
            plusCart.setOnClickListener {
                numberItemTxt.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }
            minusCart.setOnClickListener {
                if (item.numberInCart > 1) {
                    numberItemTxt.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }

            }
        }
    }
}