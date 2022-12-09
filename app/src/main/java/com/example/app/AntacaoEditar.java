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

public class AntacaoEditar extends AppCompatActivity {

    private EditText editTextData;
    private EditText editTextSaldoPosOP;
    private EditText editTextObservacao;

    private TextView textViewLucroPrejuizo;

    private ListView ListViewAnotacoes;
    private List<AnotacaoDiario> anotacaoList;
    private AdapterListaAnotacao adapterListaAnotacao;


    private Button buttonSalvarAlteracoes;

    private AnotacaoDiario anotacaoDiario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antacao_editar);

        this.editTextData = (EditText) findViewById(R.id.editTextData);
        this.editTextSaldoPosOP = (EditText) findViewById(R.id.editTextSaldoPosOP);
        this.editTextObservacao = (EditText) findViewById(R.id.editTextObservacao);

        AnotacaoCtrl anotacaoCtrl = new AnotacaoCtrl(ConexaoSQLiteDiario.getInstancia(AntacaoEditar.this));
        anotacaoList = anotacaoCtrl.getListaAnotacaoCtrl();

        textViewLucroPrejuizo = (TextView) findViewById(R.id.textViewLucroPrejuizo);

        //Botão que vai calcular e projetar as informações de lucro e prejuizo no text view
        final Button buttonCalculaLucroPrejuizo = (Button) findViewById(R.id.buttonCalculaLucroPrejuizo);
        buttonCalculaLucroPrejuizo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anotacaoList.size()>0) {
                    int ultimaPosicao = anotacaoList.size() - 1;
                    AnotacaoDiario anotacaoDiario = anotacaoList.get(ultimaPosicao);
                    int valor1 = Integer.parseInt(editTextSaldoPosOP.getText().toString());
//                    int valor2 = ultimaPosicao;
//                    int resultado1 = valor1 - valor2;


                    textViewLucroPrejuizo.setText(Double.toString(valor1 - anotacaoDiario.getSaldoPosOp()));
                }

            }
        });


        this.buttonSalvarAlteracoes = (Button) findViewById(R.id.buttonSalvarAlteracao);

        Bundle bundleDadosAnotacao = getIntent().getExtras();

        AnotacaoDiario anotacaoDiario = new AnotacaoDiario();

        anotacaoDiario.setData(bundleDadosAnotacao.getInt("data_anotacao"));
        anotacaoDiario.setSaldoPosOp(bundleDadosAnotacao.getDouble("saldo_pos_op"));
        //se deixar com essa parte do texto, ao abrir a pagina ele busca a informação salva no banco
        anotacaoDiario.setLucroPrejuizo(bundleDadosAnotacao.getString("lucroPrejuizo"));
        anotacaoDiario.setObservacao(bundleDadosAnotacao.getString("observacao_anotacao"));


        this.setDadosAnotacao(anotacaoDiario);

        this.clickNoBotaoSalvarListener();

    }

    private void setDadosAnotacao (AnotacaoDiario anotacaoDiario){

        this.editTextData.setText(String.valueOf(anotacaoDiario.getData()));
        this.editTextSaldoPosOP.setText(String.valueOf(anotacaoDiario.getSaldoPosOp()));
        this.textViewLucroPrejuizo.setText(anotacaoDiario.getLucroPrejuizo());
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
        if(textViewLucroPrejuizo.getText().toString().isEmpty() == false){
            this.anotacaoDiario.setLucroPrejuizo((this.textViewLucroPrejuizo.getText().toString()));
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