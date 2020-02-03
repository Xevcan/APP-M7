package com.example.cocktailmixerprueba.ui.bebida;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.adapter.DrinkList;
import com.example.cocktailmixerprueba.api.GetDrinkDataService;
import com.example.cocktailmixerprueba.api.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BebidaFragment extends Fragment {

    View view;
    TextView tvID;
    TextView tvNombre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bebida, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle recuperarID = getArguments();
        String idBebida = "";

        if (recuperarID != null) {
            idBebida = recuperarID.getString("ID");
        }

        tvID = view.findViewById(R.id.tvID);
        tvNombre = view.findViewById(R.id.tvNombre);

        GetDrinkDataService service = RetrofitInstance.getRetrofitInstance().create(GetDrinkDataService.class);
        Call<DrinkList> call = service.getDrink(idBebida);

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {

                if (response.isSuccessful()) {

                    String prueba = response.body().getDrinkArrayList().get(0).getStrDrink();
                    tvNombre.setText(prueba);

                } else {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DrinkList> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}