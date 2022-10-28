package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Relatorio extends AppCompatActivity {

    private Button buttonAnotacao;

    private ListView ListViewAnotacoes;
    private List<AnotacaoDiario> anotacaoLista;
    private AdapterListaAnotacao adapterListaAnotacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        ConexaoSQLiteDiario conexaoSQLiteDiario = ConexaoSQLiteDiario.getInstancia(this);

        this.buttonAnotacao = (Button) findViewById(R.id.buttonAnotacao);

        this.buttonAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ele executa o metodo onClick a cima

                Intent intent = new Intent(Relatorio.this, Anotacao.class);
                startActivity(intent);
            }
        });

        AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(Relatorio.this));
        anotacaoLista = anotacaoCtrl.getListaAnotacaoCtrl();

        this.ListViewAnotacoes = (ListView) findViewById(R.id.ListViewAnotacoes);    

        this.adapterListaAnotacao = new AdapterListaAnotacao(Relatorio.this, anotacaoLista);

        this.ListViewAnotacoes.setAdapter(this.adapterListaAnotacao);

    }
}