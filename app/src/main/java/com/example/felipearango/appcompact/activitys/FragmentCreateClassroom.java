package com.example.felipearango.appcompact.activitys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.felipearango.appcompact.R;


public class FragmentCreateClassroom extends Fragment implements View.OnClickListener{

    Button btnPublicarAula;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_classroom, container, false);
        btnPublicarAula = view.findViewById(R.id.btnPublicarAula);
        btnPublicarAula.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPublicarAula:{

                Activity_Principal activity = (Activity_Principal)view.getContext();
                FragmentClassroomCode fragmentClassroomCode = new FragmentClassroomCode();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragments, fragmentClassroomCode).addToBackStack(null).commit();


                break;
            }
        }
    }
}
