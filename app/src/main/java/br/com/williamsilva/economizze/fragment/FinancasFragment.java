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
import br.com.williamsilva.economizze.controller.helper.MoneyHelper;
import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinancasFragment extends Fragment {

    @Bind(R.id.total_despesas)
    protected TextView totalDespesas;

    @Bind(R.id.total_receitas)
    protected TextView totalReceitas;

    @Bind(R.id.despesas_nao_paga)
    protected TextView despesasNaoPagas;

    @Bind(R.id.despesas_paga)
    protected TextView despesasPagas;

    @Bind(R.id.saldo_atual)
    protected TextView saldoAtual;

    @Bind(R.id.mes)
    protected TextView mes;

    private Calendar calendar;

    public FinancasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financas, container, false);
        ButterKnife.bind(this, view);

        this.calendar = Calendar.getInstance();
        carregarFinancas();
        return view;
    }

    private void carregarFinancas() {
        DespesaController despesaController = new DespesaController(getContext());
        ReceitaController receitaController = new ReceitaController(getContext());

        this.mes.setText(new RelogioHelper(calendar.getTime()).MesPorExtenso());
        this.totalDespesas.setText(MoneyHelper.getInstance().convert(despesaController.totalDespesas(calendar)));
        this.saldoAtual.setText(MoneyHelper.getInstance().convert(receitaController.saldoAltual(calendar)));
        this.totalReceitas.setText(MoneyHelper.getInstance().convert(receitaController.totalReceitas(calendar)));
        this.despesasNaoPagas.setText(MoneyHelper.getInstance().convert(despesaController.totalDespesasNaoPagas(calendar)));
        this.despesasPagas.setText(MoneyHelper.getInstance().convert(despesaController.totalDespesasPagas(calendar)));
    }

    @OnClick(R.id.voltar)
    protected void voltarMes(View v){
        int mes = calendar.get(Calendar.MONTH);

        if(mes > 0) calendar.set(Calendar.MONTH,(mes - 1));

        this.carregarFinancas();
    }

    @OnClick(R.id.avancar)
    protected void avancarMes(View v){
        int mes = calendar.get(Calendar.MONTH);

        if(mes < 11) calendar.set(Calendar.MONTH,(mes + 1));

        this.carregarFinancas();

    }

}
