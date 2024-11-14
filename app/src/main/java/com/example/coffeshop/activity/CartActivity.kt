package com.example.coffeshop.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeshop.R
import com.example.coffeshop.databinding.ActivityCartBinding
import com.example.project1762.Helper.ManagementCart

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagementCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managementCart = ManagementCart(this)
        calculateCart()
        setVariable()
        initCartList()

    }

    private fun initCartList() {
        with(binding){
            cartView.layoutManager= LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)

        }
    }

    private fun setVariable() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

        }

    }

    private fun calculateCart() {
        val percentTax: Double = 0.02
        val delivery:Double=15.0
        tax = Math.round((managementCart.getTotalFee() * percentTax)*100) / 100.0
        val total = Math.round(managementCart.getTotalFee() + tax + delivery) / 100.0
        val itemTotal = Math.round(managementCart.getTotalFee()*100) / 100.0
        with(binding) {
            totalFeetxt.text = "$" + itemTotal
            taxTxt.text = "$" + tax
            deliveryTxt.text = "$" + delivery
            totalTxt.text = "$" + total

        }
    }
}