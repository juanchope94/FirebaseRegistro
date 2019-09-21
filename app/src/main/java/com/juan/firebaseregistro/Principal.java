package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;

public class Principal extends AppCompatActivity {



    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView emailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        photoImageView = (ImageView) findViewById(R.id.imageUsuario);
        nameTextView = (TextView) findViewById(R.id.txtNombre);
        emailTextView = (TextView) findViewById(R.id.txtEmail);

        Bundle datos=  getIntent().getExtras();
        String pos= datos.getString("imagen");
        String email= datos.getString("email");
        Glide.with(this).load(pos).into(photoImageView);
        emailTextView.setText(email);



    }


}
