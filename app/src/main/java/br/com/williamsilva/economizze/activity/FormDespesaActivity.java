package br.com.williamsilva.economizze.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.controller.DespesaController;
import br.com.williamsilva.economizze.exception.NomeExistenteException;
import br.com.williamsilva.economizze.exception.SaldoInsuficienteException;
import br.com.williamsilva.economizze.factory.DespesaFactory;
import br.com.williamsilva.economizze.model.Despesa;
import br.com.williamsilva.economizze.model.dao.DespesaDAO;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormDespesaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private DespesaFactory despesaFactory;

    @Bind(R.id.valor)
    protected EditText valorDespesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_form_despesa);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(false);

        this.calendar = Calendar.getInstance();
        this.despesaFactory = new DespesaFactory(this);
        ButterKnife.bind(this);

        this.setTitle(R.string.adicionar_despesa);
        this.setTextVencimento();

        if (getIntent().getIntExtra("id_despesa", 0) > 0) {
            setTitle(R.string.alterar_despesa);
            Despesa despesa = new DespesaDAO(this)
                    .findDespesaById(getIntent()
                            .getExtras()
                            .getInt("id_despesa"));
            this.despesaFactory.bindDespesaActivity(despesa);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.button_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
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
            DespesaController controller = new DespesaController(this);
            controller.salvar(this.despesaFactory.getDespesa());
            this.finish();
        } catch (NomeExistenteException e) {
            Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG).show();
        }catch(SaldoInsuficienteException e){
            Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        this.calendar.set(Calendar.MONTH, monthOfYear);
        this.calendar.set(Calendar.YEAR, year);
        this.setTextVencimento();
    }

    private void setTextVencimento() {
        despesaFactory.bindVencimentoDespesaAcitvity(this.calendar.getTime());
    }

    @OnClick(R.id.data_vencimento)
    public void alterarData(View view) {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                FormDespesaActivity.this,
                this.calendar.get(Calendar.YEAR),
                this.calendar.get(Calendar.MONTH),
                this.calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
