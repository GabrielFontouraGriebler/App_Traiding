package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.temporal.Temporal;

public class AntacaoEditar extends AppCompatActivity {

    private EditText editTextData;
    private EditText editTextSaldoPosOP;
    private EditText editTextObservacao;



    private Button buttonSalvarAlteracoes;

    private AnotacaoDiario anotacaoDiario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antacao_editar);

        this.editTextData = (EditText) findViewById(R.id.editTextData);
        this.editTextSaldoPosOP = (EditText) findViewById(R.id.editTextSaldoPosOP);
        this.editTextObservacao = (EditText) findViewById(R.id.editTextObservacao);






        this.buttonSalvarAlteracoes = (Button) findViewById(R.id.buttonSalvarAnotacao);

        Bundle bundleDadosAnotacao = getIntent().getExtras();

        AnotacaoDiario anotacaoDiario = new AnotacaoDiario();

        anotacaoDiario.setData(bundleDadosAnotacao.getInt("data_anotacao"));
        anotacaoDiario.setSaldoPosOp(bundleDadosAnotacao.getDouble("saldo_pos_op"));
        anotacaoDiario.setObservacao(bundleDadosAnotacao.getString("observacao_anotacao"));


        this.setDadosAnotacao(anotacaoDiario);

        this.clickNoBotaoSalvarListener();

    }

    private void setDadosAnotacao (AnotacaoDiario anotacaoDiario){

        this.editTextData.setText(String.valueOf(anotacaoDiario.getData()));
        this.editTextSaldoPosOP.setText(String.valueOf(anotacaoDiario.getSaldoPosOp()));
        this.editTextObservacao.setText(anotacaoDiario.getObservacao());

    }

    private AnotacaoDiario getAnotacoesDoFormulario() {

        this.anotacaoDiario = new AnotacaoDiario();

        if (editTextData.getText().toString().isEmpty() == false) {
            int data = Integer.parseInt(this.editTextData.getText().toString());
            this.anotacaoDiario.setData(data);
        } else {
            return null;
        }
        if (editTextSaldoPosOP.getText().toString().isEmpty() == false) {
            double saldoPosOp = Double.parseDouble(this.editTextSaldoPosOP.getText().toString());
            this.anotacaoDiario.setSaldoPosOp(saldoPosOp);
        } else {
            return null;
        }
        if (this.editTextObservacao.getText().toString().isEmpty() == false) {
            this.anotacaoDiario.setObservacao(this.editTextObservacao.getText().toString());
        } else {
            return null;
        }

        return anotacaoDiario;
    }

    private void clickNoBotaoSalvarListener(){

        this.buttonSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AnotacaoDiario anotacaoAAtualizar = getAnotacoesDoFormulario();


                if(anotacaoAAtualizar != null){

                    AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(AntacaoEditar.this));
                    boolean atualizou = anotacaoCtrl.atualizarAnotacaoCtrl(anotacaoAAtualizar);


                    if(atualizou == true){
                        Toast.makeText(AntacaoEditar.this, "Anotação salva com sucesso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AntacaoEditar.this, Relatorio.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(AntacaoEditar.this, "Anotação não pode ser salva", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(AntacaoEditar.this, "Todos os campos são obrigatorios", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

}