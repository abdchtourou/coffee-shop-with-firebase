package com.example.coffeshop.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.R
import com.example.coffeshop.adatper.CategoryAdapter
import com.example.coffeshop.adatper.ItemAdapter
import com.example.coffeshop.adatper.OfferAdapter
import com.example.coffeshop.databinding.ActivityMainBinding
import com.example.coffeshop.viewmodel.MainViewModel

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.imageView4.setVisibility(View.GONE)
        val coordinatorLayout: CoordinatorLayout = findViewById(R.id.coordinatorLayout);
        coordinatorLayout.requestLayout();

        initCategory()
        initPopular()
        initOffer()
        bottomMenu()


    }

    private fun bottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity( Intent( this, CartActivity::class.java))


        }


    }

    private fun initOffer() {
        binding.progressBarOffer.visibility = View.VISIBLE
        viewModel.offer.observe(this) {
            binding.recyclerViewOffer.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewOffer.adapter = OfferAdapter(it)
            binding.progressBarOffer.visibility = View.GONE
            binding.imageView4.setVisibility(View.VISIBLE)
        }
        viewModel.getOffer()


    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this) {
            binding.recyclerViewPopular.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewPopular.adapter = ItemAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        }
        viewModel.getPopular()


    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this) {
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE

        }
        viewModel.getCategory()


    }
}