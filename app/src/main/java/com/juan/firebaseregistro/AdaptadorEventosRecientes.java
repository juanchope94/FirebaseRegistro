package com.juan.firebaseregistro;

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
    public interface OnItemClick
    {
        void itemClick(Evento items, int position);
    }
    private AdaptadorEventos.OnItemClick listener;

    public AdaptadorEventosRecientes(List<Evento> listare, Context context, AdaptadorEventos.OnItemClick listener) {
        this.listare = listare;
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
    public AdaptadorEventosRecientes.EventoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemeventoreciente,viewGroup,false);
        AdaptadorEventosRecientes.EventoViewHolder eventoViewHolder=  new EventoViewHolder(view);
        return eventoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        holder.bind(listare.get(position),position);
    }




    @Override
    public int getItemCount() {
        return listare.size();
    }


}
