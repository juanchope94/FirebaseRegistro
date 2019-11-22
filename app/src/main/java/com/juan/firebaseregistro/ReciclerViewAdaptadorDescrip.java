package com.juan.firebaseregistro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReciclerViewAdaptadorDescrip extends RecyclerView.Adapter<ReciclerViewAdaptadorDescrip.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre, descripcion, desarrollado, nombr1,nombr2,nombr3,nombr4,nombr5,nombr6,instructor,nombr7;
        ImageView fotoNosotros;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=(TextView)itemView.findViewById(R.id.txtViewInfo);
            descripcion=(TextView)itemView.findViewById(R.id.txtViewDescrip);
            desarrollado=(TextView)itemView.findViewById(R.id.txtDesarrollado);
            fotoNosotros= itemView.findViewById(R.id.img_Foto_Info);
            nombr1=(TextView) itemView.findViewById(R.id.txtNombre1);
            nombr2=(TextView) itemView.findViewById(R.id.txtNombre2);
            nombr3=(TextView) itemView.findViewById(R.id.txtNombre3);
            nombr4=(TextView) itemView.findViewById(R.id.txtNombre4);
            nombr5=(TextView) itemView.findViewById(R.id.txtNombre5);
            nombr6=(TextView) itemView.findViewById(R.id.txtNombre6);
            instructor=(TextView)itemView.findViewById(R.id.txtInstructor);
            nombr7=(TextView) itemView.findViewById(R.id.txtNombre7);

        }
    }

    public List<Descripcion_Desarrolladores> desarrolladoresLista;

    public ReciclerViewAdaptadorDescrip(List<Descripcion_Desarrolladores> desarrolladoresLista) {
        this.desarrolladoresLista = desarrolladoresLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_informacion,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(desarrolladoresLista.get(position).getNombre());
        holder.descripcion.setText(desarrolladoresLista.get(position).getDescripcion());
        holder.desarrollado.setText(desarrolladoresLista.get(position).getDesarrollado());
        holder.fotoNosotros.setImageResource(desarrolladoresLista.get(position).getImgFoto());
        holder.nombr1.setText(desarrolladoresLista.get(position).getNombre1());
        holder.nombr2.setText(desarrolladoresLista.get(position).getNombre2());
        holder.nombr3.setText(desarrolladoresLista.get(position).getNombre3());
        holder.nombr4.setText(desarrolladoresLista.get(position).getNombre4());
        holder.nombr5.setText(desarrolladoresLista.get(position).getNombre5());
        holder.nombr6.setText(desarrolladoresLista.get(position).getNombre6());
        holder.instructor.setText(desarrolladoresLista.get(position).getInstructor());
        holder.nombr7.setText(desarrolladoresLista.get(position).getNombre7());
    }

    @Override
    public int getItemCount() {
        return desarrolladoresLista.size();
    }
}
