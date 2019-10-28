package com.juan.firebaseregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class Perfil extends Fragment {
    public static Perfil newInstance() {
        return new Perfil();
    }

    Button btn_cerrar;
    Button btn_cambio_contra;
    Button btn_nombre;

    public Perfil() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_perfil,container,false);
        // crerrar sesion
        btn_cerrar = view.findViewById(R.id.btn_Cerrar_Session);
        btn_cambio_contra = view.findViewById(R.id.btn_Cambiar_Contrasena);
        btn_nombre = view.findViewById(R.id.btn_Nombre_Usuario);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            btn_nombre.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        }

        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();//ésta linea cierra sesión

                    LoginManager.getInstance().logOut();

                    Intent intent = new Intent(getContext(), fragmento.class);// éste intent enviá a la actividad de iniciar sesión
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //corta los enlaces del intent
                    startActivity(intent);//inicia el intent
                    Toast.makeText(getContext(), "Se ha cerrado Sesion Correctamente", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getContext(), "Usted no ha iniciado sesion aun...", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btn_cambio_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                   Intent intn = new Intent(getContext(), CambioContra.class);
                   startActivity(intn);
               }
               else
               {
                   Toast.makeText(getContext(), "Usted no ha iniciado sesion aun...", Toast.LENGTH_SHORT).show();
               }
            }
        });


        return view;
    }
}
