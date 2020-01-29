package com.example.cocktailmixerprueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.drink.Drink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private ArrayList<Drink> dataList;

    public DrinkAdapter(ArrayList<Drink> dataList) {
        this.dataList = dataList;
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
        Picasso.get().load(link).resize(128, 128).into(holder.ivBebida);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DrinkViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomBebida;
        ImageView ivBebida;

        DrinkViewHolder(View itemView) {
            super(itemView);
            tvNomBebida = itemView.findViewById(R.id.tvNomBebida);
            ivBebida = itemView.findViewById(R.id.ivBebida);
        }
    }
}