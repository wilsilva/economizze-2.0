package br.com.williamsilva.economizze.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.controller.ReceitaController;
import br.com.williamsilva.economizze.exception.CampoNuloException;
import br.com.williamsilva.economizze.exception.ErroPersistenciaException;
import br.com.williamsilva.economizze.exception.NomeExistenteException;
import br.com.williamsilva.economizze.factory.ReceitaFactory;
import br.com.williamsilva.economizze.model.dao.ReceitaDAO;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormReceitaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private ReceitaFactory receitaFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_receita);
        setTitle(R.string.adicionar_receita);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        ButterKnife.bind(this);

        receitaFactory = new ReceitaFactory(this);
        calendar = Calendar.getInstance();
        setTextRecebimento();

        if (getIntent().getIntExtra("id_receita", 0) > 0) {
            setTitle(R.string.alterar_receita);
            receitaFactory.bindReceitaActivity(new ReceitaDAO(this)
                    .findReceitaById(getIntent()
                            .getExtras()
                            .getInt("id_receita")));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);

        if (getIntent().getIntExtra("id_receita", 0) == 0)
            menu.removeItem(R.id.remover);

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
                this.salvarReceita();
                break;
            case R.id.remover:
                this.removerReceita();
                break;
        }

        return true;
    }

    private void removerReceita() {
        try {
            ReceitaController receitaController = new ReceitaController(this, new ReceitaFactory(this)
                    .getReceita());
            receitaController.remover();
        } catch (ErroPersistenciaException erro) {
            Snackbar.make(findViewById(android.R.id.content), "Desculpe, o sistema gerou um erro ao deletar a receita!", Snackbar.LENGTH_LONG)
                    .show();
            Log.e("Persistência de Dados", erro.getMessage());
        } finally {
            this.finish();
        }
    }

    private void salvarReceita() {

        try {
            ReceitaController receitaController = new ReceitaController(this, receitaFactory.getReceita());
            receitaController.salvar();
            this.finish();
        } catch (NomeExistenteException erro) {
            Snackbar.make(findViewById(android.R.id.content), erro.getMessage(), Snackbar.LENGTH_LONG).show();
        } catch (CampoNuloException erro) {
            Snackbar.make(findViewById(android.R.id.content), erro.getMessage(), Snackbar.LENGTH_LONG).show();
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
        receitaFactory.bindRecebimentoReceitaActivity(calendar.getTime());
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
