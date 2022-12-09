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

    public long salvarPlanejamentoCtrl(PlanejamentoClass aPlanejamentoClass){
        return this.anotacaoDAO.salvarPlanejamentoDAO(aPlanejamentoClass);
    }

    public List<AnotacaoDiario> getListaAnotacaoCtrl(){
        return this.anotacaoDAO.getListaAnotacaoDAO();
    }

    public List<PlanejamentoClass> getPlanejamentoCtrl(){
        return this.anotacaoDAO.getListaPlanejamentoDAO();
    }

    public boolean excluirPlanejamentoCtrl(long pIdPlanejamento){
        return this.anotacaoDAO.excluirPlanejamentoDAO(pIdPlanejamento);
    }

    public boolean excluirAnotacaoCtrl(long pIdProduto){
        return this.anotacaoDAO.excluirAnotacaoDAO(pIdProduto);
    }

    public boolean atualizarAnotacaoCtrl(AnotacaoDiario pAnotacaoDiario){
        return this.anotacaoDAO.atualizarAnotacaoDAO(pAnotacaoDiario);
    }


}
