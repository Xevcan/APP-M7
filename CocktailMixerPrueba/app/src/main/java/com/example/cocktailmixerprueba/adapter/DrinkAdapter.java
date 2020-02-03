package com.example.cocktailmixerprueba.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.ui.bebida.BebidaFragment;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private ArrayList<Drink> dataList;
    private Context context;

    public DrinkAdapter(ArrayList<Drink> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_grid_bebida, parent, false);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder holder, int position) {

        holder.tvNomBebida.setText(dataList.get(position).getStrDrink());
        // Aplicar imagen con picasso
        String link = dataList.get(position).getStrDrinkThumb();
        Glide.with(context).load(link).into(holder.ivBebida);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DrinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNomBebida;
        ImageView ivBebida;

        DrinkViewHolder(View itemView) {
            super(itemView);
            tvNomBebida = itemView.findViewById(R.id.tvNomBebida);
            ivBebida = itemView.findViewById(R.id.ivBebida);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int posicion = getAdapterPosition();
            String id = dataList.get(posicion).getIdDrink();

            Bundle bundle = new Bundle();
            bundle.putString("ID", id); // set your parameteres

            BebidaFragment bebidaFragment = new BebidaFragment();
            bebidaFragment.setArguments(bundle);

            FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            int numFragmentos = ((AppCompatActivity) context).getSupportFragmentManager().getBackStackEntryCount();

            if (numFragmentos >= 1) {
                // Sacamos el de atrás de la pila (creo que era para evitar que cuando pulsaras el botón BACK volvieras a la vista anterior)
                ((AppCompatActivity) context).getSupportFragmentManager().popBackStack();

                // Reemplazamos
                ft.replace(R.id.nav_host_fragment, bebidaFragment);
                ft.remove(((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment));


            } else {
                // En caso de no existir ninguno simplemente añadimos
                ft.add(R.id.nav_host_fragment, bebidaFragment);
            }
            ft.commit();
        }
    }
}