package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Relatorio extends AppCompatActivity {

    private Button buttonAnotacao;


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

    }
}