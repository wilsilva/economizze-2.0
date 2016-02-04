package br.com.williamsilva.economizze.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.activity.FormReceitaActivity;
import br.com.williamsilva.economizze.adapter.ReceitaAdapter;
import br.com.williamsilva.economizze.controller.ReceitaController;
import br.com.williamsilva.economizze.model.Receita;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceitasFragment extends Fragment {

    @Bind(R.id.fab)
    protected FloatingActionButton fab;
    @Bind(R.id.list_receitas)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receitas, container, false);
        ButterKnife.bind(this, view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FormReceitaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ReceitaController controller = new ReceitaController(getContext());
        List<Receita> receitas = controller.listarReceitas();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new ReceitaAdapter(getContext(), receitas));
    }
}
