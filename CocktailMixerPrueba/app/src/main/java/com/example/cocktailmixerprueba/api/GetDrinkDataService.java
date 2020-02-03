package com.example.cocktailmixerprueba.api;

import com.example.cocktailmixerprueba.adapter.Drink;
import com.example.cocktailmixerprueba.adapter.DrinkList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDrinkDataService {

    @GET("filter.php")
    Call<DrinkList> getAlcoholicDrink(@Query("a") String tipo);

    @GET("random.php")
    Call<DrinkList> getRandomDrink();

    @GET("lookup.php")
    Call<DrinkList> getDrink(@Query("i") String idDrink);
}