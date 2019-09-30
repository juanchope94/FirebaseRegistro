package com.juan.firebaseregistro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Menu_Principal extends Fragment {

    RecyclerView recyclerView;
    AdaptadorEventos adaptadorEventos;
    Evento objEvent;
    List<Evento> itemEventos;
    private DatabaseReference mDatabase;// ...

    public Menu_Principal()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState ) {


        final View view = getLayoutInflater().inflate(R.layout.activity_menu_principal,container,false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView= (RecyclerView) view.findViewById(R.id.recycEventosTodos);
        itemEventos= new ArrayList<>();
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);


            mDatabase.child("Evento").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    itemEventos.clear();
                    adaptadorEventos.notifyDataSetChanged();
                    if (dataSnapshot.exists()) {
                        for(int i=0; i<dataSnapshot.getChildrenCount();i++) {


                            objEvent = new Evento();
                            objEvent.setNombre(dataSnapshot.child(""+i).child("nombre").getValue().toString());
                            objEvent.setUrlImagen(dataSnapshot.child(""+i).child("urlImagen").getValue().toString());
                            itemEventos.add(objEvent);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        adaptadorEventos= new AdaptadorEventos(itemEventos, getContext(), new AdaptadorEventos.OnItemClick(){
            @Override
            public void itemClick(Evento items, int position) {


            }
        });
        recyclerView.setAdapter(adaptadorEventos);

        return view;
    }


    }



