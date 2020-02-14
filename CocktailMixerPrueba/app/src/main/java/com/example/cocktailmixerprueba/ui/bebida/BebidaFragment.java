package com.example.cocktailmixerprueba.ui.bebida;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.adapter.DrinkList;
import com.example.cocktailmixerprueba.api.GetDrinkDataService;
import com.example.cocktailmixerprueba.api.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BebidaFragment extends Fragment {

    private static final String ARG_TIPO_BEBIDA = "tipo_bebida";

    View view;
    TextView tvID;
    TextView tvNombre;
    TextView tvVaso;
    TextView tvAlcoholic;
    TextView tvInstrucciones;
    TextView tvIngrediente1;
    TextView tvMedida1;
    ImageView imBebida;
    ImageView imIcono;
    LinearLayout llvIngredientes;
    String tipoBebida;

    public BebidaFragment() {
    }

    public static BebidaFragment newInstance(String tipoBebida) {
        BebidaFragment fragment = new BebidaFragment();
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

        // tvID = view.findViewById(R.id.tvID);
        tvNombre = view.findViewById(R.id.tvNombre);
        tvVaso = view.findViewById(R.id.tvVaso);
        tvAlcoholic = view.findViewById(R.id.tvAlcoholic);
        tvInstrucciones = view.findViewById(R.id.tvInstrucciones);
        llvIngredientes = view.findViewById(R.id.llvIngredientes);
        imBebida = view.findViewById(R.id.imBebida);
        imIcono = view.findViewById(R.id.imIcono);

        GetDrinkDataService service = RetrofitInstance.getRetrofitInstance().create(GetDrinkDataService.class);
        Call<DrinkList> call;

        if (tipoBebida.equalsIgnoreCase("Random")) {
            call = service.getRandomDrink();
        } else {
            call = service.getDrink(idBebida);
        }

        call.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(Call<DrinkList> call, Response<DrinkList> response) {

                if (response.isSuccessful()) {

                    // String id = response.body().getDrinkArrayList().get(0).getIdDrink();
                    // tvID.setText(id);

                    String nombre = response.body().getDrinkArrayList().get(0).getStrDrink();
                    tvNombre.setText(nombre);

                    String alcoholic = response.body().getDrinkArrayList().get(0).getStrAlcoholic();
                    tvAlcoholic.setText(alcoholic);
                    String vaso = response.body().getDrinkArrayList().get(0).getStrGlass();
                    tvVaso.setText(vaso);
                    String instrucciones = response.body().getDrinkArrayList().get(0).getStrInstructions();
                    tvInstrucciones.setText(instrucciones);

                    //Para las imagenes usar picasso
                    String imagenURL = response.body().getDrinkArrayList().get(0).getStrDrinkThumb();
                    Glide.with(getContext()).load(imagenURL).into(imBebida);


                    //Cambiar el icono en el caso de que sea aloholica o no
                    if (alcoholic.equals("Non alcoholic")) {
                        imIcono.setImageResource(R.drawable.noalcohol2);
                    }

                    //Los diferentes TextView de los ingredientes
                    String ingrediente1 = response.body().getDrinkArrayList().get(0).getStrIngredient1();
                    String ingrediente2 = response.body().getDrinkArrayList().get(0).getStrIngredient2();
                    String ingrediente3 = response.body().getDrinkArrayList().get(0).getStrIngredient3();
                    String ingrediente4 = response.body().getDrinkArrayList().get(0).getStrIngredient4();
                    String ingrediente5 = response.body().getDrinkArrayList().get(0).getStrIngredient5();
                    String ingrediente6 = response.body().getDrinkArrayList().get(0).getStrIngredient6();
                    String ingrediente7 = response.body().getDrinkArrayList().get(0).getStrIngredient7();
                    String ingrediente8 = response.body().getDrinkArrayList().get(0).getStrIngredient8();
                    String ingrediente9 = response.body().getDrinkArrayList().get(0).getStrIngredient9();
                    String ingrediente10 = response.body().getDrinkArrayList().get(0).getStrIngredient10();
                    String ingrediente11 = response.body().getDrinkArrayList().get(0).getStrIngredient11();
                    String ingrediente12 = response.body().getDrinkArrayList().get(0).getStrIngredient12();

                    String medida1 = response.body().getDrinkArrayList().get(0).getStrMeasure1();
                    String medida2 = response.body().getDrinkArrayList().get(0).getStrMeasure2();
                    String medida3 = response.body().getDrinkArrayList().get(0).getStrMeasure3();
                    String medida4 = response.body().getDrinkArrayList().get(0).getStrMeasure4();
                    String medida5 = response.body().getDrinkArrayList().get(0).getStrMeasure5();
                    String medida6 = response.body().getDrinkArrayList().get(0).getStrMeasure6();
                    String medida7 = response.body().getDrinkArrayList().get(0).getStrMeasure7();
                    String medida8 = response.body().getDrinkArrayList().get(0).getStrMeasure8();
                    String medida9 = response.body().getDrinkArrayList().get(0).getStrMeasure9();
                    String medida10 = response.body().getDrinkArrayList().get(0).getStrMeasure10();
                    String medida11 = response.body().getDrinkArrayList().get(0).getStrMeasure11();
                    String medida12 = response.body().getDrinkArrayList().get(0).getStrMeasure12();

                    ArrayList<String> arrayIngredientes = new ArrayList<String>();
                    arrayIngredientes.add(ingrediente1);
                    arrayIngredientes.add(ingrediente2);
                    arrayIngredientes.add(ingrediente3);
                    arrayIngredientes.add(ingrediente4);
                    arrayIngredientes.add(ingrediente5);
                    arrayIngredientes.add(ingrediente6);
                    arrayIngredientes.add(ingrediente7);
                    arrayIngredientes.add(ingrediente8);
                    arrayIngredientes.add(ingrediente9);
                    arrayIngredientes.add(ingrediente10);
                    arrayIngredientes.add(ingrediente11);
                    arrayIngredientes.add(ingrediente12);

                    ArrayList<String> arrayMedidas = new ArrayList<String>();
                    arrayMedidas.add(medida1);
                    arrayMedidas.add(medida2);
                    arrayMedidas.add(medida3);
                    arrayMedidas.add(medida4);
                    arrayMedidas.add(medida5);
                    arrayMedidas.add(medida6);
                    arrayMedidas.add(medida7);
                    arrayMedidas.add(medida8);
                    arrayMedidas.add(medida9);
                    arrayMedidas.add(medida10);
                    arrayMedidas.add(medida11);
                    arrayMedidas.add(medida12);


                    for (int i = 0; i < 12; i++) {

                        if (arrayIngredientes.get(i) != null) {
                            LinearLayout linearLayout = new LinearLayout(getContext());
                            setContentView(linearLayout);
                            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                            TextView tvMedida = new TextView(getContext());
                            tvMedida.setText(arrayMedidas.get(i));
                            tvMedida.setTypeface(null, Typeface.BOLD);
                            TextView tvIngrediente = new TextView(getContext());
                            tvIngrediente.setText(arrayIngredientes.get(i));
                            linearLayout.addView(tvMedida);
                            linearLayout.addView(tvIngrediente);
                            llvIngredientes.addView(linearLayout);
                        }
                    }


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

    private void setContentView(LinearLayout linearLayout) {

    }
}