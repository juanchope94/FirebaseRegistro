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

public class RegistroDatos extends AppCompatActivity implements View.OnClickListener {

    EditText edtCorreoReg, edtContrasenaReg;
    Button btnReg;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_datos);
        mAuth = FirebaseAuth.getInstance();
        edtCorreoReg = findViewById(R.id.editCorreoRegisto);
        edtContrasenaReg = findViewById(R.id.editContrasenaRegistro);
        btnReg = findViewById(R.id.btnRegistrar);
        progressDialog = new ProgressDialog(this);
        btnReg.setOnClickListener(this);
    }


        public void registrarDatos()
        {
            String email = edtCorreoReg.getText().toString().trim();
            String password = edtContrasenaReg.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(RegistroDatos.this, "El email es campo obligatorio", Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(password)) {
                Toast.makeText(RegistroDatos.this, "la constraseña es campo obligatorio", Toast.LENGTH_SHORT).show();
            }
            else {

                progressDialog.setMessage("Registrando Datos....");
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegistroDatos.this, "Registro de datos exitoso!", Toast.LENGTH_SHORT).show();

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

