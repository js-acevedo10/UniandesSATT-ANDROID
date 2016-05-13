package com.example.juansantiagoacev.uniandessatt.DTO;

/**
 * Created by juansantiagoacev on 5/10/16.
 */
public class Alerta {

    private String id, perfil, zona;

    private long tLlegada;

    private double altura;

    public Alerta() {
    }

    public Alerta(String id, String perfil, String zona, long tLlegada, double altura) {
        this.id = id;
        this.perfil = perfil;
        this.zona = zona;
        this.tLlegada = tLlegada;
        this.altura = altura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public long gettLlegada() {
        return tLlegada;
    }

    public void settLlegada(long tLlegada) {
        this.tLlegada = tLlegada;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return zona + " - " + perfil + " - " + altura + "m";
    }
}
