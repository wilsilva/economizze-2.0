package br.com.williamsilva.economizze.factory;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import br.com.williamsilva.economizze.model.Despesa;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by williamsilva on 16/11/2016.
 */

public class DespesaFactory {

    private Activity activity;

    @Bind(R.id.nome)
    protected EditText nomeDespesa;
    @Bind(R.id.valor)
    protected EditText valorDespesa;
    @Bind(R.id.cbx_despesa_fixa)
    protected CheckBox despesaFixa;
    @Bind(R.id.group_status)
    protected RadioGroup groupStatus;
    @Bind(R.id.data_vencimento)
    protected TextView vencimento;
    @Bind(R.id.despesa_a_pagar)
    protected RadioButton despesaNaoPaga;
    @Bind(R.id.despesa_paga)
    protected RadioButton despesaPaga;

    public DespesaFactory(Activity activity) {

        this.activity = activity;
        ButterKnife.bind(this,this.activity);
    }


    public Despesa getDespesa() {

        Despesa despesa = new Despesa();
        despesa.setId(this.activity.getIntent().getIntExtra("id_despesa", 0));
        despesa.setNome(nomeDespesa.getText().toString());
        despesa.setValor(Double.parseDouble(valorDespesa.getText().toString().replace(",",".")));
        despesa.setVencimento(RelogioHelper.parse(vencimento.getText().toString()));
        despesa.setDespesaFixa((despesaFixa.isChecked()) ? 1 : 0);
        despesa.setStatus((groupStatus.getCheckedRadioButtonId() == R.id.despesa_paga) ? 1 : 0);
        return despesa;
    }

    public void bindDespesaActivity(Despesa despesa) {

        nomeDespesa.setText(despesa.getNome());
        valorDespesa.setText(despesa.getValor().toString());
        vencimento.setText(new RelogioHelper(despesa.getVencimento()).dataPtBr());
        despesaFixa.setChecked((despesa.getDespesaFixa() > 0) ? true : false);
        despesaNaoPaga.setChecked((despesa.getStatus() == 0) ? true : false);
        despesaPaga.setChecked((despesa.getStatus() == 1) ? true : false);
    }

    public void bindVencimentoDespesaAcitvity(Date data) {
        vencimento.setText(new RelogioHelper(data).dataPtBr());
    }
}
