package com.example.marcos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class Contenedor_agregar extends Fragment {

    View vista;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MiAdaptador adapter;

    public Contenedor_agregar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_contenedor_agregar, container, false);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //para crear vistas con  findViewById se usa onCreateView para asegurarse que los elementos
        //de onCreateView ya esten inflados
        //super.onViewCreated(view, savedInstanceState);
        adapter = new MiAdaptador(this);
        viewPager2 = view.findViewById(R.id.viewPagerAgregar);
        tabLayout = view.findViewById(R.id.tab_layout);

        llenarViewPager();
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Presupuesto");break;
                case 1:
                    tab.setText("Pedido");break;
            }
        }).attach();
    }

    private void llenarViewPager() {
        adapter.addFragment(new fragment_presupuesto());
        adapter.addFragment(new fragment_pedido());
    }
}

class MiAdaptador extends FragmentStateAdapter {
    private final List<Fragment> lista = new ArrayList<>();

    public MiAdaptador(Fragment fragment) {
        super(fragment);
    }

    public void addFragment(Fragment fragment) {
        lista.add(fragment);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return lista.get(position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
