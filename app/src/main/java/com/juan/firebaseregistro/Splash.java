package com.juan.firebaseregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imgSplash = (ImageView) findViewById(R.id.img_Splash);
        Glide.with(this)

                .load(R.drawable.eventime)
                .into(imgSplash);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(Splash.this, fragmento.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
