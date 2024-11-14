package com.example.coffeshop.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeshop.databinding.ActivityDetailBinding
import com.example.coffeshop.model.ItemsModel

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding;
    private lateinit var item: ItemsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bundle()


    }

    @SuppressLint("SetTextI18n")
    private fun bundle() {
        binding.apply {

            item = intent.getParcelableExtra("item")!!
            titleTxt.text = item.title
            descTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingBar.rating = item.rating.toFloat()

        }
    }
}