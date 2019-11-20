package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroDatos extends AppCompatActivity implements View.OnClickListener {



    EditText edtCorreoReg, edtContrasenaReg, edtNombreUsuario;
    Button btnReg;
    FirebaseAuth mAuth;
     FirebaseFirestore db = FirebaseFirestore.getInstance();



    String email = "";
    String nombreUsuario = "";
    String password= "";
    String roll="Usuario";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String strDate = dateFormat.format(date).toString();
    //myRef.child("datetime").setValue(strDate);


    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_datos);
        mAuth = FirebaseAuth.getInstance();
       //aDatabase = FirebaseDatabase.getInstance().getReference();
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
                                    map.put("rol",roll);
                                   map.put("fechaIngreso",strDate);

                                    String id = mAuth.getCurrentUser().getUid();
                                    db.collection("users")
                                            .add(map)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    startActivity(new Intent(RegistroDatos.this,fragmento.class));
                                                    finish();
                                                }
                                            })

                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(RegistroDatos.this, "Error al registrar datos ", Toast.LENGTH_SHORT).show();
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

