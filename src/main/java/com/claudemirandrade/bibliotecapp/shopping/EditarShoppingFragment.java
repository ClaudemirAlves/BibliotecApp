package com.claudemirandrade.bibliotecapp.shopping;

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

public class EditarShoppingFragment extends Fragment {

    private EditText etNome;
    private EditText etSobre;
    private EditText etoQue;
    private DataBaseHelper dataBaseHelper;
    private Shopping s;

    public EditarShoppingFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editar_shopping, container, false);

        etNome = v.findViewById(R.id.editText_nome_shopping);
        etSobre = v.findViewById(R.id.editText_sobre_shopping);
        etoQue = v.findViewById(R.id.editText_oque_shopping);

        Bundle b = getArguments();
        int id_shopping = b.getInt("id");
        dataBaseHelper = new DataBaseHelper(getActivity());
        s = dataBaseHelper.getByIdShopping(id_shopping);

        etNome.setText(s.getNome());
        etSobre.setText(s.getSobre());
        etoQue.setText(s.getoQue());

        Button btnEditar = v.findViewById(R.id.button_editar_shopping);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_shopping);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir_shopping);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Excluir loja?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_shopping);
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
            Toast.makeText(getActivity(), "Por favor, informe o nome da loja", Toast.LENGTH_SHORT).show();
        } else if (etSobre.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe sobre o que a loja vende", Toast.LENGTH_SHORT).show();
        } else if (etoQue.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o que é a loja (ex.: e-commerce, loja física, etc)", Toast.LENGTH_SHORT).show();
        } else {
            s = new Shopping();
            s.setId(id);
            s.setNome(etNome.getText().toString());
            s.setSobre(etSobre.getText().toString());
            s.setOoQue(etoQue.getText().toString());
            dataBaseHelper.updateShopping(s);
            Toast.makeText(getActivity(), "Loja atualizada", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_shopping, new ListarShoppingFragment()).commit();
        }
    }

    private void excluir (int id) {
        s = new Shopping();
        s.setId(id);
        dataBaseHelper.deleteShopping(s);
        Toast.makeText(getActivity(), "Loja excluída", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_shopping, new ListarShoppingFragment()).commit();
    }
}