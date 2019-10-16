package com.juan.firebaseregistro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Menu_Principal extends Fragment {

    RecyclerView recyclerView, recycleRecientes;
    AdaptadorEventos adaptadorEventos;
    AdaptadorEventosRecientes adaptadorEventosRecientes;
    BottomNavigationView opciones;
    TextView tituloSeccion;
    Comunicador comunicador;
    Activity activity;
    AdaptadorEventos.OnItemClick click;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Evento objEvent;
    List<Evento> itemEventos, listareciente;

    //private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = getLayoutInflater().inflate(R.layout.activity_menu_principal, container, false);

        opciones = (BottomNavigationView) view.findViewById(R.id.navigation_00);
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycEventosTodos);
        recycleRecientes = (RecyclerView) view.findViewById(R.id.recycEventosRecientes);
        itemEventos = new ArrayList<>();
        tituloSeccion = view.findViewById(R.id.textView2);
        listareciente = new ArrayList<>();
        opciones.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleRecientes.setLayoutManager(layoutManager);
        eventoclick();
        listatodos();
        listarecientes();


        return view;
    }
    private void eventoclick() {
        click = new AdaptadorEventos.OnItemClick() {
            @Override
            public void itemClick(Evento  position) {
                comunicador.enviardatos(position);
            }


        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            this.activity = (Activity) context;
            comunicador = (Comunicador) this.activity;
        }
        //  if (context instanceof OnF)

    }

    public void ActualizarRecycler(final String parametro) {

        db.collection("Evento").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d: list){
                        Evento e = d.toObject(Evento.class);
                        itemEventos.add(e);
                    }
                    adaptadorEventos.notifyDataSetChanged();
                }
            }
        });
        adaptadorEventos = new AdaptadorEventos(itemEventos, getContext(),click);

        recyclerView.setAdapter(adaptadorEventos);
    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.deportes_Y_recreaccion:
                    tituloSeccion.setText("Deportes y Recreacion");
                    ActualizarRecycler("Deporte");
                    return true;
                case R.id.ofertas_educativas:
                    tituloSeccion.setText("Ofertas Educativas");
                    ActualizarRecycler("Educacion");

                    return true;
                case R.id.ofertas_laborales:
                    tituloSeccion.setText("Ofertas Laborales");
                    ActualizarRecycler("Laboral");
                    return true;

                default:
                    listatodos();
            }

            return false;

        }

    };

    private void listarecientes() {

        db.collection("Evento").orderBy("fecha").limit(5).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d: list){
                                Evento e = d.toObject(Evento.class);
                                listareciente.add(e);
                            }
                            adaptadorEventos.notifyDataSetChanged();
                        }
                    }
                });

        adaptadorEventosRecientes = new AdaptadorEventosRecientes(listareciente,getContext(), new AdaptadorEventosRecientes.OnItemClick() {
            @Override
            public void itemClick(Evento items, int position) {

            }
        });
        recycleRecientes.setAdapter(adaptadorEventosRecientes);

    }

    //de aqui pa abajo es de juan

    private void listatodos() {

        db.collection("Evento").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d: list){
                        Evento e = d.toObject(Evento.class);
                        itemEventos.add(e);
                    }
                    adaptadorEventos.notifyDataSetChanged();
                }
            }
        });
        adaptadorEventos = new AdaptadorEventos(itemEventos, getContext(),click);

        recyclerView.setAdapter(adaptadorEventos);


    }
}



