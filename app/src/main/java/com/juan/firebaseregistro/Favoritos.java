package com.juan.firebaseregistro;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Map;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
    ImageView imagenevento;
    ImageButton botonLlamar, botonCalendario, botonubicacion, btn_interes;;
    Button incripcion;
    String telefono;
    String titulo, descripcion;
    int  duracion= 1;

    public static String  latitud;
    public static String longitud;
    public static String urlformularioo;
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
        imagenevento = (ImageView) view.findViewById(R.id.img_Favoritos);
        descripcionevento =(TextView) view.findViewById(R.id.txt_Descripcion_Del_Evento);
        direccionEvento =(TextView) view.findViewById(R.id.txt_Dirreccion);
        numeroTelefono =(TextView) view.findViewById(R.id.txt_Numero_Telefonico);
        botonLlamar =(ImageButton) view.findViewById(R.id.btn_Llamar);
        botonCalendario =(ImageButton) view.findViewById(R.id.btn_Calendario);
        incripcion = (Button)view.findViewById(R.id.btnIncripcion);
        fechaEvento =(TextView) view.findViewById(R.id.txt_Fecha_Del_Evento);
        botonubicacion =(ImageButton) view.findViewById(R.id.btn_Ubicacion);
        btn_interes =(ImageButton)  view.findViewById(R.id.btn_Me_Interesa);

        //   boomMenuButton =(BoomMenuButton) view.findViewById(R.id.idboom);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            btn_interes.setVisibility(view.VISIBLE);
        }
        else {
            btn_interes.setVisibility(view.INVISIBLE);
        }

        Bundle eventodetall  = getArguments();

        Evento eventomues = null;
        if (getArguments() != null) {

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
            descripcion = eventomues.getDescripcion();
            latitud = eventomues.getLatitud();
            longitud = eventomues.getLongitud();
            urlformularioo = eventomues.getUrlInscripcion();


            final String id= eventomues.getId();
            final String fecha = eventomues.getFecha();

            String[] parts = fecha.split("-");
            final String part1 = parts[0];
            final String part2 = parts[1];
            final String part3 = parts[2];

            incripcion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent formular = new Intent(getContext(), Formulario.class);
                    startActivity(formular);
                }
            });

            btn_interes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("idEvento",id);
                    map.put("correo", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    map.put("nombre", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Interes")
                            .add(map)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                    Toast.makeText(getContext(), "Te intereso este evento!", Toast.LENGTH_LONG).show();
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                }
            });


            botonubicacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapas = new Intent(getContext(), Ubication.class);
                    startActivity(mapas);
                    Toast.makeText(getContext(), "ub:"+latitud
                            , Toast.LENGTH_SHORT).show();


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

    }

