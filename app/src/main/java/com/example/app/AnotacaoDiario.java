package com.example.app;

public class AnotacaoDiario {

    public Long Id;
    private int data;
    private double saldoPosOp;
    private double lucroPrejuizo;
    private String observacao;

    public AnotacaoDiario() {
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public double getSaldoPosOp() {
        return saldoPosOp;
    }

    public void setSaldoPosOp(double saldoPosOp) {
        this.saldoPosOp = saldoPosOp;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getLucroPrejuizo() {
        return lucroPrejuizo;
    }

    public void setLucroPrejuizo(double lucroPrejuizo) {
        this.lucroPrejuizo = lucroPrejuizo;
    }

    @Override
    public String toString() {
        return "AnotacaoDiario{" +
                "Id=" + Id +
                "data=" + data +
                ", saldoPosOp=" + saldoPosOp +
                ", lucroPrejuizo=" + lucroPrejuizo +
                ", observacao='" + observacao + '\'' +
                '}';
    }

    public Long getId() {
        return null;
    }

    public void setId(long aLong) {
    }
}
