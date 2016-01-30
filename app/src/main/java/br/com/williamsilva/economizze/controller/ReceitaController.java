package br.com.williamsilva.economizze.controller;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public ReceitaController(Context context, Receita receita) {
        this.context = context;
        this.receita = receita;
    }

    public List<Receita> listarReceitas() {

        ReceitaDAO dao = new ReceitaDAO(context);
        List<Receita> receitas = dao.listReceitas();
        List<Receita> receitasDoMes = new ArrayList<>();

        /**
         * Formata a data para comparar o mês e o ano atual
         * Formato: Mês/Ano ex: 01/2016
         */
        SimpleDateFormat fmt = new SimpleDateFormat("MM/yyyy");

        for (Receita receita : receitas) {

            if (fmt.format(receita.getDataRecebimento()).equals(fmt.format(Calendar.getInstance().getTime())))
                receitasDoMes.add(receita);
        }

        return receitasDoMes;
    }

    public Double valorTotalReceita() {
        Double totalReceita = 0d;
        List<Receita> receitas = this.listarReceitas();

        for (Receita receita : receitas) {
            totalReceita += receita.getValor();
        }

        return totalReceita;
    }
}
