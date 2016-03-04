package com.example.usuario.proyectorest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usuario.proyectorest.Actividades.AddActividad;
import com.example.usuario.proyectorest.Actividades.EditActividad;
import com.example.usuario.proyectorest.Adaptador.Adaptador;
import com.example.usuario.proyectorest.clases.Actividad;
import com.example.usuario.proyectorest.interfaz.ApiActividades;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private List<Actividad> list;
    private ListView lv;
    private Adaptador adaptador;
    private Retrofit retrofit;
    private ApiActividades api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Proyecto Rest");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiActividades.class);
        list=new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv);
        recoger();
        generaAdaptador();
    }

    public void create(View v) {
        Intent i=new Intent(this, AddActividad.class);
        startActivity(i);
    }
    public void generaAdaptador(){
        adaptador = new Adaptador(this, R.layout.item, list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editar(position);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.alert)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                borrar(position);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).show();
                return false;
            }
        });
        lv.setAdapter(adaptador);
        registerForContextMenu(lv);
    }
    public void editar(int position){
        Intent i=new Intent(this, EditActividad.class);
        Actividad aux=list.get(position);
        Bundle b=new Bundle();
        b.putParcelable("aux",aux);
        i.putExtras(b);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recoger();
        generaAdaptador();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            adaptador.notifyDataSetChanged();
            switch (requestCode){
                case 1:

                    break;
            }
        }
    }

    public void btRecoger(View v) {
        recoger();
        adaptador.notifyDataSetChanged();
    }

    public void borrar(int position) {
        String id=(list.get(position).getId());
        System.out.println("ID: " + id);
        ApiActividades apiActividades = retrofit.create(ApiActividades.class);
        Call<Actividad> call =apiActividades.deleteActividad(id);
        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                System.out.println(": "+response.raw());
            }
            @Override
            public void onFailure(Throwable t) {}
        });
        list.remove(position);
        adaptador.notifyDataSetChanged();
    }

    public void recoger() {
        list=new ArrayList<>();
        Call<List<Actividad>> call = api.getActividades();
        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Response<List<Actividad>> response, Retrofit retrofit) {
                for (Actividad a : response.body()) {
                    list.add(a);
                }
                generaAdaptador();
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }
}
