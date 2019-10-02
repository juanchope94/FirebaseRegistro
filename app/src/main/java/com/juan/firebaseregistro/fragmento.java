package com.juan.firebaseregistro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class fragmento extends AppCompatActivity {


    final Fragment fragment1 = new Menu_Principal();
    final Fragment fragment2 = new Menu_Favoritos();
    final Fragment fragment3 = new Perfil();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmento);
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.content_main,fragment1,"1").commit();
        fm.beginTransaction().add(R.id.content_main,fragment2,"2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.content_main,fragment3,"3").hide(fragment3).commit();
    }
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
    = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_Principal:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active=fragment1;
                    return true;

                case R.id.menu_Favoritos:
                 // fm.beginTransaction().hide(active).show(fragment2).commit();
                  Intent intento = new Intent(fragmento.this,MainActivity.class);
                  startActivity(intento);
                   // active=fragment2;

                    return true;
                case R.id.perfil:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active=fragment3;
                    return true;


            }

            return false;

    }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
