package com.example.cocktailmixerprueba.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.adapter.DrinkAdapter;
import com.example.cocktailmixerprueba.api.GetDrinkDataService;
import com.example.cocktailmixerprueba.api.RetrofitInstance;
import com.example.cocktailmixerprueba.drink.Drink;
import com.example.cocktailmixerprueba.drink.DrinkList;
import com.example.cocktailmixerprueba.utils.Tools;
import com.example.cocktailmixerprueba.widget.SpacingItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    View view;
    RecyclerView rvBebidas;
    DrinkAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GetDrinkDataService service = RetrofitInstance.getRetrofitInstance().create(GetDrinkDataService.class);

        // Llamamos al método pasándole por paráemtro de la interficie para coger datos de bebidas
        Call<DrinkList> call = service.getDrinkData("margarita");

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {
                generateDrinkList(response.body().getDrinkArrayList());
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Toast.makeText(getContext(), "Ha ocurrido un problema... intentalo más tarde!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Genera la lista de bebidas usando RecyclerView con un adaptador custom
    private void generateDrinkList(ArrayList<Drink> empDataList) {
        rvBebidas = view.findViewById(R.id.rvBebidas);

        adapter = new DrinkAdapter(empDataList);

        // Cambiar el RecyclerView para que sea como Grid de MaterialX
        rvBebidas.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvBebidas.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getContext(), 3), true));
        rvBebidas.setHasFixedSize(true);

        rvBebidas.setAdapter(adapter);
    }
}