package br.com.williamsilva.economizze.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.controller.DespesaController;
import br.com.williamsilva.economizze.controller.ReceitaController;
import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import butterknife.Bind;
import butterknife.ButterKnife;


public class PrincipalFragment extends Fragment {

    @Bind(R.id.valor_receita)
    protected TextView valorReceita;

    @Bind(R.id.valor_despesa)
    protected TextView valorDespesa;

    @Bind(R.id.visao_geral)
    protected TextView visaoGeral;

    public PrincipalFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ButterKnife.bind(this, view);

        visaoGeral.setText(getString(R.string.visao_geral) + " - " + new RelogioHelper(Calendar.getInstance().getTime()).MesPorExtenso());
        DespesaController despesaController = new DespesaController(getContext());
        ReceitaController receitaController = new ReceitaController(getContext());
        valorDespesa.setText("R$ " + despesaController.totalDespesas(Calendar.getInstance()).toString().replace(".", ","));
        valorReceita.setText("R$ " + receitaController.totalReceitas(Calendar.getInstance()).toString().replace(".", ","));

        return view;
    }

}