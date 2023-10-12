package com.example.agenciadevuelosapp;

public class ItemModel {

    private String id;
    private String origen;
    private String destino;
    private String salida;
    private String regreso;
    private String hora;
    private String pago;


    public ItemModel(String id, String origen, String destino, String salida, String regreso, String hora, String pago) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.salida = salida;
        this.regreso = regreso;
        this.hora = hora;
        this.pago = pago;
    }


    public String getId() {
        return id;
    }


    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getSalida() {
        return salida;
    }

    public String getRegreso() {
        return regreso;
    }

    public String getHora() {
        return hora;
    }

    public String getPago() {
        return pago;
    }
}

