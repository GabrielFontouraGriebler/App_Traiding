package com.example.app;

public class AnotacaoCtrl {

    private final AnotacaoDAO anotacaoDAO;

    public AnotacaoCtrl(ConexaoSQLiteDiario pConexaoSQLiteDiario){
        anotacaoDAO = new AnotacaoDAO(pConexaoSQLiteDiario);
    }

    public long salvarAnotacaoCtrl(AnotacaoDiario aAnotacaoDiario){
        return this.anotacaoDAO.salvarAnotacaoDAO(aAnotacaoDiario);
    }

}
