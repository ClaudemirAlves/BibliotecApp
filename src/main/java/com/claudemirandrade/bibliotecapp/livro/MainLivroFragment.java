package com.claudemirandrade.bibliotecapp.livro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.claudemirandrade.bibliotecapp.R;

public class MainLivroFragment extends Fragment {

    public MainLivroFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_livro, container, false);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_livro, new ListarLivroFragment()).commit();
        }
        Button btnAdicionar = v.findViewById(R.id.button_adicionar_livro);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_livro, new AdicionarLivroFragment()).commit();
            }
        });

        Button btnListar = v.findViewById(R.id.button_listar_livros);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_livro, new ListarLivroFragment()).commit();
            }
        });

        return v;
    }
}