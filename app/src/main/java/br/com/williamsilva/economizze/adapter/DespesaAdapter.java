package br.com.williamsilva.economizze.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.activity.FormDespesaActivity;
import br.com.williamsilva.economizze.model.Despesa;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by william on 30/01/16.
 */
public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.DespesaViewHolder> {

    private List<Despesa> despesas;
    private LayoutInflater inflater;

    public DespesaAdapter(Context context, List<Despesa> despesas) {
        this.despesas = despesas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DespesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item_despesa, parent, false);

        return new DespesaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DespesaViewHolder holder, int position) {

        Despesa despesa = despesas.get(position);
        holder.nomeDespesa.setText(despesa.getNome());
        holder.valorDespesa.setText("R$ " + despesa.getValor().toString().replace(".", ","));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        holder.vencimentoDespesa.setText(format.format(despesa.getVencimento()));
        holder.siglaDespesa.setText(despesa.getNome().substring(0, 2).toUpperCase());

    }

    @Override
    public int getItemCount() {
        return despesas.size();
    }

    public class DespesaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.sigla_despesa)
        public TextView siglaDespesa;
        @Bind(R.id.nome_despesa)
        public TextView nomeDespesa;
        @Bind(R.id.valor_despesa)
        public TextView valorDespesa;
        @Bind(R.id.vencimento_despesa)
        public TextView vencimentoDespesa;

        public DespesaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this.itemView.getContext(), FormDespesaActivity.class);
            intent.putExtra("id_despesa",despesas.get(getPosition()).getId());
            itemView.getContext().startActivity(intent);
        }
    }
}
