package br.com.williamsilva.economizze.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.dao.DespesaDAO;
import io.realm.Realm;


public class PrincipalFragment extends Fragment {

    public PrincipalFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

//        Despesa despesa = new Despesa();
//        despesa.setDespesaFixa(0);
//        despesa.setNome("Casas Bahia");
//        despesa.setStatus(0);
//        despesa.setValor(400d);
//
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        Date data = null;
//        try {
//            data = formato.parse("10/01/2016");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        despesa.setVencimento(data);
//
//        DespesaDAO dao = new DespesaDAO(getContext(),despesa);
//        dao.insertOrUpdate();


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

}