package br.com.williamsilva.economizze.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.activity.FormDespesaActivity;
import br.com.williamsilva.economizze.adapter.DespesaAdapter;
import br.com.williamsilva.economizze.controller.DespesaController;
import br.com.williamsilva.economizze.model.Despesa;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DespesasFragment extends Fragment {


    @Bind(R.id.fab)
    protected FloatingActionButton fab;
    @Bind(R.id.list_despesas)
    protected RecyclerView recyclerView;

    public DespesasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_despesas, container, false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FormDespesaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        DespesaController controller = new DespesaController(getContext());
        List<Despesa> despesas = controller.listarDespesas(Calendar.getInstance());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new DespesaAdapter(getContext(), despesas));

    }
}
