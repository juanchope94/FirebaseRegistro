package com.juan.firebaseregistro;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

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


        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            nombre = (TextView) itemView.findViewById(R.id.txtNombreItem);
            foto = (ImageView) itemView.findViewById(R.id.imgEventoItem);
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
