package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtCorreo, edtContrasena;
    TextView txtCrear;
    Button btnIniciar;

    public static final int SIGN_IN_CODE = 777;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    private GoogleApiClient googleApiClient;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        edtCorreo=findViewById(R.id.editCorreo);
        edtContrasena=findViewById(R.id.editContrasena);
        txtCrear=findViewById(R.id.textCrear);
        btnIniciar=findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);
        txtCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistroDatos.class));
            }
        });

    }



    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.btnIniciar) {
            iniciarSesion();
        }

        if(v.getId()==R.id.btnGmail) {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent, SIGN_IN_CODE);
        }
    }

    public void iniciarSesion()
    {

        final String email = edtCorreo.getText().toString().trim();
        String password = edtContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MainActivity.this, "El email es campo obligatorio", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "la constraseña es campo obligatorio", Toast.LENGTH_SHORT).show();
        }
        else {

            progressDialog.setMessage("Iniciando sesion....");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                int pos= email.indexOf("@");
                                String nombre= email.substring(0,pos);
                                // Sign in success, update UI with the signed-in user's information
                                Intent pasar= new Intent(MainActivity.this,Principal.class);
                                pasar.putExtra("nombre",nombre);
                                startActivity(pasar);
                                Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Upss a ocurrido un error "+task.getException(), Toast.LENGTH_LONG).show();
                            }

                            progressDialog.dismiss();

                            // ...
                        }
                    });

        }


    }


}
