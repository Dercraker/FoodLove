package com.masterclass.FoodLove.Activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.masterclass.FoodLove.Domain.FoodDomain
import com.masterclass.FoodLove.Helper.ManagementCart
import com.masterclass.FoodLove.R

class ShowDetailActivity : AppCompatActivity() {
    private var addToCartBtn: TextView? = null
    private var titleTxt: TextView? = null
    private var feeTxt: TextView? = null
    private var descriptionTxt: TextView? = null
    private var numberOrderTxt: TextView? = null
    private var plusBtn: ImageView? = null
    private var minusBtn: ImageView? = null
    private var picFood: ImageView? = null
    private var `object`: FoodDomain? = null
    var numberOrder = 1
    private var managementCart: ManagementCart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        managementCart = ManagementCart(this)
        initView()
        bundle
    }

    private val bundle: Unit
        private get() {
            `object` = intent.getSerializableExtra("object") as FoodDomain?
            val drawableRessourceId =
                this.resources.getIdentifier(`object`!!.pic, "drawable", this.packageName)
            Glide.with(this)
                .load(drawableRessourceId)
                .into(picFood!!)
            titleTxt!!.text = `object`!!.title
            feeTxt!!.text = "$" + `object`!!.fee
            descriptionTxt!!.text = `object`!!.desc
            numberOrderTxt!!.text = numberOrder.toString()
            plusBtn!!.setOnClickListener {
                numberOrder = numberOrder + 1
                numberOrderTxt!!.text = numberOrder.toString()
            }
            minusBtn!!.setOnClickListener {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1
                }
                numberOrderTxt!!.text = numberOrder.toString()
            }
            addToCartBtn!!.setOnClickListener {
                `object`!!.numberInCart = numberOrder
                managementCart!!.insertFood(`object`!!)
            }
        }

    private fun initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn)
        titleTxt = findViewById(R.id.titleTxt)
        feeTxt = findViewById(R.id.priceTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picFood)
    }
}