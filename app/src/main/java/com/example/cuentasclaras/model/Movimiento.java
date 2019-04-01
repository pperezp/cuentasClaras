package com.example.cuentasclaras.model;

public class Movimiento {
    private int id;
    private int monto;
    private String cuentaOrigen;
    private String cuentaDestino;
    private String detalle;
    private String fecha;
    private int cuentaOrigenResourceId;
    private int cuentaDestinoResourceId;

    public int getCuentaOrigenResourceId() {
        return cuentaOrigenResourceId;
    }

    public void setCuentaOrigenResourceId(int cuentaOrigenResourceId) {
        this.cuentaOrigenResourceId = cuentaOrigenResourceId;
    }

    public int getCuentaDestinoResourceId() {
        return cuentaDestinoResourceId;
    }

    public void setCuentaDestinoResourceId(int cuentaDestinoResourceId) {
        this.cuentaDestinoResourceId = cuentaDestinoResourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id=" + id +
                ", monto=" + monto +
                ", cuentaOrigen='" + cuentaOrigen + '\'' +
                ", cuentaDestino='" + cuentaDestino + '\'' +
                ", detalle='" + detalle + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
