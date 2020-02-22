package com.example.cocktailmixerprueba.ui.bebida;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.adapter.Drink;
import com.example.cocktailmixerprueba.adapter.DrinkAdapter;
import com.example.cocktailmixerprueba.adapter.DrinkList;
import com.example.cocktailmixerprueba.api.GetDrinkDataService;
import com.example.cocktailmixerprueba.api.RetrofitInstance;
import com.example.cocktailmixerprueba.utils.Tools;
import com.example.cocktailmixerprueba.widget.SpacingItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String ARG_TIPO_BEBIDA = "tipo_bebida";

    View view;
    RecyclerView rvBebidas;
    public static DrinkAdapter adapter;
    String tipoBebida;
    Call<DrinkList> call;
    public static boolean buscar = false;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String tipoBebida) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TIPO_BEBIDA, tipoBebida);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipoBebida = getArguments().getString(ARG_TIPO_BEBIDA);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GetDrinkDataService service = RetrofitInstance.getRetrofitInstance().create(GetDrinkDataService.class);

        if (tipoBebida.equalsIgnoreCase("Random")) {
            call = service.getRandomDrink();
        } else {
            call = service.getAlcoholicDrink(tipoBebida);
        }

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {

                if (response.isSuccessful()) {
                    generateDrinkList(response.body().getDrinkArrayList());
                    buscar = true;
                }
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Toast.makeText(getContext(), "Ha ocurrido un problema... intentalo más tarde!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Genera la lista de bebidas usando RecyclerView con un adaptador custom
    private void generateDrinkList(ArrayList<Drink> drinkDataList) {
        rvBebidas = view.findViewById(R.id.rvBebidas);

        adapter = new DrinkAdapter(drinkDataList, getActivity(), tipoBebida);

        // Cambiar el RecyclerView para que sea como Grid de MaterialX
        rvBebidas.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvBebidas.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getContext(), 3), true));
        rvBebidas.setHasFixedSize(true);

        rvBebidas.setAdapter(adapter);
    }
}