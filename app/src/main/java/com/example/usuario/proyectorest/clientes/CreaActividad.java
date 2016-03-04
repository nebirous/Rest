package com.example.usuario.proyectorest.clientes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.usuario.proyectorest.clases.Actividad;
import com.example.usuario.proyectorest.interfaz.ApiActividades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;

/**
 * Created by usuario on 02/03/2016.
 */
public class CreaActividad {
    private String url;
    private Context c;
    private Actividad a;

    public CreaActividad(Actividad a) {
        this.a = a;
    }

    List<Actividad> list = new ArrayList<>();

//    @Override
//    public Call<Actividad> createActividad(@Body Actividad actividad) {
//        url = "http://ieszv.x10.bz/restful/api/actividad";
//        Tarea t = new Tarea();
//        t.execute(url);
//        return null;
//    }


}
