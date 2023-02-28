package com.masterclass.FoodLove.Adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masterclass.FoodLove.Activity.ShowDetailActivity
import com.masterclass.FoodLove.Domain.FoodDomain
import com.masterclass.FoodLove.R

class PopularAdaptator(var popularFood: ArrayList<FoodDomain>) :
    RecyclerView.Adapter<PopularAdaptator.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = popularFood[position].title
        holder.fee.text = popularFood[position].fee.toString()
        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            popularFood[position].pic,
            "drawable",
            holder.itemView.context.packageName
        )
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)
        holder.addBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java)
            intent.putExtra("object", popularFood[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return popularFood.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var fee: TextView
        var pic: ImageView
        var addBtn: TextView
        var mainLayout: ConstraintLayout? = null

        init {
            title = itemView.findViewById(R.id.title)
            fee = itemView.findViewById(R.id.fee)
            pic = itemView.findViewById(R.id.pic)
            addBtn = itemView.findViewById(R.id.addBtn)
        }
    }
}