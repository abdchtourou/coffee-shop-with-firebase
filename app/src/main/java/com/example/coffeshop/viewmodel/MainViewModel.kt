package com.example.coffeshop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeshop.model.CategoryModel
import com.example.coffeshop.model.ItemsModel
import com.example.coffeshop.model.OfferModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {
    private val firebaseDateBase = FirebaseDatabase.getInstance()
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()
    private val _offer = MutableLiveData<MutableList<OfferModel>>()
    val category: LiveData<MutableList<CategoryModel>> = _category
    val popular: LiveData<MutableList<ItemsModel>> = _popular
    val offer: LiveData<MutableList<OfferModel>> = _offer

    fun getCategory() {
        val Ref = firebaseDateBase.getReference("Category")
        Ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<CategoryModel>()

                    for (data in snapshot.children) {
                        val model = data.getValue(CategoryModel::class.java)
                        if (model != null) {
                            list.add(model)
                        }


                    }
                    Log.d("TAG", "onDataChange: $list")

                    _category.value = list
                }

                override fun onCancelled(error: DatabaseError) {


                }

            }

        )

    }

    fun getPopular() {
        val Ref = firebaseDateBase.getReference("Items")
        Ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<ItemsModel>()
                    for (data in snapshot.children) {
                        val model = data.getValue(ItemsModel::class.java)
                        if (model != null) {
                            list.add(model)
                            Log.d("TAG", "onDataChange: ${model.picUrl}")

                        }
                    }
                    _popular.value = list
                    Log.d("TAG", "onDataChange: $list")

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled: $error")


                }
            }
        )


    }

    fun getOffer() {

        val Ref = firebaseDateBase.getReference("Offers")
        Ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<OfferModel>()

                    for (data in snapshot.children) {
                        val model = data.getValue(OfferModel::class.java)
                        if (model != null) {
                            list.add(model)
                        }
                    }
                    _offer.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }

        )

    }
}