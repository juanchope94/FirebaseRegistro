package com.juan.firebaseregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Favoritos extends Fragment {


    TextView nombreevento, descripcionevento, numeroTelefono, direccionEvento;
    ImageView imagenevento;


    public Favoritos() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState ) {


        //View view = inflater.inflate(R.layout.activity_favoritos,container,false);
        final View view = getLayoutInflater().inflate(R.layout.activity_favoritos, container, false);

        nombreevento = (TextView) view.findViewById(R.id.txt_Nombre_Del_Evento_Favoritos);
        imagenevento = (ImageView) view.findViewById(R.id.img_Favoritos);
        descripcionevento =(TextView) view.findViewById(R.id.txt_Descripcion_Del_Evento);
        direccionEvento =(TextView) view.findViewById(R.id.txt_Dirreccion);
        numeroTelefono =(TextView) view.findViewById(R.id.txt_Numero_Telefonico);


        Bundle eventodetall  = getArguments();
        Evento eventomues = null;
        if (getArguments() != null){

            eventomues = (Evento) eventodetall.getSerializable("objeto");
            nombreevento.setText(eventomues.getNombre());
            //  Glide.with(context).load(item.getUrlImagen()).into(foto);
            Glide.with(getActivity()).load(eventomues.getUrlImagen()).into(imagenevento);
            descripcionevento.setText(eventomues.getDescripcion());
            direccionEvento.setText(eventomues.getDireccion());
            numeroTelefono.setText(eventomues.getTelefono());


        }




        return view;




    }



}
