package com.juan.firebaseregistro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Informacion_Desarrolladores extends AppCompatActivity {

    private RecyclerView recyclerViewDesarrolladores;
    private ReciclerViewAdaptadorDescrip adaptadorDescrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion__desarrolladores);

        recyclerViewDesarrolladores = (RecyclerView) findViewById(R.id.Rcy_Informacion);
        recyclerViewDesarrolladores.setLayoutManager(new LinearLayoutManager(this));

        adaptadorDescrip=new ReciclerViewAdaptadorDescrip(obtenerDescripcion());
        recyclerViewDesarrolladores.setAdapter(adaptadorDescrip);
    }

    public List<Descripcion_Desarrolladores> obtenerDescripcion()
    {
        List<Descripcion_Desarrolladores> desarrollo = new ArrayList<>();
        desarrollo.add(new Descripcion_Desarrolladores("Version 1.0","Aplicación desarrollada bajo la especialización en desarrollo de aplicaciones para dispositivos móviles de SENA CTPI 2019","Desarrollado por:","Fabián Urrea Ceballos","Edwin Willer Narvaez","Danilo Manquillo López","Juan David Muñoz Garzón","Felipe Betancourt Figueroa","Gustavo Salazar Escobar","Instructora:","Ing. Zulema León",R.drawable.eventime));

        return desarrollo;
    }
}
