package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Planejamento extends AppCompatActivity {

    private Button buttonPlanejamento;

//    private ListView ListViewPlanejamento;
    private List<PlanejamentoClass> planejamentoList;
//    private AdapterListaAnotacao adapterListaAnotacao;

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

    }
}