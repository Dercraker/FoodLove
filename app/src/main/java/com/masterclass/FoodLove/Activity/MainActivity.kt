package com.masterclass.FoodLove.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterclass.FoodLove.Adaptor.CategoryAdaptator
import com.masterclass.FoodLove.Domain.CategoryDomain
import com.masterclass.FoodLove.R

class MainActivity : AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<*>? = null
    private var adapter2: RecyclerView.Adapter<*>? = null
    private var recyclerViewCategoryList: RecyclerView? = null
    private var recyclerViewPopularList: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewCategory()
    }

    private fun recyclerViewCategory() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.recyclerView)
        recyclerViewCategoryList?.setLayoutManager(linearLayoutManager)
        val category = ArrayList<CategoryDomain>()
        category.add(CategoryDomain("Pizza", "cat_1"))
        category.add(CategoryDomain("Burger", "cat_2"))
        category.add(CategoryDomain("HotDog", "cat_3"))
        category.add(CategoryDomain("Drink", "cat_4"))
        category.add(CategoryDomain("Donut", "cat_5"))
        adapter = CategoryAdaptator(category)
        recyclerViewCategoryList?.setAdapter(adapter)
    }


}