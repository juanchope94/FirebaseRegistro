package com.juan.firebaseregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtCorreo, edtContrasena;
    TextView txtCrear;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        edtCorreo=findViewById(R.id.editCorreo);
        edtContrasena=findViewById(R.id.editContrasena);
        txtCrear=findViewById(R.id.textCrear);
        txtCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistroDatos.class));
            }
        });
    }
}
