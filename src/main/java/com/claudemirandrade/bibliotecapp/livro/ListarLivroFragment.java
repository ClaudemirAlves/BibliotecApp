package com.claudemirandrade.bibliotecapp.livro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.claudemirandrade.bibliotecapp.R;
import com.claudemirandrade.bibliotecapp.database.DataBaseHelper;

public class ListarLivroFragment extends Fragment {

    public ListarLivroFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_livro, container, false);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_listar_livros);
        dataBaseHelper.getAllLivro(getActivity(), lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvId = view.findViewById(R.id.textViewIdListarLivro);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                EditarLivroFragment editar = new EditarLivroFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.frame_livro, editar).commit();
            }
        });

        return v;
    }
}