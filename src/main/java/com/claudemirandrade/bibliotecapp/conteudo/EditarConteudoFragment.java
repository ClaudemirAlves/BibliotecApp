package com.claudemirandrade.bibliotecapp.conteudo;

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

public class EditarConteudoFragment extends Fragment {

    private EditText etNome;
    private EditText etSobre;
    private EditText etoQue;
    private DataBaseHelper dataBaseHelper;
    private Conteudo c;

    public EditarConteudoFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editar_conteudo, container, false);

        etNome = v.findViewById(R.id.editText_nome_conteudo);
        etSobre = v.findViewById(R.id.editText_sobre_conteudo);
        etoQue = v.findViewById(R.id.editText_oque_conteudo);

        Bundle b = getArguments();
        int id_conteudo = b.getInt("id");
        dataBaseHelper = new DataBaseHelper(getActivity());
        c = dataBaseHelper.getByIdConteudo(id_conteudo);

        etNome.setText(c.getNome());
        etSobre.setText(c.getSobre());
        etoQue.setText(c.getoQue());

        Button btnEditar = v.findViewById(R.id.button_editar_conteudo);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_conteudo);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_conteudo);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Excluir Conteúdo?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_conteudo);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return v;
    }

    private void editar(int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do conteúdo", Toast.LENGTH_SHORT).show();
        } else if (etSobre.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe sobre o que é o conteúdo", Toast.LENGTH_SHORT).show();
        } else if (etoQue.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o que é o conteúdo (ex.: canal do youtube, blog, etc)", Toast.LENGTH_SHORT).show();
        } else {
            c = new Conteudo();
            c.setId(id);
            c.setNome(etNome.getText().toString());
            c.setSobre(etSobre.getText().toString());
            c.setOoQue(etoQue.getText().toString());
            dataBaseHelper.updateConteudo(c);
            Toast.makeText(getActivity(), "Conteúdo atualizado", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, new ListarConteudoFragment()).commit();
        }
    }

    private void excluir (int id) {
        c = new Conteudo();
        c.setId(id);
        dataBaseHelper.deleteConteudo(c);
        Toast.makeText(getActivity(), "Conteúdo excluído", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, new ListarConteudoFragment()).commit();
    }
}