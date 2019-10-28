package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class CambioContra extends AppCompatActivity {

    EditText edt_email;
    Button btn_confirma;
    private String email="";
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contra);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        edt_email = findViewById(R.id.edtCorreo);
        btn_confirma = findViewById(R.id.btnConfirmar);

        btn_confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_email.getText().toString().trim();

                if (!email.isEmpty()) {
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassWord();
                } else {
                    Toast.makeText(CambioContra.this, "Debe ingresar el correo", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

        private void resetPassWord()
        {
            mAuth.setLanguageCode("es"); // se manda el mensaje en español
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(CambioContra.this, "Se ha enviado un correo para reestablecer tu contraseña", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(CambioContra.this, "No se pudo enviar el correo de reestablecer contraseña", Toast.LENGTH_SHORT).show();
                    }
                    mDialog.dismiss();
                }
            });
        }


}
