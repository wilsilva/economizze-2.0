package br.com.williamsilva.economizze.model.dao;

import android.content.Context;

import java.util.List;

import br.com.williamsilva.economizze.exception.ErroPersistenciaException;
import br.com.williamsilva.economizze.model.Receita;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;

/**
 * Created by william on 30/01/16.
 */
public class ReceitaDAO {
    private Receita receita;
    private Context context;

    public ReceitaDAO(Context context, Receita receita) {
        this.receita = receita;
        this.context = context;
    }

    public ReceitaDAO(Context context) {
        this.context = context;
    }

    private int autoIncrementForId() {
        Realm realm = Realm.getInstance(this.context);
        int id = 0;
        try {

            RealmResults<Receita> results = realm.where(Receita.class).findAll();

            if (results.size() == 0) {
                return 1;
            }

            results.sort("id", Sort.DESCENDING);
            Receita receita = results.get(0);
            id = receita.getId() + 1;

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
            if (this.receita.getId().equals(null) || this.receita.getId().equals(0)) {
                this.receita.setId(this.autoIncrementForId());
            }

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(this.receita);
            realm.commitTransaction();
        } catch (RealmException erro) {
            erro.printStackTrace();
        } finally {
            realm.close();
        }
    }

    public void delete() throws ErroPersistenciaException {
        Realm realm = Realm.getInstance(this.context);
        try {
            receita = this.findReceitaById(receita.getId());
            realm.beginTransaction();
            receita.removeFromRealm();
            realm.commitTransaction();
        } catch (RealmException erro) {
            throw new ErroPersistenciaException(erro.getMessage());
        } finally {
            realm.close();
        }
    }

    public List<Receita> listReceitas() {

        Realm realm = Realm.getInstance(context);
        RealmResults<Receita> receitas = null;
        try {
            receitas = realm.where(Receita.class).findAll();
            receitas.sort("nome");
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return receitas;
    }

    public Receita findReceitaById(int id) {
        Realm realm = Realm.getInstance(context);
        Receita receita = null;

        try {
            RealmResults<Receita> despesas = realm.where(Receita.class).findAll();
            receita = despesas.where().equalTo("id", id).findFirst();
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return receita;
    }
}
