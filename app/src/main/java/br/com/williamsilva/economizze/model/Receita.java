package br.com.williamsilva.economizze.model;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by william on 27/01/16.
 */
public class Receita extends RealmObject {

    @PrimaryKey
    private Integer id;
    @Required
    private String nome;
    @Required
    private Date dataRecebimento;
    @Required
    private Double valor;
    @Required
    private Integer receitaFixa;


    public Receita(Integer id, String nome, Date dataRecebimento, Double valor, Integer receitaFixa) {
        this.id = id;
        this.nome = nome;
        this.dataRecebimento = dataRecebimento;
        this.valor = valor;
        this.receitaFixa = receitaFixa;
    }

    public Receita() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getReceitaFixa() {
        return receitaFixa;
    }

    public void setReceitaFixa(Integer receitaFixa) {
        this.receitaFixa = receitaFixa;
    }

}
