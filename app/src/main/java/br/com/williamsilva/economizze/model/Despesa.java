package br.com.williamsilva.economizze.model;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by william on 27/01/16.
 */
public class Despesa extends RealmObject{

    @PrimaryKey
    private Integer id;
    private String nome;
    private Double valor;
    private Date vencimento;
    private int status;
    private int despesaFixa;


    public Despesa(Integer id, String nome, Double valor, Date vencimento, int status, int despesaFixa) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.vencimento = vencimento;
        this.status = status;
        this.despesaFixa = despesaFixa;
    }

    public Despesa(){}

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDespesaFixa() {
        return despesaFixa;
    }

    public void setDespesaFixa(int despesaFixa) {
        this.despesaFixa = despesaFixa;
    }

}
