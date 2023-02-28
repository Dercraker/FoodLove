package com.masterclass.FoodLove.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.masterclass.FoodLove.Adaptor.CartListAdapter
import com.masterclass.FoodLove.Helper.ManagementCart
import com.masterclass.FoodLove.R

class CartListActivity : AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<*>? = null
    private var recyclerViewList: RecyclerView? = null
    private var managementCart: ManagementCart? = null
    var totalFeeTxt: TextView? = null
    var taxTxt: TextView? = null
    var deliveryTxt: TextView? = null
    var totalTxt: TextView? = null
    var emptyTxt: TextView? = null
    private var tax = 0.0
    private var scrollView: ScrollView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        managementCart = ManagementCart(this)
        initView()
        initList()
        CalculateCart()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.CartBtn)
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        floatingActionButton.setOnClickListener {
            startActivity(
                Intent(
                    this@CartListActivity,
                    CartListActivity::class.java
                )
            )
        }
        homeBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@CartListActivity,
                    MainActivity::class.java
                )
            )
        }
    }

    private fun initView() {
        recyclerViewList = findViewById(R.id.recyclerView)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.totalTxt)
        emptyTxt = findViewById(R.id.emptyTxt)
        scrollView = findViewById(R.id.scrollView2)
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList!!.layoutManager = linearLayoutManager
        adapter = CartListAdapter(managementCart!!.listCart, this) { CalculateCart() }
        recyclerViewList!!.adapter = adapter
        if (managementCart!!.listCart.isEmpty()) {
            emptyTxt!!.visibility = View.VISIBLE
            scrollView!!.visibility = View.GONE
        } else {
            emptyTxt!!.visibility = View.GONE
            scrollView!!.visibility = View.VISIBLE
        }
    }

    private fun CalculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = (Math.round(managementCart!!.totalFee * percentTax * 100) / 100).toDouble()
        val total =
            (Math.round((managementCart!!.totalFee + tax + delivery) * 100) / 100).toDouble()
        val itemTotal = (Math.round(managementCart!!.totalFee * 100) / 100).toDouble()
        totalFeeTxt!!.text = "$$itemTotal"
        taxTxt!!.text = "$$tax"
        deliveryTxt!!.text = "$$delivery"
        totalTxt!!.text = "$$total"
    }
}