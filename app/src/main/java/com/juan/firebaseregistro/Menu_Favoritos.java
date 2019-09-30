package com.juan.firebaseregistro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class Menu_Favoritos extends Fragment {

    public Menu_Favoritos()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {


        View view = inflater.inflate(R.layout.activity_menu__favoritos,container,false);
        return view;
    }
}
