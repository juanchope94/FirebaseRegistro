package com.juan.firebaseregistro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class Menu_Principal extends Fragment {

    public static Menu_Principal newInstance() {
        return new Menu_Principal();
    }

    RecyclerView recyclerView, recycleRecientes;
    AdaptadorEventos adaptadorEventos;
    AdaptadorEventosRecientes adaptadorEventosRecientes;
    BottomNavigationView opciones;
    TextView tituloSeccion;
    Comunicador comunicador;
    Activity activity;
    AdaptadorEventos.OnItemClick click;
    AdaptadorEventosRecientes.OnItemClick clickr;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Evento objEvent;
    List<Evento> itemEventos, listareciente;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = getLayoutInflater().inflate(R.layout.activity_menu_principal, container, false);

        opciones = (BottomNavigationView) view.findViewById(R.id.navigation_00);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycEventosTodos);
        recycleRecientes = (RecyclerView) view.findViewById(R.id.recycEventosRecientes);
        itemEventos = new ArrayList<>();
        tituloSeccion = view.findViewById(R.id.textView2);
           opciones.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        eventoclick();
        eventoClicr();
        listatodos();

        return view;
    }
    private void eventoClicr()
    {
        clickr = new AdaptadorEventosRecientes.OnItemClick() {
            @Override
            public void itemClick(Evento position) {
                comunicador.enviardatos(position);

            }
        };
    }
    private void eventoclick() {
        click = new AdaptadorEventos.OnItemClick() {
            @Override
            public void itemClick(Evento position) {
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

        itemEventos.clear();
        adaptadorEventos.notifyDataSetChanged();
        db.collection("Evento").whereEqualTo("categoria", parametro)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("", "Listen failed.", e);
                            return;
                        }
                        itemEventos.clear();
                        for (QueryDocumentSnapshot doc : value) {

                                Evento eve = doc.toObject(Evento.class);
                                eve.setId(doc.getId());
                                itemEventos.add(eve);

                        }

                        adaptadorEventos.notifyDataSetChanged();

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
                case R.id.bienestar:
                    tituloSeccion.setText("Bienestar");
                    ActualizarRecycler("Bienestar");
                    return true;

                default:
                    listatodos();
            }

            return false;

        }

    };

      private void listatodos() {

        FirebaseMessaging.getInstance().subscribeToTopic("evento")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "bien";
                        if (!task.isSuccessful()) {
                            msg = "mal";
                        }
                        Log.d("me suscribi a ", msg);
                    }
                });





        db.collection("Evento").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("", "Listen failed.", e);
                    return;
                }
                itemEventos.clear();
                for (QueryDocumentSnapshot doc : value) {
                    Evento eve = doc.toObject(Evento.class);
                    eve.setId(doc.getId());
                    itemEventos.add(eve);

                }

                adaptadorEventos.notifyDataSetChanged();

            }
        });

        adaptadorEventos = new AdaptadorEventos(itemEventos, getContext(),click);
        recyclerView.setAdapter(adaptadorEventos);
    }
}



