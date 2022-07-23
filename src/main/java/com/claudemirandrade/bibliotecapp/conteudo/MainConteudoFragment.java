package com.claudemirandrade.bibliotecapp.conteudo;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.claudemirandrade.bibliotecapp.R;

public class MainConteudoFragment extends Fragment {

    public MainConteudoFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_conteudo, container, false);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, new ListarConteudoFragment()).commit();
        }
        Button btnAdicionar = v.findViewById(R.id.button_adicionar_conteudo);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, new AdicionarConteudoFragment()).commit();
            }
        });

        Button btnListar = v.findViewById(R.id.button_listar_conteudo);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, new ListarConteudoFragment()).commit();
            }
        });

        return v;
    }
}