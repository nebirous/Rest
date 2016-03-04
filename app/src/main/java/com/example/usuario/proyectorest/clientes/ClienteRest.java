package com.example.usuario.proyectorest.clientes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.usuario.proyectorest.clases.Actividad;
import com.example.usuario.proyectorest.interfaz.ApiActividades;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;

public class ClienteRest{
    private String url;
    private Context c;

    public ClienteRest(Context c) {
        this.c = c;

//        url = "http://ieszv.x10.bz/restful/api/profesor";
//        Tarea t = new Tarea();
//        t.execute(url);
    }

    private static final String OPEN_WEATHER_MAP_API = "http://ieszv.x10.bz/restful/api/alumno";

//    @Override
//    public Call<List<Actividad>> getActividades() {
//        url = "http://ieszv.x10.bz/restful/api/profesor";
//        Tarea t = new Tarea();
//        t.execute(url);
//        return null;
//    }


}