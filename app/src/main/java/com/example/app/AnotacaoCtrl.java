package com.example.app;

import java.util.List;

public class AnotacaoCtrl {

    private final AnotacaoDAO anotacaoDAO;

    public AnotacaoCtrl(ConexaoSQLiteDiario pConexaoSQLiteDiario){
        anotacaoDAO = new AnotacaoDAO(pConexaoSQLiteDiario);
    }

    public long salvarAnotacaoCtrl(AnotacaoDiario aAnotacaoDiario){
        return this.anotacaoDAO.salvarAnotacaoDAO(aAnotacaoDiario);
    }

    public List<AnotacaoDiario> getListaAnotacaoCtrl(){
        return this.anotacaoDAO.getListaAnotacaoDAO();
    }

    public boolean excluirAnotacaoCtrl(long pIdProduto){
        return this.anotacaoDAO.excluirAnotacaoDAO(pIdProduto);
    }


}
