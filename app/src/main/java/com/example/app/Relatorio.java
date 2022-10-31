package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Relatorio extends AppCompatActivity {

    private Button buttonAnotacao;

    private ListView ListViewAnotacoes;
    private List<AnotacaoDiario> anotacaoList;
    private AdapterListaAnotacao adapterListaAnotacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        ConexaoSQLiteDiario conexaoSQLiteDiario = ConexaoSQLiteDiario.getInstancia(this);

        this.buttonAnotacao = (Button) findViewById(R.id.buttonAnotacao);

        this.buttonAnotacao.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ele executa o metodo onClick a cima

                Intent intent = new Intent(Relatorio.this, Anotacao.class);
                startActivity(intent);
            }
        });

        AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(Relatorio.this));
        anotacaoList = anotacaoCtrl.getListaAnotacaoCtrl();




        this.ListViewAnotacoes = (ListView) findViewById(R.id.ListViewAnotacoes);

        this.adapterListaAnotacao = new AdapterListaAnotacao(Relatorio.this, this.anotacaoList);

        this.ListViewAnotacoes.setAdapter(this.adapterListaAnotacao);

        this.ListViewAnotacoes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                final AnotacaoDiario anotacaoSelecionada = (AnotacaoDiario) adapterListaAnotacao.getItem(posicao);

                Toast.makeText(Relatorio.this, "Produto" + anotacaoSelecionada.getData(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(Relatorio.this);

                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("O que deseja fazer com a notificação selecionada");

                janelaDeEscolha.setNeutralButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dialogInterface.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                        boolean excluiu = anotacaoCtrl.excluirAnotacaoCtrl(anotacaoSelecionada.getData());

                        dialogInterface.cancel();

                        if(excluiu == true){
                            Toast.makeText(Relatorio.this, "Produto excluido com sucesso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Relatorio.this, "Erro ao excluir produto", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                janelaDeEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                janelaDeEscolha.create().show();

                return false;
            }
        });

    }


}