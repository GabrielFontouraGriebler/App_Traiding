package com.example.app;

public class AnotacaoDiario {

    public Object Id;
    private int data;
    private double saldoPosOp;
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

    @Override
    public String toString() {
        return "AnotacaoDiario{" +
                "data=" + data +
                ", saldoPosOp=" + saldoPosOp +
                ", observacao='" + observacao + '\'' +
                '}';
    }

    public Long getId() {
        return null;
    }

    public void setId(long aLong) {
    }
}
