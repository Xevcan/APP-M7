package com.example.cocktailmixerprueba;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cocktailmixerprueba.ui.about.AboutFragment;
import com.example.cocktailmixerprueba.ui.bebida.BebidaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AboutFragment aboutFragment = new AboutFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            int numFragmentos = getSupportFragmentManager().getBackStackEntryCount();

            if (numFragmentos >= 1) {
                // Sacamos el de atrás de la pila (creo que era para evitar que cuando pulsaras el botón BACK volvieras a la vista anterior)
                getSupportFragmentManager().popBackStack();

                // Reemplazamos
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment, aboutFragment);

            } else {
                // En caso de no existir ninguno simplemente añadimos
                ft.addToBackStack(null);
                ft.add(R.id.nav_host_fragment, aboutFragment);
            }
            ft.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
