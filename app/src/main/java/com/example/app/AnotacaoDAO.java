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

    public long salvarPlanejamentoDAO(PlanejamentoClass aPlanejamentoClass){

        SQLiteDatabase db = conexaoSQLiteDiario.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("id_planejamento", aPlanejamentoClass.getIdPlanejamento());
            values.put("data_inicial", aPlanejamentoClass.getDataInicial());
            values.put("data_final", aPlanejamentoClass.getDataFinal());
            values.put("saldo", aPlanejamentoClass.getSaldo());
            values.put("porcentagem", aPlanejamentoClass.getPorcentagem());
            values.put("saldo_perda", aPlanejamentoClass.getPerda());
            values.put("saldo_ganho", aPlanejamentoClass.getGanho());

            long idPlanejamentoInserido = db.insert("planejamento", null, values);

            return idPlanejamentoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }

        return 0;
    }

    public long salvarAnotacaoDAO(AnotacaoDiario aAnotacaoDiario){

        SQLiteDatabase db = conexaoSQLiteDiario.getWritableDatabase();

        try{

            ContentValues values = new ContentValues();
            values.put("id", aAnotacaoDiario.getId());
            values.put("data", aAnotacaoDiario.getData());
            values.put("saldo_pos_op", aAnotacaoDiario.getSaldoPosOp());
            values.put("lucroPrejuizo", aAnotacaoDiario.getLucroPrejuizo());

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

    public List<PlanejamentoClass> getListaPlanejamentoDAO(){

        List<PlanejamentoClass> ListaPlanejamento = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM planejamento;";

        try {

            db = this.conexaoSQLiteDiario.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()){

                PlanejamentoClass planejamentoClassTemp = null;

                do {
                    planejamentoClassTemp = new PlanejamentoClass();
                    planejamentoClassTemp.setIdPlanejamento(cursor.getInt(0));
                    planejamentoClassTemp.setDataInicial(cursor.getInt(1));
                    planejamentoClassTemp.setDataFinal(cursor.getInt(2));
                    planejamentoClassTemp.setSaldo(cursor.getDouble(3));
                    planejamentoClassTemp.setPorcentagem(cursor.getDouble(4));
                    planejamentoClassTemp.setPerda(cursor.getDouble(5));
                    planejamentoClassTemp.setGanho(cursor.getDouble(6));

                    ListaPlanejamento.add(planejamentoClassTemp);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("ERRO LISTA PLANEJAMENTO", "ERRO AO RETORNAR PLANEJAMENTOS");
            return null;
        }finally {
            if (db != null);
            db.close();
        }

        return ListaPlanejamento;
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
                    anotacaoDiarioTemp.setLucroPrejuizo(cursor.getString(3));
                    anotacaoDiarioTemp.setObservacao(cursor.getString(4));

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

    public boolean excluirPlanejamentoDAO(long pIdPlanejamento){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQLiteDiario.getWritableDatabase();

            db.delete(
                    "planejamento",
                    "id_planejamento = ?",
                    new String[]{String.valueOf(pIdPlanejamento)}
            );
        }catch (Exception e){
            Log.d("PLANEJAMENTODAO", "NAO FOI POSSIVEL DELETAR PLAN");
            return false;
        }finally {
            if (db != null) {
                db.close();
            }
        }
        return true;
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
            anotacaoAtributos.put("lucroPrejuizo", pAnotacaoDiario.getLucroPrejuizo());

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
