package com.example.agenciadevuelosapp;

import com.google.firebase.Timestamp;

public class Note {
    String title;
    String Origen;
    String Destino;
    String Salida;
    String Regreso;
    String Hora;
    Timestamp date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public String getSalida() {
        return Salida;
    }

    public void setSalida(String salida) {
        Salida = salida;
    }

    public String getRegreso() {
        return Regreso;
    }

    public void setRegreso(String regreso) {
        Regreso = regreso;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}


