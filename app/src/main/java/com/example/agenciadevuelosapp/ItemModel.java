package com.example.agenciadevuelosapp;

public class ItemModel {

    private String id;

    String origen;
    String destino;

    String salida;

    String regreso;
    String hora;
    String pago;


    public ItemModel(String id, String origen, String destino,String salida,String regreso,String hora,
    String pago) {
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
}