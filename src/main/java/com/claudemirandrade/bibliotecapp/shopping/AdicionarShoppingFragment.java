package com.claudemirandrade.bibliotecapp.shopping;

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

public class AdicionarShoppingFragment extends Fragment {

    private EditText etNome;
    private EditText etSobre;
    private EditText etoQue;

    public AdicionarShoppingFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adicionar_shopping, container, false);

        etNome = v.findViewById(R.id.editText_nome_shopping);
        etSobre = v.findViewById(R.id.editText_sobre_shopping);
        etoQue = v.findViewById(R.id.editText_oque_shopping);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_shopping);
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
            Toast.makeText(getActivity(), "Por favor, informe o nome da loja", Toast.LENGTH_SHORT).show();
        } else if (etSobre.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe sobre o que a loja vende", Toast.LENGTH_SHORT).show();
        } else if (etoQue.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o que é a loja (ex.: e-commerce, loja física, etc", Toast.LENGTH_SHORT).show();
        } else {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
            Shopping s = new Shopping();
            s.setNome(etNome.getText().toString());
            s.setSobre(etSobre.getText().toString());
            s.setOoQue(etoQue.getText().toString());
            dataBaseHelper.createShopping(s);
            Toast.makeText(getActivity(), "Loja salva", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_shopping, new ListarShoppingFragment()).commit();
        }
    }
}