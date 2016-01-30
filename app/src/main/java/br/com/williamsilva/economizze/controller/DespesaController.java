package br.com.williamsilva.economizze.controller;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.dao.DespesaDAO;
import io.realm.RealmResults;

/**
 * Created by william on 30/01/16.
 */
public class DespesaController {

    private Context context;
    private Despesa despesa;

    public DespesaController(Context context) {
        this.context = context;
    }

    public DespesaController(Context context, Despesa despesa) {
        this.context = context;
        this.despesa = despesa;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public List<Despesa> listarDespesas() {
        DespesaDAO dao = new DespesaDAO(context);
        List<Despesa> despesas = dao.listDespesas();
        List<Despesa> despesasDoMes = new ArrayList<>();

        /**
         * Formata a data para comparar o mês e o ano atual
         * Formato: Mês/Ano ex: 01/2016
         */
        SimpleDateFormat fmt = new SimpleDateFormat("MM/yyyy");

        for (Despesa despesa : despesas) {

            if (fmt.format(despesa.getVencimento()).equals(fmt.format(Calendar.getInstance().getTime())))
                despesasDoMes.add(despesa);
        }

        return despesasDoMes;
    }

    public Double valorTotalDespesa() {
        List<Despesa> despesas = this.listarDespesas();
        Double totalDespesas = 0d;
        for (Despesa despesa : despesas) {
            totalDespesas += despesa.getValor();
        }
        return totalDespesas;
    }
}
