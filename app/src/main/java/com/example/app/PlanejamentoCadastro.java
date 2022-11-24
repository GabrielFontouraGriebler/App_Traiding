package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlanejamentoCadastro extends AppCompatActivity {

    private EditText editTextDataInicial;
    private EditText editTextDataFinal;
    private EditText editTextSaldo;
    private EditText editTextPorcentagem;
    private EditText editTextSaldoPerda;
    private EditText editTextSaldoGanho;

    private Button buttonSalvarPlanejamento;

    private PlanejamentoClass planejamentoClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento_cadastro);

        editTextDataInicial = (EditText) findViewById(R.id.editTextDataInicial);
        editTextDataFinal = (EditText) findViewById(R.id.editTextDataFinal);
        editTextSaldo = (EditText) findViewById(R.id.editTextSaldo);
        editTextPorcentagem = (EditText) findViewById(R.id.editTextPorcentagem);
        editTextSaldoPerda = (EditText) findViewById(R.id.editTextSaldoPerda);
        editTextSaldoGanho = (EditText) findViewById(R.id.editTextSaldoGanho);

        buttonSalvarPlanejamento = (Button) findViewById(R.id.buttonSalvarPlanejamento);


    }

    private void clickNoBotaoSalvarListener(){

        this.buttonSalvarPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanejamentoClass planejamentoACadastrar = getPlanejamentosDoFormulario();

                if (planejamentoACadastrar != null){

                    AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(PlanejamentoCadastro.this));
                    long idPlanejamento =  anotacaoCtrl.salvarPlanejamentoCtrl(planejamentoACadastrar);

                    if (idPlanejamento > 0){

                        Toast.makeText(PlanejamentoCadastro.this, "Planejamento salvo com sucesso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(PlanejamentoCadastro.this, Planejamento.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(PlanejamentoCadastro.this, "Planejamento não pode ser salvo", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(PlanejamentoCadastro.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private PlanejamentoClass getPlanejamentosDoFormulario(){

        this.planejamentoClass = new PlanejamentoClass();

        if (editTextDataInicial.getText().toString().isEmpty() == false){
            int dataInicial = Integer.parseInt(this.editTextDataInicial.getText().toString());
            this.planejamentoClass.setDataInicial(dataInicial);
        }else {
            return null;
        }
        if (editTextDataFinal.getText().toString().isEmpty() == false){
            int dataFinal = Integer.parseInt(this.editTextDataFinal.getText().toString());
            this.planejamentoClass.setDataFinal(dataFinal);
        }else {
            return null;
        }
        if (editTextSaldo.getText().toString().isEmpty() == false){
            double saldo = Double.parseDouble(this.editTextSaldo.getText().toString());
            this.planejamentoClass.setSaldo(saldo);
        }else {
            return null;
        }
        if (editTextPorcentagem.getText().toString().isEmpty() == false){
            double porcentagem = Double.parseDouble(this.editTextPorcentagem.getText().toString());
            this.planejamentoClass.setSaldo(porcentagem);
        }else {
            return null;
        }
        if (editTextSaldoPerda.getText().toString().isEmpty() == false){
            double saldoPerda = Double.parseDouble(this.editTextSaldoPerda.getText().toString());
            this.planejamentoClass.setSaldo(saldoPerda);
        }else {
            return null;
        }
        if (editTextSaldoGanho.getText().toString().isEmpty() == false){
            double saldoGanho = Double.parseDouble(this.editTextSaldoGanho.getText().toString());
            this.planejamentoClass.setSaldo(saldoGanho);
        }else {
            return null;
        }

        return planejamentoClass;
    }

}