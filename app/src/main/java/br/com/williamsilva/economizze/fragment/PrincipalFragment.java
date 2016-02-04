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
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.controller.DespesaController;
import br.com.williamsilva.economizze.controller.ReceitaController;
import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.dao.DespesaDAO;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;


public class PrincipalFragment extends Fragment {

    @Bind(R.id.valor_receita)
    protected TextView valorReceita;

    @Bind(R.id.valor_despesa)
    protected TextView valorDespesa;

    @Bind(R.id.visao_geral)
    protected TextView visaoGeral;

    public PrincipalFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ButterKnife.bind(this,view);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MMMM",new Locale("pt","BR"));
        String mes = dateFormat.format(calendar.getTime());
        visaoGeral.setText(getString(R.string.visao_geral) + " - " + mes.substring(0,1).toUpperCase() + mes.substring(1));
        DespesaController despesaController = new DespesaController(getContext());
        ReceitaController receitaController = new ReceitaController(getContext());
        valorDespesa.setText("R$ " + despesaController.valorTotalDespesa().toString().replace(".", ","));
        valorReceita.setText("R$ " + receitaController.valorTotalReceita().toString().replace(".", ","));

        return view;
    }

}