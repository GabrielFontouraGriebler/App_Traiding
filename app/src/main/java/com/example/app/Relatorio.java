package com.example.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class Relatorio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button buttonAnotacao;

    private ListView ListViewAnotacoes;
    private List<AnotacaoDiario> anotacaoList;
    private AdapterListaAnotacao adapterListaAnotacao;

    public TextView textViewSaldoAtual;

    private DrawerLayout drawerLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        drawerLayout = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.navigation_draw_open,R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        textViewSaldoAtual = (TextView) findViewById(R.id.textViewSaldoAtual);
        String valor = getIntent().getStringExtra("Saldo");
        textViewSaldoAtual.setText(valor);



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




        this. ListViewAnotacoes = (ListView) findViewById(R.id.ListViewAnotacoes);

        this.adapterListaAnotacao = new AdapterListaAnotacao(Relatorio.this, this.anotacaoList);

        this.ListViewAnotacoes.setAdapter(this.adapterListaAnotacao);

        this.ListViewAnotacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

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

                            adapterListaAnotacao.removerAnotacao(posicao);

                            Toast.makeText(Relatorio.this, "Anotação excluida com sucesso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Relatorio.this, "Erro ao excluir anotação", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                janelaDeEscolha.setPositiveButton("Detalhes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Bundle bundleDadosAnotacao = new Bundle();

                        bundleDadosAnotacao.putInt("data_anotacao", anotacaoSelecionada.getData());
                        bundleDadosAnotacao.putDouble("saldo_pos_op", anotacaoSelecionada.getSaldoPosOp());
                        bundleDadosAnotacao.putString("lucroPrejuizo", anotacaoSelecionada.getLucroPrejuizo());

                        bundleDadosAnotacao.putString("observacao_anotacao", anotacaoSelecionada.getObservacao());

                        Intent intentEditarProdutos = new Intent(Relatorio.this, AntacaoEditar.class);
                        intentEditarProdutos.putExtras(bundleDadosAnotacao);
                        startActivity(intentEditarProdutos);

                    }
                });

                janelaDeEscolha.create().show();

            }
        });




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.Planejamento:
                Intent intent = new Intent(this, Planejamento.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}