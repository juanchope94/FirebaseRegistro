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
        desarrollo.add(new Descripcion_Desarrolladores("Direccion SENA CTPI: Tv. 9 Nte. #60, Popayán, Cauca","Numero de contacto: 3233674202","Correos instructor:","zleon_20@hotmail.co",R.drawable.logo));

        return desarrollo;
    }
}
