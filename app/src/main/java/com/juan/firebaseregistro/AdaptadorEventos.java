package com.juan.firebaseregistro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.EventoViewHolder> {

    List<Evento> lista;
    Context context;
    public interface OnItemClick
    {
        void itemClick(Evento items, int position);
    }
    private OnItemClick listener;

    public AdaptadorEventos(List<Evento> lista, Context context, OnItemClick listener) {
        this.lista = lista;
        this.context = context;
        this.listener=listener;
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {
     TextView nombre;
     ImageView foto;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtNombreItem);
            foto= (ImageView) itemView.findViewById(R.id.imgEventoItem);
        }

        public void  bind(final  Evento item, final  int position)
        {


            nombre.setText(item.getNombre());
            Glide.with(context).load(item.getUrlImagen()).into(foto);


        }
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemevento,viewGroup,false);
        EventoViewHolder eventoViewHolder=  new EventoViewHolder(view);
        return eventoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder contactosViewHolder, int i) {
        contactosViewHolder.bind(lista.get(i),i);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
