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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
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
    DatabaseReference aDatabasefe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        photoImageView = (ImageView) findViewById(R.id.imageUsuario);
        nameTextView = (TextView) findViewById(R.id.txtNombre);
        emailTextView = (TextView) findViewById(R.id.txtEmail);

        mAuthf = FirebaseAuth.getInstance();
        aDatabasefe = FirebaseDatabase.getInstance().getReference();

        Bundle datos=  getIntent().getExtras();
        String pos= datos.getString("imagen");
        String email= datos.getString("email");
        Glide.with(this).load(pos).into(photoImageView);
        emailTextView.setText(email);






    }

    @Override
    protected void onStart() {
        super.onStart();
        aDatabasefe.child(mAuthf.getCurrentUser().getUid()).child("Usuarios").child("correo").setValue(strDate);

    }
}
