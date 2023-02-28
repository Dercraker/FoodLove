package com.masterclass.FoodLove.Helper

import android.content.Context
import android.widget.Toast
import com.masterclass.FoodLove.Domain.FoodDomain
import com.masterclass.FoodLove.Interface.ChangeNumberItemsListener

class ManagementCart(private val context: Context?) {
    private val tinydb: TinyDB

    init {
        tinydb = TinyDB(context)
    }

    fun insertFood(item: FoodDomain) {
        val listFood = listCart
        var existAlready = false
        var n = 0
        for (i in listFood.indices) {
            if (listFood[i].title == item.title) {
                existAlready = true
                n = i
                break
            }
            if (existAlready) {
                listFood[n].numberInCart = item.numberInCart
            } else {
                listFood.add(item)
            }
            tinydb.putListObject("CardList", listFood)
            Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show()
        }
    }

    val listCart: ArrayList<FoodDomain>
        get() = tinydb.getListObject("CartList")

    fun plusNumberFood(
        listFood: ArrayList<FoodDomain>,
        positionn: Int,
        changeNumberItemsListener: ChangeNumberItemsListener?
    ) {
        listFood[positionn].numberInCart = listFood[positionn].numberInCart + 1
        tinydb.putListObject("CartList", listFood)
    }

    fun minNumberFood(
        listFood: ArrayList<FoodDomain>,
        positionn: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        if (listFood[positionn].numberInCart == 1) {
            listFood.removeAt(positionn)
        } else {
            listFood[positionn].numberInCart = listFood[positionn].numberInCart - 1
        }
        tinydb.putListObject("CartList", listFood)
        changeNumberItemsListener.changed()
    }

    val totalFee: Double
        get() {
            val listFood = listCart
            var fee = 0.0
            for (i in listFood.indices) {
                fee = fee + listFood[i].fee * listFood[i].numberInCart
            }
            return fee
        }
}