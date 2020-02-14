package com.example.cocktailmixerprueba.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktailmixerprueba.R;
import com.example.cocktailmixerprueba.ui.bebida.BebidaFragment;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> implements Filterable {

    private ArrayList<Drink> arrayListDrink;
    private ArrayList<Drink> arrayListFiltro;
    private FragmentActivity context;

    public DrinkAdapter(ArrayList<Drink> dataList, FragmentActivity context) {
        this.arrayListDrink = dataList;
        this.arrayListFiltro = dataList;
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

        holder.tvNomBebida.setText(arrayListFiltro.get(position).getStrDrink());
        // Aplicar imagen con glide
        String link = arrayListFiltro.get(position).getStrDrinkThumb();
        Glide.with(context).load(link).into(holder.ivBebida);
    }

    @Override
    public int getItemCount() {
        return arrayListFiltro.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    arrayListFiltro = arrayListDrink;

                } else {

                    ArrayList<Drink> filteredList = new ArrayList<>();

                    for (Drink drink : arrayListDrink) {

                        if (drink.getStrDrink().toLowerCase().contains(charString)) {

                            filteredList.add(drink);
                        }
                    }

                    arrayListFiltro = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayListFiltro;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayListFiltro = (ArrayList<Drink>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
            String id = arrayListFiltro.get(posicion).getIdDrink();

            Bundle bundle = new Bundle();
            bundle.putString("ID", id); // set your parameteres
            bundle.putString("tipo_bebida", "Non_Random");

            Fragment bebidaFragment = new BebidaFragment();
            bebidaFragment.setArguments(bundle);

            FragmentManager fragmentManager = context.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .replace(R.id.nav_host_fragment, bebidaFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}