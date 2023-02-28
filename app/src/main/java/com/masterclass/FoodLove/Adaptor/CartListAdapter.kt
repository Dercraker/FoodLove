package com.masterclass.FoodLove.Adaptor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masterclass.FoodLove.Domain.FoodDomain
import com.masterclass.FoodLove.Helper.ManagementCart
import com.masterclass.FoodLove.Interface.ChangeNumberItemsListener
import com.masterclass.FoodLove.R

class CartListAdapter(
    private val foodDomains: ArrayList<FoodDomain>,
    context: Context?,
    changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private val managementCart: ManagementCart
    private val changeNumberItemsListener: ChangeNumberItemsListener

    init {
        managementCart = ManagementCart(context)
        this.changeNumberItemsListener = changeNumberItemsListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.title.text = foodDomains.get(position).title
        holder.feeEachItem.text = foodDomains.get(position).fee.toString()
        holder.totalEachItem.text =
            (Math.round((foodDomains.get(position).numberInCart * foodDomains.get(position).fee) * 100) / 100).toString()
        holder.num.text = foodDomains.get(position).numberInCart.toString()
        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            foodDomains[position].pic,
            "drawable", holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)
        holder.plusItem.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                managementCart.plusNumberFood(foodDomains, position, ChangeNumberItemsListener {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                })
            }
        })
        holder.minusItem.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                managementCart.minNumberFood(
                    foodDomains,
                    position,
                    object : ChangeNumberItemsListener {
                        override fun changed() {
                            notifyDataSetChanged()
                            changeNumberItemsListener.changed()
                        }
                    })
            }
        })
    }

    override fun getItemCount(): Int {
        return foodDomains.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var feeEachItem: TextView
        var pic: ImageView
        var plusItem: ImageView
        var minusItem: ImageView
        var totalEachItem: TextView
        var num: TextView

        init {
            title = itemView.findViewById(R.id.titleTxt)
            feeEachItem = itemView.findViewById(R.id.fee)
            pic = itemView.findViewById(R.id.picCart)
            totalEachItem = itemView.findViewById(R.id.totalEachItem)
            num = itemView.findViewById(R.id.numberItemTxt)
            plusItem = itemView.findViewById(R.id.plusCartBtn)
            minusItem = itemView.findViewById(R.id.minusCartBtn)
        }
    }
}