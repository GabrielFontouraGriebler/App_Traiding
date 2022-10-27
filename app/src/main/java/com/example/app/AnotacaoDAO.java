package com.example.app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AnotacaoDAO {

    private final ConexaoSQLiteDiario conexaoSQLiteDiario;

    public AnotacaoDAO(ConexaoSQLiteDiario conexaoSQLiteDiario) {
        this.conexaoSQLiteDiario = conexaoSQLiteDiario;
    }

    public long salvarAnotacaoDAO(AnotacaoDiario aAnotacaoDiario){

        SQLiteDatabase db = conexaoSQLiteDiario.getWritableDatabase();

        try{

            ContentValues values = new ContentValues();
            values.put("id", aAnotacaoDiario.getId());
            values.put("data", aAnotacaoDiario.getData());
            values.put("saldo_pos_op", aAnotacaoDiario.getSaldoPosOp());
            values.put("observacao", aAnotacaoDiario.getObservacao());

            long idAnotacaoInserido = db.insert("anotacoes", null, values);

            return idAnotacaoInserido;

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;

    }

}
