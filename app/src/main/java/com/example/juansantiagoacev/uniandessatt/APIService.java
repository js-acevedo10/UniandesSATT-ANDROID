package com.example.juansantiagoacev.uniandessatt;

import com.example.juansantiagoacev.uniandessatt.DTO.Alerta;
import com.example.juansantiagoacev.uniandessatt.DTO.Evento;
import com.example.juansantiagoacev.uniandessatt.DTO.Sensor;
import com.example.juansantiagoacev.uniandessatt.DTO.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by juansantiagoacev on 5/10/16.
 */
public interface APIService {

    @GET("eventos")
    Call<List<Evento>> loadEventos(@Header("Authorization") String authorization);

    @GET("sensores")
    Call<List<Sensor>> loadSensores(@Header("Authorization") String authorization);

    @GET("alertas")
    Call<List<Alerta>> loadAlertas(@Header("Authorization") String authorization);

    @POST("eventos")
    Call<Evento> postEvento();

    @POST("auth")
    Call<User> login(@Body HashMap<String, String> json);
}
