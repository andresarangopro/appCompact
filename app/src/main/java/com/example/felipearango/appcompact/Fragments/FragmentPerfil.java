package com.example.felipearango.appcompact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.activitys.Activity_Principal;


public class FragmentPerfil extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        Activity_Principal.toolbar.setTitle("Perfil");
        return view;
    }

}
