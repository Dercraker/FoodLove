package com.masterclass.FoodLove.Helper;

import android.content.Context;
import android.widget.Toast;

import com.masterclass.FoodLove.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinydb;
    public ManagementCart(Context context){
        this.context = context;
        this.tinydb = new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready= false;
        int n=0;
        for (int i=0; i<listFood.size();i++){
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
            if (existAlready){
                listFood.get(n).setNumberInCart(item.getNumberInCart());
            }else {
                listFood.add(item);
            }
            tinydb.putListObject("CardList",listFood);
            Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinydb.getListObject("CartList");
    }
}
