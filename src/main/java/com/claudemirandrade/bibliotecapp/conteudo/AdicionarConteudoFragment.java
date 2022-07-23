package com.claudemirandrade.bibliotecapp.conteudo;

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

public class AdicionarConteudoFragment extends Fragment {
    // aqui criou-se as variaveis. elas foram inseridas aqui para que pudessem ser chamadas nos metodos seguintes.
    private EditText etNome;
    private EditText etSobre;
    private EditText etoQue;

    public AdicionarConteudoFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adicionar_conteudo, container, false);
        // aqui, as variaveis recebem os conteudos da tela
        etNome = v.findViewById(R.id.editText_nome_conteudo);
        etSobre = v.findViewById(R.id.editText_sobre_conteudo);
        etoQue = v.findViewById(R.id.editText_oque_conteudo);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_conteudo);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });
        return v;
    }
    // metodo para adicionar um novo conteudo, chamado em public void onClick(View view)
    private void adicionar() {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do conteúdo", Toast.LENGTH_SHORT).show();
        } else if (etSobre.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe sobre o que é este conteúdo", Toast.LENGTH_SHORT).show();
        } else if (etoQue.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o que é este conteúdo (ex: canal do youtube, blog, etc", Toast.LENGTH_SHORT).show();
        } else {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
            Conteudo c = new Conteudo();
            c.setNome(etNome.getText().toString());
            c.setSobre(etSobre.getText().toString());
            c.setOoQue(etoQue.getText().toString());
            dataBaseHelper.createConteudo(c);
            Toast.makeText(getActivity(), "Conteúdo salvo", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, new ListarConteudoFragment()).commit();
        }
    }
}