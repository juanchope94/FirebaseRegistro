package com.juan.firebaseregistro;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Calendar;

public class Favoritos extends Fragment {
    public static Favoritos newInstance()
    {
        return new Favoritos();
    }

    TextView nombreevento, descripcionevento, numeroTelefono, direccionEvento, fechaEvento;
    PhotoView imagenevento;
    ImageButton botonLlamar, botonCalendario, botonubicacion;
    Button incripcion;
    String telefono;
    String titulo, descripcion;

    int  duracion= 1;

    public static String  latitud;
    public static String longitud;
    public static String titulomapa;
    final private int REQUIERE =111;
    private static final String TAG = "MyActivity";

    //private boolean init =false;
    //private BoomMenuButton boomMenuButton;

   public Favoritos() {

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState ) {


        final View view = getLayoutInflater().inflate(R.layout.activity_favoritos, container, false);

        nombreevento = (TextView) view.findViewById(R.id.txt_Nombre_Del_Evento_Favoritos);
        imagenevento = (PhotoView) view.findViewById(R.id.img_Favoritos);
        descripcionevento =(TextView) view.findViewById(R.id.txt_Descripcion_Del_Evento);
        direccionEvento =(TextView) view.findViewById(R.id.txt_Dirreccion);
        numeroTelefono =(TextView) view.findViewById(R.id.txt_Numero_Telefonico);
        botonLlamar =(ImageButton) view.findViewById(R.id.btn_Llamar);
        botonCalendario =(ImageButton) view.findViewById(R.id.btn_Calendario);
incripcion = (Button)view.findViewById(R.id.btnIncripcion);
        fechaEvento =(TextView) view.findViewById(R.id.txt_Fecha_Del_Evento);
        botonubicacion =(ImageButton) view.findViewById(R.id.btn_Ubicacion);
     //   boomMenuButton =(BoomMenuButton) view.findViewById(R.id.idboom);


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
            titulomapa = eventomues.getNombre();
            descripcion= eventomues.getDescripcion();
            latitud = eventomues.getLatitud();
            longitud= eventomues.getLongitud();
            final String fecha = eventomues.getFecha();

            String[] parts = fecha.split("-");
            final String part1 = parts[0];
            final String part2 = parts[1];
            final String part3 = parts[2];

            incripcion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent formular= new Intent(getContext(),Formulario.class);
                    startActivity(formular);
                }
            });

botonubicacion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent mapas = new Intent(getContext(),Ubication.class);
        startActivity(mapas);
    }
});


            botonLlamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                    Uri uri = Uri.parse("tel:" + telefono);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                   // if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                  //  {return;}
                    startActivity(intent);




                }
            });

            botonCalendario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Toast.makeText(getContext(), "fun"+part1+part2+part3, Toast.LENGTH_SHORT).show();

                    Calendar cal = Calendar.getInstance();

                    Intent intento = null;



                            cal.set(Calendar.YEAR, Integer.parseInt(part1));
                            cal.set(Calendar.MONTH, Integer.parseInt(part2)-1) ;
                            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(part3));


                            Intent intentoc = new Intent(Intent.ACTION_EDIT);
                            intentoc.setType("vnd.android.cursor.item/event");
                            intentoc.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis());
                            intentoc.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,cal.getTimeInMillis()+ 60 +60 + 1000);


                    intentoc.putExtra(CalendarContract.Events.ALL_DAY,duracion);
                            intentoc.putExtra(CalendarContract.Events.TITLE, titulo);

                            intentoc.putExtra(CalendarContract.Events.DESCRIPTION, descripcion);

                            intentoc.putExtra(CalendarContract.Events.EVENT_LOCATION, titulo);


                            startActivity(intentoc);


                }
            });
        }








        return view;




    }

/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {



        int [][]  suButtonColors = new int[3][2];
        for (int i = 0 ; i<3;i++ )

        {

            suButtonColors[i][1] = ContextCompat.getColor(getContext(), R.color.Azul_Botones);
            suButtonColors[i][0] = Util.getInstance().getPressedColor(suButtonColors[i][1]);

        }

        new BoomMenuButton.Builder()
                .addSubButton (ContextCompat.getDrawable(getContext(),R.drawable.ic_gps),suButtonColors[0],"Menu Boton")
                .addSubButton (ContextCompat.getDrawable(getContext(),R.drawable.ic_bienestar),suButtonColors[0],"Recurso de Codigo")


                .button(ButtonType.CIRCLE)
                .boom(BoomType.LINE)
                .place(PlaceType.CIRCLE_8_1)
                .subButtonTextColor(ContextCompat.getColor(getContext(),R.color.default_boom_button_color))
                .subButtonsShadow(Util.getInstance().dp2px(2),Util.getInstance().dp2px(2))
                .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                                      @Override
                                      public void onClick(int buttonIndex) {

                                          switch (buttonIndex){

                                              case 0:
                                                  Toast.makeText(getContext(), "Primer boton", Toast.LENGTH_SHORT).show();
                                                  break;

                                              case 1:
                                                  Toast.makeText(getContext(), "segundo boton", Toast.LENGTH_SHORT).show();
                                                  break;




                                          }
                                      }
                                  }

                )
                .init(boomMenuButton);



    }*/


    }

