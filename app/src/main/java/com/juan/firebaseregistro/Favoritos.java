package com.juan.firebaseregistro;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;

public class Favoritos extends Fragment {


    TextView nombreevento, descripcionevento, numeroTelefono, direccionEvento;
    ImageView imagenevento;
    ImageButton botonLlamar, botonCalendario;
    String telefono;


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
        botonLlamar =(ImageButton) view.findViewById(R.id.btn_Llamar);
        botonCalendario =(ImageButton) view.findViewById(R.id.btn_Calendario);


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
            telefono = eventomues.getTelefono();
            final String fecha = eventomues.getFecha();

            botonLlamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "presiono", Toast.LENGTH_SHORT).show();

                    Uri uri = Uri.parse("tel:3122826217");
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);

                    /*if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!=
                            PackageManager.PERMISSION_GRANTED)
                            return;*/
                   

                }
            });

           /*  botonCalendario.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     Calendar cal = Calendar.getInstance();
                     boolean val = false;
                     Intent intento = null;
                     while (val = false)
                     {
                         try
                         {

                             cal.set(Calendar.DATE, fecha);

                         }catch (Exception e)
                         {

                         }
                     }
                 }
             });*/
        }




        return view;




    }



}
