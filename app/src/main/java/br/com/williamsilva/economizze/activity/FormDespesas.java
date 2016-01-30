package br.com.williamsilva.economizze.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.williamsilva.economizze.R;

public class FormDespesas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_despesas);
        this.setTitle("Adicionar Despesa");
    }
}
