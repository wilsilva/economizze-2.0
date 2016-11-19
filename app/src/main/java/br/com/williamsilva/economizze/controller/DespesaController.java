package br.com.williamsilva.economizze.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import br.com.williamsilva.economizze.exception.ErroPersistenciaException;
import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.dao.DespesaDAO;

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

    public List<Despesa> listarDespesas(Calendar calendar) {

        DespesaDAO dao = new DespesaDAO(context);
        List<Despesa> despesas = dao.listDespesas();
        List<Despesa> despesasDoMes = new ArrayList<>();

        for (Despesa despesa : despesas) {

            if (new RelogioHelper(despesa.getVencimento()).possuiMesmoMesAno(calendar.getTime()))
                despesasDoMes.add(despesa);
        }

        return despesasDoMes;
    }


    public Double totalDespesas(Calendar calendar) {

        List<Despesa> despesas = this.listarDespesas(calendar);
        Double totalDespesas = 0d;

        for (Despesa despesa : despesas) {
            totalDespesas += despesa.getValor();
        }

        return totalDespesas;
    }

    public Double totalDespesasPagas(Calendar calendar) {

        List<Despesa> despesas = this.listarDespesas(calendar);
        Double totalDespesasPagas = 0d;

        for (Despesa despesa : despesas) {
            if (despesa.getStatus() == 1) {
                totalDespesasPagas += despesa.getValor();
            }
        }

        return totalDespesasPagas;
    }

    public Double totalDespesasNaoPagas(Calendar calendar) {

        List<Despesa> despesas = this.listarDespesas(calendar);
        Double totalDespesasPagas = 0d;

        for (Despesa despesa : despesas) {
            if (despesa.getStatus() == 0) {
                totalDespesasPagas += despesa.getValor();
            }
        }

        return totalDespesasPagas;
    }

    public void salvar(Despesa despesa) {

        DespesaDAO dao = new DespesaDAO(this.context, despesa);
        dao.insertOrUpdate();
    }

    public void remover() throws ErroPersistenciaException{
        DespesaDAO despesaDAO = new DespesaDAO(this.context,this.despesa);
        despesaDAO.delete();
    }
}
