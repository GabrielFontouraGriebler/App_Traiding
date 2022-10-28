package com.example.app;

public class AnotacaoDiario {

    public Object setId;
    private int data;
    private float saldoPosOp;
    private String observacao;

    public AnotacaoDiario() {
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public float getSaldoPosOp() {
        return saldoPosOp;
    }

    public void setSaldoPosOp(float saldoPosOp) {
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
