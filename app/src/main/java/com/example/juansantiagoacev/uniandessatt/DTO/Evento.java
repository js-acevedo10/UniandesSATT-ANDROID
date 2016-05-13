package com.example.juansantiagoacev.uniandessatt.DTO;

/**
 * Created by juansantiagoacev on 5/10/16.
 */
public class Evento {

    private String id;
    private double lat, lng, distancia;

    public Evento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Lat: " + lat + " | Long: " + lng + " | Dist: " + distancia;
    }
}
