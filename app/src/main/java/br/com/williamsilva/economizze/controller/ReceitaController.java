package br.com.williamsilva.economizze.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import br.com.williamsilva.economizze.exception.ErroPersistenciaException;
import br.com.williamsilva.economizze.exception.CampoNuloException;
import br.com.williamsilva.economizze.exception.NomeExistenteException;
import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.Receita;
import br.com.williamsilva.economizze.model.dao.ReceitaDAO;

/**
 * Created by william on 30/01/16.
 */
public class ReceitaController {

    private Context context;
    private Receita receita;

    public ReceitaController(Context context) {
        this.context = context;
    }

    public boolean camposPreenchidos() {
        if(this.receita.getNome().isEmpty() || this.receita.getDataRecebimento() instanceof Date == false || this.receita.getValor() < 1)  return false;

        return true;
    }

    public ReceitaController(Context context, Receita receita) {
        this.context = context;
        this.receita = receita;
    }

    public List<Receita> listarReceitas(Calendar calendar) {

        ReceitaDAO dao = new ReceitaDAO(context);
        List<Receita> receitas = dao.listReceitas();
        List<Receita> receitasDoMes = new ArrayList<>();

        for (Receita receita : receitas) {

            if (new RelogioHelper(receita.getDataRecebimento()).possuiMesmoMesAno(calendar.getTime()))
                receitasDoMes.add(receita);
        }

        return receitasDoMes;
    }

    public Double totalReceitas(Calendar calendar) {

        Double totalReceita = 0d;

        List<Receita> receitas = this.listarReceitas(calendar);

        for (Receita receita : receitas) {
            totalReceita += receita.getValor();
        }

        return totalReceita;
    }

    public Double saldoAltual(Calendar calendar) {

        DespesaController despesaController = new DespesaController(this.context);
        Double totalDespesaPaga = despesaController.totalDespesasPagas(calendar);
        Double totalReceita = this.totalReceitas(calendar);
        return totalReceita - totalDespesaPaga;
    }

    public void remover() throws ErroPersistenciaException {
        ReceitaDAO receitaDAO = new ReceitaDAO(this.context, this.receita);
        receitaDAO.delete();
    }

    public void salvar() throws NomeExistenteException {

        if (this.camposPreenchidos()) {
            ReceitaDAO dao = new ReceitaDAO(this.context, this.receita);
            dao.insertOrUpdate();
        } else {
            throw new CampoNuloException("Favor preencher todos os campos");
        }
    }

    public boolean possuiSaldoDisponivel(Despesa despesa) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(despesa.getVencimento());

        if(saldoAltual(calendar) - despesa.getValor() < 0) return false;

        return  true;
    }
}
