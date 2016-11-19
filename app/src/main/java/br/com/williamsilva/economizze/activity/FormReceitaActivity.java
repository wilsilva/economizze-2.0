package br.com.williamsilva.economizze.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.model.Receita;
import br.com.williamsilva.economizze.model.dao.ReceitaDAO;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormReceitaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private SimpleDateFormat format;

    @Bind(R.id.data_recebimento)
    protected TextView recebimento;
    @Bind(R.id.nome)
    protected EditText nome;
    @Bind(R.id.valor)
    protected EditText valor;
    @Bind(R.id.cbx_receita_fixa)
    protected CheckBox receitaFixa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_receita);
        setTitle(R.string.adicionar_receita);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        ButterKnife.bind(this);
        format = new SimpleDateFormat("dd/MM/yyyy");
        calendar = Calendar.getInstance();
        setTextRecebimento();

        if (getIntent().getIntExtra("id_receita", 0) > 0) {
            setTitle(R.string.alterar_receita);
            Receita receita = new ReceitaDAO(this).findReceitaById(getIntent().getExtras().getInt("id_receita"));
            recebimento.setText(format.format(receita.getDataRecebimento()));
            nome.setText(receita.getNome());
            valor.setText(receita.getValor().toString());
            receitaFixa.setChecked((receita.getReceitaFixa() > 0) ? true : false);
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
                salvarReceita();
                finish();
                break;
        }

        return true;
    }

    private void salvarReceita() {

        try {
            Receita receita = new Receita();
            receita.setId(getIntent().getIntExtra("id_receita", 0));
            receita.setNome(nome.getText().toString());
            receita.setValor(Double.parseDouble(valor.getText().toString().replace(",", ".")));
            receita.setDataRecebimento(format.parse(recebimento.getText().toString()));
            receita.setReceitaFixa((receitaFixa.isChecked()) ? 1 : 0);

            ReceitaDAO dao = new ReceitaDAO(this, receita);
            dao.insertOrUpdate();

        } catch (Exception erro) {
            erro.printStackTrace();
        }

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.YEAR, year);
        setTextRecebimento();
    }

    private void setTextRecebimento() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        recebimento.setText(format.format(calendar.getTime()));
    }

    @OnClick(R.id.data_recebimento)
    public void alterarData(View view) {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FormReceitaActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
