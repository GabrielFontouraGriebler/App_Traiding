                                                                                                    package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

                                                                                                    public class Planejamento extends AppCompatActivity {

    private Button buttonPlanejamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento);

        this.buttonPlanejamento = (Button) findViewById(R.id.buttonAnotacao);

        this.buttonPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ele executa o metodo onClick a cima

                Intent intent = new Intent();
            }
        });

    }
}