package com.juan.firebaseregistro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Menu_Favoritos extends Fragment {


    public static Menu_Favoritos newInstance() {
        return new Menu_Favoritos();
    }
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email = "";

    ArrayList<PojoFavoritos> idEventos;
    RecyclerView recyclerViewFavoritos;
    List<Evento> eventosFavoritos;
    AdaptadorEventos adaptadorEventos;
    AdaptadorEventos.OnItemClick click;
    Comunicador comunicador;
    Activity activity;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {


        View view = inflater.inflate(R.layout.activity_menu__favoritos,container,false);
        mAuth = FirebaseAuth.getInstance();


        recyclerViewFavoritos = (RecyclerView) view.findViewById(R.id.recycFavoritos);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewFavoritos.setLayoutManager(gridLayoutManager);

        eventosFavoritos=new ArrayList<>();
        idEventos = new ArrayList<>();

        if(mAuth.getCurrentUser()!=null) {
            llenarListaFavoritos();
            //cargarFavoritos();
            eventoclick();
        }


        return view;
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


    private void llenarListaFavoritos() {

        email = mAuth.getCurrentUser().getEmail();
        db.collection("Favoritos").whereEqualTo("correo", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String id=document.getString("idEvento");
                                Log.d("cantidad: ", "llenarListaFavoritos: "+document.getString("idEvento"));




                                DocumentReference docRef = db.collection("Evento")
                                        .document(document.getString("idEvento"));
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Evento eve = document.toObject(Evento.class);
                                                eve.setId(document.getId());
                                                eventosFavoritos.add(eve);

                                            } else {
                                                Log.d("", "No such document");
                                            }
                                            adaptadorEventos.notifyDataSetChanged();
                                        } else {
                                            Log.d("", "get failed with ", task.getException());
                                        }
                                    }
                                });





                            adaptadorEventos = new AdaptadorEventos(eventosFavoritos, getContext(),click);
                            recyclerViewFavoritos.setAdapter(adaptadorEventos);




                            }

                        } else {

                        }
                    }
                });


        Toast.makeText(activity, ""+idEventos.size(), Toast.LENGTH_SHORT).show();

    }
    private void cargarFavoritos() {
        eventosFavoritos.clear();

        for (int i = 0; i < idEventos.size(); i++) {

        }
    }

    private void updateUI(FirebaseUser user) {
        if (user == null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
