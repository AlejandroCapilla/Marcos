package com.example.marcos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Contenedor_agregar contenedor_agregar;
    fragment_lista fragment_lista;
    fragment_estadisticas fragment_estadisticas;
    fragment_ajustes fragment_ajustes;

    ActionBarDrawerToggle toggle;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //Setuo toolbar
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        contenedor_agregar = new Contenedor_agregar();
        fragment_lista = new fragment_lista();
        fragment_estadisticas = new fragment_estadisticas();
        fragment_ajustes = new fragment_ajustes();

        getSupportFragmentManager().beginTransaction().add(R.id.content, contenedor_agregar).commit();
        setTitle("Agregar");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectiItemNav(item);
        return true;
    }

    private void selectiItemNav(MenuItem item) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_add:
                if (contenedor_agregar.isAdded()) {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .show(contenedor_agregar);
                }
                break;
            case R.id.nav_list:
                if (fragment_lista.isAdded()) {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .show(fragment_lista);
                } else {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .add(R.id.content, fragment_lista);
                }
                break;
            case R.id.nav_estats:
                if (fragment_estadisticas.isAdded()) {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .show(fragment_estadisticas);
                } else {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .add(R.id.content, fragment_estadisticas);
                }
                break;
            case R.id.nav_settings:
                if (fragment_ajustes.isAdded()) {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .show(fragment_ajustes);
                } else {
                    fragmentTransaction
                            .hide(fragmentoActual())
                            .add(R.id.content, fragment_ajustes);
                }
                break;
        }
        fragmentTransaction.commit();

        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    Fragment fragmentoActual(){
        if (contenedor_agregar.isVisible()) {
            return contenedor_agregar;
        }else{
            if (fragment_lista.isVisible()) {
                return fragment_lista;
            }else {
                if (fragment_estadisticas.isVisible()) {
                    return fragment_estadisticas;
                }else {
                    return fragment_ajustes;
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}