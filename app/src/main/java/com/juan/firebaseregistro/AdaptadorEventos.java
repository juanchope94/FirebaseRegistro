package com.juan.firebaseregistro;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.EventoViewHolder> {

    List<Evento> lista;
    Context context;
    private OnItemClick listener;

    public interface OnItemClick {
        void itemClick(Evento position);
    }


    public AdaptadorEventos(List<Evento> lista, Context context, OnItemClick listener) {
        this.lista = lista;
        this.context = context;
        this.listener = listener;
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {
        Dialog dialog;
        TextView nombre;
        ImageView foto;
        OnItemClick onItemClick;
        Evento eventopojo;
        View layout;
        Button btnFavoritos;
        boolean  resp=false;
        String idFav="";


        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            nombre = (TextView) itemView.findViewById(R.id.txtNombreItem);
            foto = (ImageView) itemView.findViewById(R.id.imgEventoItem);
            btnFavoritos = itemView.findViewById(R.id.btn_favoritos);
        }

        public void setData(Evento eventopojo) {
//aqui tengo que seteat todos los datos al pojo, asi como esta nombre
            this.eventopojo = eventopojo;
            nombre.setText(eventopojo.getNombre());


            //  descripcion.setTet(eventopojo.getDescripcion());

        }

        public void bind(final Evento item, final int position) {


            nombre.setText(item.getNombre());
            Glide.with(context).load(item.getUrlImagen()).into(foto);
            btnFavoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if( FirebaseAuth.getInstance().getCurrentUser()!=null)
                    {
                        String email="";
                     FirebaseFirestore db = FirebaseFirestore.getInstance();
                     email= FirebaseAuth.getInstance().getCurrentUser().getEmail();


                        final String finalEmail = email;
                        db.collection("Favoritos").whereEqualTo("correo", email).
                                whereEqualTo("idEvento",item.getId())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {

                                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                db.collection("Favoritos").document(document.getId())
                                                        .delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                resp=false;

                                                                Toast.makeText(context, "Evento removido de tus favoritos!", Toast.LENGTH_LONG).show();

                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {

                                                                resp=false;
                                                                Toast.makeText(context, "Error al eliminar de favoritos, vuelve a intentar", Toast.LENGTH_LONG).show();

                                                            }
                                                        });

                                                return;

                                            }
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("idEvento",item.getId());
                                            map.put("correo", finalEmail);
                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            db.collection("Favoritos")
                                                    .add(map)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {

                                                            resp=false;
                                                            Toast.makeText(context, "Evento agregado a favoritos!", Toast.LENGTH_LONG).show();
                                                        }
                                                    })

                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                            resp=false;
                                                            Toast.makeText(context, "Error al agregar a favoritos, vuelve a intentar", Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                        } else {

                                        }
                                    }
                                });


                        if (resp==true)
                        {

                            Toast.makeText(context, "entro el perro", Toast.LENGTH_SHORT).show();

                        }
                        else {



                        }
                    }



                }
            });


        }


    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemevento, viewGroup, false);
        // EventoViewHolder eventoViewHolder=  new EventoViewHolder(view,listener);


        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder contactosViewHolder, final int i) {
        contactosViewHolder.bind(lista.get(i), i);
        contactosViewHolder.setData(lista.get(i));

        contactosViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.itemClick(lista.get(i));
            }
        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
