package com.example.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoSQLiteDiario extends SQLiteOpenHelper {

    private static ConexaoSQLiteDiario INSTANCIA_CONEXAO;
    private static final int VERSAO_DB =1;
    private static final String NOME_DB = "anotacao_diario_app";

    public ConexaoSQLiteDiario(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLiteDiario getInstancia(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLiteDiario(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlTabelaDiario =
                "CREATE TABLE IF NOT EXISTS anotacoes" +
                        "(" +
                        "id INTEGER PRIMARY KEY autoincrement," +
                        "data DATE," +
                        "saldo_pos_op REAL," +
                        "lucroPrejuizo TEXT," +
                        "observacao TEXT" +
                        ")";
        sqLiteDatabase.execSQL(sqlTabelaDiario);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
