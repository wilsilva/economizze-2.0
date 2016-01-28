package br.com.williamsilva.economizze.model.dao;

import android.content.Context;

import br.com.williamsilva.economizze.model.Despesa;
import io.realm.Realm;
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

    public void insertOrUpdate() {
        Realm realm = Realm.getInstance(this.context);

        try {
            if (this.despesa.getId().equals(null) || this.despesa.getId().equals(0)) {
                this.despesa.setId(this.autoIncrementForId());
            }

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(this.despesa);
            realm.commitTransaction();
        } catch (RealmException erro) {
            erro.printStackTrace();
        } finally {
            realm.close();
        }
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

    public RealmResults<Despesa> listDespesas() {

        Realm realm = Realm.getInstance(context);
        RealmResults<Despesa> despesas = null;

        try {
            despesas = realm.where(Despesa.class).findAll();
            despesas.sort("nome");
        } catch (Exception erro) {
            erro.printStackTrace();
        } finally {
            realm.close();
        }

        return despesas;
    }

    public Despesa findDespesaById(int id) {

        RealmResults<Despesa> despesas = this.listDespesas();
        return despesas.where().equalTo("id", id).findFirst();
    }
}
