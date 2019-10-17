package com.juan.firebaseregistro;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorEventosRecientes extends RecyclerView.Adapter<AdaptadorEventosRecientes.EventoViewHolder> {

    List<Evento> listare;
    Context context;
    private OnItemClick listeners;

    public interface OnItemClick
    {

        void itemClick(Evento position);
    }

    public AdaptadorEventosRecientes(List<Evento> listare, Context context, OnItemClick listeners) {
        this.listare = listare;
        this.context = context;
        this.listeners=listeners;
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
            foto= (ImageView) itemView.findViewById(R.id.imgEventoItem);
        }

        public void setData(Evento eventopojo) {

            this.eventopojo = eventopojo;
            nombre.setText(eventopojo.getNombre());

        }

        public void  bind(final  Evento item, final  int position)
        {


            nombre.setText(item.getNombre());
            Glide.with(context).load(item.getUrlImagen()).into(foto);


        }
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemeventoreciente,viewGroup,false);

        return new  EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder,final int i) {
        holder.bind(listare.get(i),i);
        holder.setData(listare.get(i));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.itemClick(listare.get(i));

            }
        });



    }




    @Override
    public int getItemCount() {
        return listare.size();
    }


}
