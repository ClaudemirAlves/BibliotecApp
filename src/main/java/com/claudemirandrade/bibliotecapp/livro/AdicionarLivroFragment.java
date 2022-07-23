package com.claudemirandrade.bibliotecapp.livro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.claudemirandrade.bibliotecapp.R;
import com.claudemirandrade.bibliotecapp.database.DataBaseHelper;

public class AdicionarLivroFragment extends Fragment {

    private EditText etNome;
    private EditText etAutor;
    private EditText etPaginas;

    public AdicionarLivroFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adicionar_livro, container, false);

        etNome = v.findViewById(R.id.editText_nome_livro);
        etAutor = v.findViewById(R.id.editText_autor_livro);
        etPaginas = v.findViewById(R.id.editText_paginas_livro);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_livro);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });
        return v;
    }

    private void adicionar() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do livro", Toast.LENGTH_SHORT).show();
        } else if (etAutor.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o autor do livro", Toast.LENGTH_SHORT).show();
        } else if (etPaginas.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número de páginas do livro", Toast.LENGTH_SHORT).show();
        } else {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
            Livro l = new Livro();
            l.setNome(etNome.getText().toString());
            l.setAutor(etAutor.getText().toString());
            l.setPaginas(etPaginas.getText().toString());
            dataBaseHelper.createLivro(l);
            Toast.makeText(getActivity(), "Livro salvo", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_livro, new ListarLivroFragment()).commit();
        }
    }
}