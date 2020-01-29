package com.example.cocktailmixerprueba.api;

import com.example.cocktailmixerprueba.drink.DrinkList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDrinkDataService {
    @GET("search.php?s=margarita")
    Call<DrinkList> getDrinkData(@Query("strDrink") String strDrink);
}