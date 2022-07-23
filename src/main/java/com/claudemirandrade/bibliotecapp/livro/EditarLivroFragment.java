package com.claudemirandrade.bibliotecapp.livro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.claudemirandrade.bibliotecapp.R;
import com.claudemirandrade.bibliotecapp.database.DataBaseHelper;

public class EditarLivroFragment extends Fragment {
    // aqui criou-se as variaveis. elas foram inseridas aqui para que pudessem ser chamadas nos metodos seguintes.
    private EditText etNome;
    private EditText etAutor;
    private EditText etPaginas;
    private DataBaseHelper dataBaseHelper;
    private Livro l;

    public EditarLivroFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editar_livro, container, false);
        // aqui, as variaveis recebem os conteudos da tela
        etNome = v.findViewById(R.id.editText_nome_livro);
        etAutor = v.findViewById(R.id.editText_autor_livro);
        etPaginas = v.findViewById(R.id.editText_paginas_livro);

        Bundle b = getArguments();
        int id_livro = b.getInt("id");
        dataBaseHelper = new DataBaseHelper(getActivity());
        l = dataBaseHelper.getByIdLivro(id_livro);

        etNome.setText(l.getNome());
        etAutor.setText(l.getAutor());
        etPaginas.setText(l.getPaginas());

        Button btnEditar = v.findViewById(R.id.button_editar_livro);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_livro);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_livro);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Excluir Livro?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_livro);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Não faz nada
                        // este metodo vazio eh necessario para que o usuario possa clicar em nao
                        // e o sistema voltar a tela anterior sem nenhum efeito adicional
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return v;
    }

    private void editar(int id) {
        if (etNome.getText().toString().equals("")) { // sem campo nome vazio
            Toast.makeText(getActivity(), "Por favor, informe o nome do livro", Toast.LENGTH_SHORT).show();
        } else if (etAutor.getText().toString().equals("")) { // sem campo autor vazio
            Toast.makeText(getActivity(), "Por favor, informe o autor do livro", Toast.LENGTH_SHORT).show();
        } else if (etPaginas.getText().toString().equals("")) { // sem campo paginas vazio
            Toast.makeText(getActivity(), "Por favor, informe o número de páginas do livro", Toast.LENGTH_SHORT).show();
        } else {
            l = new Livro();
            l.setId(id);
            l.setNome(etNome.getText().toString());
            l.setAutor(etAutor.getText().toString());
            l.setPaginas(etPaginas.getText().toString());
            dataBaseHelper.updateLivro(l);
            Toast.makeText(getActivity(), "Livro atualizado", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_livro, new ListarLivroFragment()).commit();
        }
    }

    private void excluir (int id) {
        l = new Livro();
        l.setId(id);
        dataBaseHelper.deleteLivro(l);
        Toast.makeText(getActivity(), "Livro excluído", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_livro, new ListarLivroFragment()).commit();
    }
}