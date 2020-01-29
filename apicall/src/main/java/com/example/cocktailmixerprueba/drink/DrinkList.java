package com.example.cocktailmixerprueba.drink;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DrinkList {

    @SerializedName("drinks")
    private ArrayList<Drink> drinksList;

    public ArrayList<Drink> getDrinkArrayList() {
        return drinksList;
    }

    public void setEmployeeArrayList(ArrayList<Drink> drinkArrayList) {
        this.drinksList = drinkArrayList;
    }
}