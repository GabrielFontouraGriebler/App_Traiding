package com.example.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterListaPlanejamento extends BaseAdapter {

    private Context context;
    private List<PlanejamentoClass> PlanejamentoList;

    public AdapterListaPlanejamento(Context context, List<PlanejamentoClass> planejamentoList){
        this.context = context;
        this.PlanejamentoList = planejamentoList;
    }


    @Override
    public int getCount() {
        return this.PlanejamentoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.PlanejamentoList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public void removerPlanejamento(int posicao){
        this.PlanejamentoList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.layout_planejamento, null);

        TextView textViewDataInicialPlan = (TextView) v.findViewById(R.id.textViewDataInicialPlan);
        TextView textViewDataFinalPlan = (TextView) v.findViewById(R.id.textViewDataFinalPlan);
        TextView textViewSaldoPlan = (TextView) v.findViewById(R.id.textViewSaldoPlan);
        TextView textViewPorceentagemPlan = (TextView) v.findViewById(R.id.textViewPorceentagemPlan);
        TextView textViewPerdaPlan = (TextView) v.findViewById(R.id.textViewPerdaPlan);
        TextView textViewGanhoPlan = (TextView) v.findViewById(R.id.textViewGanhoPlan);

        textViewDataInicialPlan.setText(String.valueOf(this.PlanejamentoList.get(posicao).getDataInicial()));
        textViewDataFinalPlan.setText(String.valueOf(this.PlanejamentoList.get(posicao).getDataFinal()));
        textViewSaldoPlan.setText(String.valueOf(this.PlanejamentoList.get(posicao).getSaldo()));
        textViewPorceentagemPlan.setText(String.valueOf(this.PlanejamentoList.get(posicao).getPorcentagem()));
        textViewPerdaPlan.setText(String.valueOf(this.PlanejamentoList.get(posicao).getPerda()));
        textViewGanhoPlan.setText(String.valueOf(this.PlanejamentoList.get(posicao).getGanho()));

        return v;
    }
}
