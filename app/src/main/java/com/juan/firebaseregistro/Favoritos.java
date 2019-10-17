package com.juan.firebaseregistro;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
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


    TextView nombreevento, descripcionevento, numeroTelefono, direccionEvento, fechaEvento;
    ImageView imagenevento;
    ImageButton botonLlamar, botonCalendario;
    String telefono;
    String titulo;
    final private int REQUIERE =111;


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
        fechaEvento =(TextView) view.findViewById(R.id.txt_Fecha_Del_Evento);


        Bundle eventodetall  = getArguments();
        Evento eventomues = null;
        if (getArguments() != null){

            eventomues = (Evento) eventodetall.getSerializable("objeto");
            nombreevento.setText(eventomues.getNombre());
            //  Glide.with(context).load(item.getUrlImagen()).into(foto);
            Glide.with(getActivity()).load(eventomues.getUrlImagen()).into(imagenevento);
            descripcionevento.setText(eventomues.getDescripcion());
            direccionEvento.setText(eventomues.getDireccion());
            fechaEvento.setText(eventomues.getFecha());
            numeroTelefono.setText(eventomues.getTelefono());
            telefono = eventomues.getTelefono();
            titulo = eventomues.getNombre();
            final String fecha = eventomues.getFecha();

            String[] parts = fecha.split("-");
            final String part1 = parts[0];
            final String part2 = parts[1];
            final String part3 = parts[2];





            botonLlamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(getContext(), "presiono", Toast.LENGTH_SHORT).show();



                    int llamar = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUIERE);
                    Uri uri = Uri.parse("tel:" + telefono);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);

                    if (llamar != PackageManager.PERMISSION_GRANTED) {





                    }










                }
            });

            botonCalendario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Toast.makeText(getContext(), "fun"+part1+part2+part3, Toast.LENGTH_SHORT).show();

                    Calendar cal = Calendar.getInstance();

                    Intent intento = null;


                            cal.set(Calendar.YEAR, Integer.parseInt(part1));
                            cal.set(Calendar.MONTH, Integer.parseInt(part2));
                            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(part3));

                            Intent intentoc = new Intent(Intent.ACTION_EDIT);
                            intentoc.setType("vnd.android.cursor.item/event");
                            intentoc.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis());
                            intentoc.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,cal.getTimeInMillis()+ 60 +60 + 1000);


                            intentoc.putExtra(CalendarContract.Events.ALL_DAY, "1");
                            intentoc.putExtra(CalendarContract.Events.TITLE, titulo);
                            intentoc.putExtra(CalendarContract.Events.DESCRIPTION, titulo);
                            intentoc.putExtra(CalendarContract.Events.EVENT_LOCATION, titulo);

                            startActivity(intentoc);


                }
            });
        }




        return view;




    }



}
