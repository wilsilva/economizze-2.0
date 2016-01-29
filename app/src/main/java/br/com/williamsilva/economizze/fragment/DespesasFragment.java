package br.com.williamsilva.economizze.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.activity.FormDespesas;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DespesasFragment extends Fragment {


    @Bind(R.id.fab)
    protected FloatingActionButton fab;
    public DespesasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_despesas, container, false);
        ButterKnife.bind(this,view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FormDespesas.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
