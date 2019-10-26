package com.juan.firebaseregistro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class Menu_Favoritos extends Fragment {

    public static Menu_Favoritos newInstance() {
        return new Menu_Favoritos();
    }
    public Menu_Favoritos()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {


        View view = inflater.inflate(R.layout.activity_menu__favoritos,container,false);
        Toast.makeText(getContext(), "Pruebaa", Toast.LENGTH_SHORT).show();
        return view;
    }
}
