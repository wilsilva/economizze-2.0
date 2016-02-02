package br.com.williamsilva.economizze.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.dao.DespesaDAO;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormDespesaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_despesa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        this.setTitle("Adicionar Despesa");
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        setTextVencimento();


        if(getIntent().getIntExtra("id_despesa",0) > 0)
        {
            Despesa despesa = null;
            despesa = new DespesaDAO(this).findDespesaById(getIntent().getExtras().getInt("id_despesa"));
            nomeDespesa.setText(despesa.getNome());
            valorDespesa.setText(despesa.getValor().toString());
            vencimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(despesa.getVencimento()));
            despesaFixa.setChecked((despesa.getDespesaFixa() > 0) ? true : false);
            despesaNaoPaga.setChecked((despesa.getStatus() == 0) ? true : false);
            despesaPaga.setChecked((despesa.getStatus() == 1) ? true : false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.button_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.add:
                salvarDespesa();
                break;
        }

        return true;
    }

    private void salvarDespesa() {
        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Despesa despesa = new Despesa();
            despesa.setId(getIntent().getIntExtra("id_despesa",0));
            despesa.setNome(nomeDespesa.getText().toString());
            despesa.setValor(Double.parseDouble(valorDespesa.getText().toString()));
            despesa.setVencimento(format.parse(vencimento.getText().toString()));
            despesa.setDespesaFixa((despesaFixa.isChecked()) ? 1 : 0);
            despesa.setStatus((groupStatus.getCheckedRadioButtonId() == R.id.despesa_paga) ? 1 : 0);

            DespesaDAO dao = new DespesaDAO(this,despesa);
            dao.insertOrUpdate();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.YEAR, year);
        setTextVencimento();
    }

    private void setTextVencimento() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        vencimento.setText(format.format(calendar.getTime()));
    }

    @OnClick(R.id.data_vencimento)
    public void alterarData(View view) {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FormDespesaActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
