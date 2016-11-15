package br.com.williamsilva.economizze.model.dao;

import android.content.Context;
import android.util.Log;

import java.util.List;

import br.com.williamsilva.economizze.exception.NomeExistenteException;
import br.com.williamsilva.economizze.model.Despesa;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;

/**
 * Created by william on 28/01/16.
 */
public class DespesaDAO {

    private Despesa despesa;
    private Context context;

    public DespesaDAO(Context context, Despesa despesa) {
        this.despesa = despesa;
        this.context = context;

    }

    public DespesaDAO(Context context) {
        this.context = context;
    }

    private int autoIncrementForId() {

        Realm realm = Realm.getInstance(this.context);
        int id = 0;

        try {

            RealmResults<Despesa> results = realm.where(Despesa.class).findAll();

            if (results.size() == 0) {
                return 1;
            }

            results.sort("id", Sort.DESCENDING);
            Despesa despesa = results.get(0);
            id = despesa.getId() + 1;

        } catch (RealmException erro) {
            erro.printStackTrace();
        } finally {
            realm.close();
        }

        return id;
    }

    public void insertOrUpdate() throws NomeExistenteException {
        Realm realm = Realm.getInstance(this.context);

        try {

            if (!this.nomeJaFoiCadastrado(this.despesa.getId())) {
                if (this.despesa.getId() == null || this.despesa.getId().equals(0)) {
                    this.despesa.setId(this.autoIncrementForId());
                }

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(this.despesa);
                realm.commitTransaction();
            } else {
                throw new NomeExistenteException("Já existe este nome cadastrado!");
            }
        } catch (RealmException erro) {
            erro.printStackTrace();
            Log.e("Erro Cadastro Despesa", erro.getMessage());

        } finally {
            realm.close();
        }
    }

    private boolean nomeJaFoiCadastrado(Integer id) {
        Realm realm = Realm.getInstance(this.context);
        RealmResults<Despesa> despesas = realm.where(Despesa.class).findAll();
        RealmQuery<Despesa> query = despesas.where().equalTo("nome", this.despesa.getNome());

        if (query.count() > 0)
            if (!query.findFirst().getId().equals(id))
                return true;

        return false;
    }

    public void delete() {
        Realm realm = Realm.getInstance(this.context);
        try {
            realm.beginTransaction();
            despesa.removeFromRealm();
            realm.commitTransaction();
        } catch (RealmException erro) {
            erro.printStackTrace();
        } finally {
            realm.close();
        }
    }

    public List<Despesa> listDespesas() {

        Realm realm = Realm.getInstance(context);
        RealmResults<Despesa> despesas = null;

        try {
            despesas = realm.where(Despesa.class).findAll();
            despesas.sort("nome");
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return despesas;
    }

    public Despesa findDespesaById(int id) {

        Realm realm = Realm.getInstance(context);
        Despesa despesa = null;

        try {
            RealmResults<Despesa> despesas = realm.where(Despesa.class).findAll();
            despesa = despesas.where().equalTo("id", id).findFirst();
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return despesa;
    }
}
