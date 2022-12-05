package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Anotacao extends AppCompatActivity {

    private ListView ListViewAnotacoes;
    private List<AnotacaoDiario> anotacaoList;
    private AdapterListaAnotacao adapterListaAnotacao;

    private EditText editTextData;
    public EditText editTextSaldoPosOP;
    private EditText editTextObservacao;
    private TextView textViewLucroPrejuizo;



    private Button buttonSalvarAnotacao;

    private AnotacaoDiario  anotacaoDiario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacao);

        editTextData = (EditText) findViewById(R.id.editTextData);
        editTextSaldoPosOP = (EditText) findViewById(R.id.editTextSaldoPosOP);
        editTextObservacao = (EditText) findViewById(R.id.editTextObservacao);

        textViewLucroPrejuizo = (TextView) findViewById(R.id.textViewLucroPrejuizo);

        buttonSalvarAnotacao = (Button) findViewById(R.id.buttonSalvarAlteracao);

        AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(Anotacao.this));
        anotacaoList = anotacaoCtrl.getListaAnotacaoCtrl();


        this.clickNoBotaoSalvarListener();

        //Botão que vai calcular e projetar as informações de lucro e prejuizo no text view
        final Button buttonCalculaLucroPrejuizo = (Button) findViewById(R.id.buttonCalculaLucroPrejuizo);
        buttonCalculaLucroPrejuizo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anotacaoList.size()>0) {
                    int ultimaPosicao = anotacaoList.size() - 1;
                    AnotacaoDiario anotacaoDiario = anotacaoList.get(ultimaPosicao);
                    textViewLucroPrejuizo.setText(Double.toString(anotacaoDiario.getSaldoPosOp()));
                }

               // textViewLucroPrejuizo.setText(editTextSaldoPosOP.getText().toString());
            }
        });


    }

    private void clickNoBotaoSalvarListener(){

        this.buttonSalvarAnotacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AnotacaoDiario anotacaoACadastrar = getAnotacoesDoFormulario();

                if(anotacaoACadastrar != null){

                    AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(Anotacao.this));
                    long idAnotacao = anotacaoCtrl.salvarAnotacaoCtrl(anotacaoACadastrar);




                    if(idAnotacao > 0){

                        Toast.makeText(Anotacao.this, "Anotação salva com sucesso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Anotacao.this, Relatorio.class);
                        //intent.putExtra("Saldo", editTextSaldoPosOP.getText().toString());
                        startActivity(intent);

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
            double saldoPosOp = Double.parseDouble(this.editTextSaldoPosOP.getText().toString());
            this.anotacaoDiario.setSaldoPosOp(saldoPosOp);
        } else {
            return null;
        }
        if(textViewLucroPrejuizo.getText().toString().isEmpty() == false){
            this.anotacaoDiario.setLucroPrejuizo((this.textViewLucroPrejuizo.getText().toString()));
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