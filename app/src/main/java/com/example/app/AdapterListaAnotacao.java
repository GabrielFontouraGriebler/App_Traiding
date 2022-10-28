package com.example.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterListaAnotacao extends BaseAdapter {

    private Context context;
    private List<Anotacao> AnotacaoList;

    public AdapterListaAnotacao(Context context, List<Anotacao> anotacaoList) {
        this.context = context;
        this.AnotacaoList = anotacaoList;
    }

    @Override
    public int getCount() {
        return this.AnotacaoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.AnotacaoList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.layout_anotacao, null);

        TextView textViewDataAnotacao = (TextView) v.findViewById(R.id.textViewDataAnotacao);
        TextView textViewSaldoPosOp = (TextView) v.findViewById(R.id.textViewSaldoPosOp);

        textViewDataAnotacao.setText(String.valueOf(this.AnotacaoList.get(posicao).()))
        textViewSaldoPosOp.setText(String.valueOf(this.AnotacaoList.get(posicao).get));

        return null;
    }
}
