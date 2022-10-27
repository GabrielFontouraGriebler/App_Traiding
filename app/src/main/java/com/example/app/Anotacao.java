package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Anotacao extends AppCompatActivity {


    private EditText editTextData;
    private EditText editTextSaldoPosOP;
    private EditText editTextObservacao;

    private Button buttonSalvarAnotacao;

    private AnotacaoDiario  anotacaoDiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacao);

        editTextData = (EditText) findViewById(R.id.editTextData);
        editTextSaldoPosOP = (EditText) findViewById(R.id.editTextSaldoPosOP);
        editTextObservacao = (EditText) findViewById(R.id.editTextObservacao);

        buttonSalvarAnotacao = (Button) findViewById(R.id.buttonSalvarAnotacao);

        this.clickNoBotaoSalvarListener();


    }

    private void clickNoBotaoSalvarListener(){

        this.buttonSalvarAnotacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AnotacaoDiario anotacaoACadastrar = getAnotacoesDoFormulario();

                if(anotacaoACadastrar != null){

                    AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(Anotacao.this));
                    long idAnotacao = AnotacaoCtrl.salvarAnotacaoCtrl(anotacaoACadastrar);

                    if(idAnotacao > 0){
                        Toast.makeText(Anotacao.this, "Anotação salva com sucesso", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Anotacao.this, "Anotação não pode ser salva", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(Anotacao.this, "Todos os campos são obrigatorios", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private AnotacaoDiario getAnotacoesDoFormulario(){

        this.anotacaoDiario = new AnotacaoDiario();

        if(editTextData.getText().toString().isEmpty() == false){
            int data = Integer.parseInt(this.editTextData.getText().toString());
            this.anotacaoDiario.setData(data);
        } else {
            return null;
        }
        if(editTextSaldoPosOP.getText().toString().isEmpty() == false){
            float saldoPosOp = Float.parseFloat(this.editTextSaldoPosOP.getText().toString());
            this.anotacaoDiario.setSaldoPosOp(saldoPosOp);
        } else {
            return null;
        }
        if(this.editTextObservacao.getText().toString().isEmpty() == false){
            this.anotacaoDiario.setObservacao(this.editTextObservacao.getText().toString());
        } else {
            return null;
        }

        return anotacaoDiario;
    }
}