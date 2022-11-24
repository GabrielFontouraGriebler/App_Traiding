package com.example.app;

public class PlanejamentoClass {

    private int idPlanejamento;
    private int dataInicial;
    private int dataFinal;
    private double saldo;
    private double porcentagem;
    private double Perda;
    private double Ganho;

    public int getIdPlanejamento() {
        return idPlanejamento;
    }

    public void setIdPlanejamento(int idPlanejamento) {
        this.idPlanejamento = idPlanejamento;
    }

    public int getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(int dataInicial) {
        this.dataInicial = dataInicial;
    }

    public int getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(int dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public double getPerda() {
        return Perda;
    }

    public void setPerda(double perda) {
        Perda = perda;
    }

    public double getGanho() {
        return Ganho;
    }

    public void setGanho(double ganho) {
        Ganho = ganho;
    }

    @Override
    public String toString() {
        return "PlanejamentoClass{" +
                "idPlanejamento=" + idPlanejamento +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", saldo=" + saldo +
                ", porcentagem=" + porcentagem +
                ", Perda=" + Perda +
                ", Ganho=" + Ganho + '\'' +
                '}';
    }
}
