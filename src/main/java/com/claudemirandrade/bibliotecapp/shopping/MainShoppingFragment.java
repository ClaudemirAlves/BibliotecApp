package com.claudemirandrade.bibliotecapp.shopping;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.claudemirandrade.bibliotecapp.R;

public class MainShoppingFragment extends Fragment {

    public MainShoppingFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_shopping, container, false);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_shopping, new ListarShoppingFragment()).commit();
        }
        Button btnAdicionar = v.findViewById(R.id.button_adicionar_shopping);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_shopping, new AdicionarShoppingFragment()).commit();
            }
        });

        Button btnListar = v.findViewById(R.id.button_listar_shopping);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_shopping, new ListarShoppingFragment()).commit();
            }
        });

        return v;
    }
}