package br.com.williamsilva.economizze.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.williamsilva.economizze.R;
import br.com.williamsilva.economizze.activity.FormReceitaActivity;
import br.com.williamsilva.economizze.controller.helper.MoneyHelper;
import br.com.williamsilva.economizze.controller.helper.NameFinanceHelper;
import br.com.williamsilva.economizze.controller.helper.RelogioHelper;
import br.com.williamsilva.economizze.model.Receita;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by william on 30/01/16.
 */
public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder> {


    private List<Receita> receitas;
    private LayoutInflater inflater;

    public ReceitaAdapter(Context context, List<Receita> receitas) {
        this.receitas = receitas;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ReceitaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReceitaViewHolder(inflater.inflate(R.layout.list_item_receita, parent, false));
    }

    @Override
    public void onBindViewHolder(ReceitaViewHolder holder, int position) {
        Receita receita = receitas.get(position);
        holder.nomeReceita.setText(receita.getNome());
        holder.siglaReceita.setText(NameFinanceHelper.getInstance().gerarIniciais(receita.getNome()));
        holder.valorReceita.setText(MoneyHelper.getInstance().convert(receita.getValor()));
        holder.recebimentoReceita.setText(new RelogioHelper(receita.getDataRecebimento()).dataPtBr());
    }

    @Override
    public int getItemCount() {
        return receitas.size();
    }

    public class ReceitaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.nome_receita)
        public TextView nomeReceita;
        @Bind(R.id.sigla_receita)
        public TextView siglaReceita;
        @Bind(R.id.valor_receita)
        public TextView valorReceita;
        @Bind(R.id.recebimento_receita)
        public TextView recebimentoReceita;

        public ReceitaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(), FormReceitaActivity.class);
            intent.putExtra("id_receita",receitas.get(getPosition()).getId());
            itemView.getContext().startActivity(intent);
        }
    }
}
