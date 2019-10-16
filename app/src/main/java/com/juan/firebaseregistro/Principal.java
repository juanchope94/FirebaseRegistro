package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal extends AppCompatActivity {



    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String strDate = dateFormat.format(date).toString();
    FirebaseAuth mAuthf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        photoImageView = (ImageView) findViewById(R.id.imageUsuario);
        nameTextView = (TextView) findViewById(R.id.txtNombre);
        emailTextView = (TextView) findViewById(R.id.txtEmail);

        mAuthf = FirebaseAuth.getInstance();

        Bundle datos=  getIntent().getExtras();
        String pos= datos.getString("imagen");
        String email= datos.getString("email");
        Glide.with(this).load(pos).into(photoImageView);
        emailTextView.setText(email);






    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
