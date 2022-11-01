package com.example.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AnotacaoDAO {

    private ConexaoSQLiteDiario conexaoSQLiteDiario;

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
        }finally {
            if(db != null){
                db.close();
            }
        }

        return 0;

    }

    public List<AnotacaoDiario> getListaAnotacaoDAO(){

        List<AnotacaoDiario> ListaAnotacao = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM anotacoes;";

        try{

            db = this.conexaoSQLiteDiario.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()){

                AnotacaoDiario anotacaoDiarioTemp = null;

                do {

                    anotacaoDiarioTemp = new AnotacaoDiario();
                    anotacaoDiarioTemp.setId(cursor.getLong(0));
                    anotacaoDiarioTemp.setData(cursor.getInt(1));
                    anotacaoDiarioTemp.setSaldoPosOp(cursor.getDouble(2));
                    anotacaoDiarioTemp.setObservacao(cursor.getString(3));

                    ListaAnotacao.add(anotacaoDiarioTemp);

                }while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("ERRO LISTA PRODUTOS","ERRO AO RETORNAR PRODUTOS");
            return null;
        }finally {
            if (db != null);
            db.close();
        }

        return ListaAnotacao;
    }

    public boolean excluirAnotacaoDAO(long pIdAnotacao){
        SQLiteDatabase db = null;

        try{

            db = this.conexaoSQLiteDiario.getWritableDatabase();

            db.delete(
                    "anotacoes",
                    "data = ?",
                    new String[]{String.valueOf(pIdAnotacao)}
            );

        }catch(Exception e){
            Log.d("ANOTACAODAO", "NÃO FOI POSSIVEL DELETAR ANOTAÇÃO");
            return false;
        }finally {
            if(db != null){
                db.close();
            }
        }

        return true;
    }

    public boolean atualizarAnotacaoDAO(AnotacaoDiario pAnotacaoDiario){
        SQLiteDatabase db = null;

        try{

            db = this.conexaoSQLiteDiario.getWritableDatabase();

            ContentValues anotacaoAtributos = new ContentValues();
            anotacaoAtributos.put("data", pAnotacaoDiario.getData());
            anotacaoAtributos.put("saldo_pos_op", pAnotacaoDiario.getSaldoPosOp());
            anotacaoAtributos.put("observacao", pAnotacaoDiario.getObservacao());

            int atualizou = db.update("anotacoes",
                    anotacaoAtributos,
                    "data = ?",
                    new String[]{String.valueOf(pAnotacaoDiario.getData())}
                    );

            if(atualizou > 0);
            return true;

        }catch (Exception e){
            Log.d("ANOTACAODAO", "Não foi possivel atualizar anotacao");
            return false;
        }finally {
            if(db != null);{
                db.close();
            }
        }
    }


   }
