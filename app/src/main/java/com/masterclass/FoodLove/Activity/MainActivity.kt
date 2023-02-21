package com.masterclass.FoodLove.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masterclass.FoodLove.Adaptor.CategoryAdaptator
import com.masterclass.FoodLove.Adaptor.PopularAdaptator
import com.masterclass.FoodLove.Domain.CategoryDomain
import com.masterclass.FoodLove.Domain.FoodDomain
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
        recyclerViewPopular()
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
    private fun recyclerViewPopular() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.recyclerView2)
        recyclerViewPopularList?.setLayoutManager(linearLayoutManager)
        val foodList = java.util.ArrayList<FoodDomain>()
        foodList.add(
            FoodDomain(
                "Pepperoni Pizza",
                "pizza1",
                "slices pepperoni,mozzarella cheese, fresh oreo, ground black pepper, pizza sauce",
                9.76
            )
        )
        foodList.add(
            FoodDomain(
                "Cheese Burger",
                "Burger",
                "beef, Gouda Cheese, Special sauce, lettuce, tomato",
                8.79
            )
        )
        foodList.add(
            FoodDomain(
                "Vegetable Pizza",
                "pizza2",
                "olive,oil,pitted kalamata, cherry tomatoes, fresh oregano, basil",
                8.5
            )
        )
        adapter2 = PopularAdaptator(foodList)
        recyclerViewPopularList!!.adapter = adapter2
    }

}