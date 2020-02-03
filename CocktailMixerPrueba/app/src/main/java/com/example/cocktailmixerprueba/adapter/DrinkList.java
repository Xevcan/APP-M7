package com.example.cocktailmixerprueba.adapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DrinkList {

    @SerializedName("drinks")
    private ArrayList<Drink> drinksList;

    public ArrayList<Drink> getDrinkArrayList() {
        return drinksList;
    }

    public void setDrinkArrayList(ArrayList<Drink> drinkArrayList) {
        this.drinksList = drinkArrayList;
    }
}