package com.masterclass.FoodLove.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.masterclass.FoodLove.Domain.FoodDomain;
import com.masterclass.FoodLove.R;

import java.util.ArrayList;

public class aa {
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList() = findViewById(R.id.recyclerView2);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni Pizza", "pizza1", "slices pepperoni,mozzarella cheese, fresh oreo, ground black pepper, pizza sauce", 9.76));
    }
}
