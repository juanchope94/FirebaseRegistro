package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroDatos extends AppCompatActivity implements View.OnClickListener {

    EditText edtCorreoReg, edtContrasenaReg, edtNombreUsuario;
    Button btnReg;
  //private FirebaseAuth mAuth;
     FirebaseAuth mAuth;
   DatabaseReference aDatabase;


    String email = "";
    String nombreUsuario = "";
    String password= "";



    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_datos);
        mAuth = FirebaseAuth.getInstance();
      aDatabase = FirebaseDatabase.getInstance().getReference();
        edtCorreoReg = findViewById(R.id.editCorreoRegisto);
        edtContrasenaReg = findViewById(R.id.editContrasenaRegistro);
        btnReg = findViewById(R.id.btnRegistrar);
        progressDialog = new ProgressDialog(this);
        edtNombreUsuario = findViewById(R.id.editNombreUsuario);

        btnReg.setOnClickListener(this);
    }


        public void registrarDatos()
        {
             email = edtCorreoReg.getText().toString().trim();
             password = edtContrasenaReg.getText().toString().trim();
             nombreUsuario = edtNombreUsuario.getText().toString();



            if (TextUtils.isEmpty(email)) {
                Toast.makeText(RegistroDatos.this, "El email es campo obligatorio", Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(password)) {
                Toast.makeText(RegistroDatos.this, "la constraseña es campo obligatorio", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(nombreUsuario)) {
                Toast.makeText(this, "El nombre es Obligatorio", Toast.LENGTH_SHORT).show();

        }

            else {

                progressDialog.setMessage("Registrando Datos....");
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // si la tarea se relalizo
                                  Map<String,Object> map = new HashMap<>();
                                    map.put("nombre",nombreUsuario);
                                    map.put("correo",email);
                                    map.put("fechaIngreso", ServerValue.TIMESTAMP);
                                  //  map.put("nombre",nombreUsuario);

                                    String id = mAuth.getCurrentUser().getUid();
                                    aDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task2) {
                                            if (task2.isSuccessful()){
                                                startActivity(new Intent(RegistroDatos.this,MainActivity.class));
                                                  finish();//evita que el usuario vuelva a la pantalla de registro cuand ya este registrado
                                            }else {
                                                Toast.makeText(RegistroDatos.this, "Error al registrar datos ", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });



                                } else {
                                    // If sign in fails, display a message to the user.
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                    {

                                        Toast.makeText(RegistroDatos.this, "El usuario ya se encuentra registrado!", Toast.LENGTH_LONG).show();

                                    }
                                    else
                                    Toast.makeText(RegistroDatos.this, "Upss a ocurrido un error "+task.getException(), Toast.LENGTH_LONG).show();
                                }

                                progressDialog.dismiss();

                                // ...
                            }
                        });

            }

        }



    @Override
    public void onClick(View v) {

        registrarDatos();

    }
}

