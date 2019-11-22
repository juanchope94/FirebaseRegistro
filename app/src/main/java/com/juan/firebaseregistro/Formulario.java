package com.juan.firebaseregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Formulario extends AppCompatActivity {

private WebView m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        m = findViewById(R.id.web);
        WebSettings webSettings =m.getSettings();
        webSettings.setJavaScriptEnabled(true);
        m.setWebViewClient(new WebViewClient());
        m.loadUrl(Favoritos.urlformularioo);

    }
}
