package br.com.williamsilva.economizze.factory;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import br.com.williamsilva.economizze.model.Receita;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by williamsilva on 19/11/2016.
 */

public class ReceitaFactory {

    private Activity activity;

    @Bind(R.id.data_recebimento)
    protected TextView recebimento;
    @Bind(R.id.nome)
    protected EditText nome;
    @Bind(R.id.valor)
    protected EditText valor;
    @Bind(R.id.cbx_receita_fixa)
    protected CheckBox receitaFixa;

    public ReceitaFactory(Activity activity) {
        this.activity = activity;
        ButterKnife.bind(this, this.activity);
    }

    public Receita getReceita() {
        Receita receita = new Receita();
        receita.setId(this.activity.getIntent().getIntExtra("id_receita", 0));
        receita.setNome(nome.getText().toString());
        receita.setValor((valor.getText().toString().isEmpty()) ? 0d : Double.parseDouble(valor.getText().toString().replace(",", ".")));
        receita.setDataRecebimento(RelogioHelper.parse(recebimento.getText().toString()));
        receita.setReceitaFixa((receitaFixa.isChecked()) ? 1 : 0);
        return receita;
    }

    public void bindReceitaActivity(Receita receita) {
        recebimento.setText(new RelogioHelper(receita.getDataRecebimento()).dataPtBr());
        nome.setText(receita.getNome());
        valor.setText(receita.getValor().toString());
        receitaFixa.setChecked((receita.getReceitaFixa() > 0) ? true : false);
    }

    public void bindRecebimentoReceitaActivity(Date data) {
        recebimento.setText(new RelogioHelper(data).dataPtBr());
    }
}
