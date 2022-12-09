package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Planejamento extends AppCompatActivity {

    private Button buttonPlanejamento;

    private ListView ListViewPlanejamento;
    private List<PlanejamentoClass> planejamentoList;
    private AdapterListaPlanejamento adapterListaPlanejamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento);

        ConexaoSQLiteDiario conexaoSQLiteDiario = ConexaoSQLiteDiario.getInstancia(this);

        this.buttonPlanejamento = (Button) findViewById(R.id.buttonPlanejamento);

        this.buttonPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ele executa o metodo onClick a cima

                Intent intent = new Intent(Planejamento.this, PlanejamentoCadastro.class);
                startActivity(intent);
            }
        });

        AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(Planejamento.this));
        planejamentoList = anotacaoCtrl.getPlanejamentoCtrl();

        this.ListViewPlanejamento = (ListView) findViewById(R.id.ListViewPlanejamento);

        this.adapterListaPlanejamento = new AdapterListaPlanejamento(Planejamento.this, this.planejamentoList);

        this.ListViewPlanejamento.setAdapter(this.adapterListaPlanejamento);

        this.ListViewPlanejamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                final PlanejamentoClass planejamentoSelecionado = (PlanejamentoClass) adapterListaPlanejamento.getItem(posicao);

                Toast.makeText(Planejamento.this, "Planejamento" + planejamentoSelecionado.getDataInicial(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(Planejamento.this);

                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("O que deseja fazer com o planejamento selecionado");

                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dialogInterface.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        boolean excluiu = anotacaoCtrl.excluirPlanejamentoCtrl(planejamentoSelecionado.getIdPlanejamento());

                        dialogInterface.cancel();

                        if (excluiu == true){

                            adapterListaPlanejamento.removerPlanejamento(posicao);

                            Toast.makeText(Planejamento.this, "Planejamento excluido com sucesso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Planejamento.this, "Erro ao excluir Planejamento", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                janelaDeEscolha.setPositiveButton("Detalhes", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        Bundle bundleDadosPlanejamento = new Bundle();
////
////                        bundleDadosPlanejamento.putInt("data_inicial", planejamentoSelecionado.getDataInicial());
////                        bundleDadosPlanejamento.putInt("data_final", planejamentoSelecionado.getDataFinal());
////                        bundleDadosPlanejamento.putDouble("saldo", planejamentoSelecionado.getSaldo());
////                        bundleDadosPlanejamento.putDouble("porcentagem", planejamentoSelecionado.getPorcentagem());
////                        bundleDadosPlanejamento.putDouble("saldo_perda", planejamentoSelecionado.getPerda());
////                        bundleDadosPlanejamento.putDouble("saldo_ganho", planejamentoSelecionado.getGanho());
////
////                        Intent intentEditarPlanejamento = new Intent(Planejamento.this, PlanejamentoEditar.class);
////                        intentEditarPlanejamento.putExtras(bundleDadosPlanejamento);
////                        startActivity(intentEditarPlanejamento);
////                    }
////                });

                janelaDeEscolha.create().show();

            }
        });

    }

}