package com.example.juansantiagoacev.uniandessatt;

import com.example.juansantiagoacev.uniandessatt.DAO.User;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by juansantiagoacev on 5/10/16.
 */
public interface APIService {

    public String baseUrl = "https://uniandes-satt.herokuapp.com";

    @GET("eventos")
    Call<List<Evento>> loadEventos();

    @GET("sensores")
    Call<List<Sensor>> loadSensores();

    @GET("alertas")
    Call<List<Alerta>> loadAlertas(@Header("Authorization") String authorization);

    @POST("eventos")
    Call<Evento> postEvento();

    @POST("auth")
    Call<User> login(@Body HashMap<String, String> json);
}
