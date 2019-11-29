package com.juan.firebaseregistro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class fragmento extends AppCompatActivity implements Comunicador {

/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
if ( instanceof BommMenu){
    ((BommMenu) favoritos).onWindowFocusChanged(hasFocus);

}

    }*/

    private BottomNavigationView bnview;
   // final Fragment fragment1 = new Menu_Principal();
   //final Fragment fragment2 = new Menu_Favoritos();
   //final Fragment fragment3 = new Perfil();
    Favoritos favoritos = new Favoritos();
    final FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmento);

        init();
        cambiarFragment(Menu_Principal.newInstance());
        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_Principal:
                        cambiarFragment(Menu_Principal.newInstance());
                        return true;

                    case R.id.perfil:
                        cambiarFragmentper(Perfil.newInstance());
                        return true;


                }

                return false;
            }
        });



    }

    private void init() {
        this.bnview = findViewById(R.id.navigation);
    }

    @Override
    public void enviardatos(Evento eventoco) {


        favoritos = new Favoritos();
        Bundle bundleenvio = new Bundle();
        bundleenvio.putSerializable("objeto",eventoco);
        favoritos.setArguments(bundleenvio);
        // cambiarFragmentdetalle(Favoritos.newInstance());

        fm.beginTransaction().replace(R.id.content_main,favoritos).addToBackStack(null).commit();


    }
    private void cambiarFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void cambiarFragmentper(Fragment fragment1) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment1);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
