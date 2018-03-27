package com.example.felipearango.appcompact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.activitys.Activity_Principal;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.RecyclerAdapterRetos;
import com.example.felipearango.appcompact.util.ManejoUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentChooseChallenge extends Fragment {

    private ArrayList<Reto> mData = new ArrayList<>();
    private ManejoUser mn =new  ManejoUser();
    private FragmentTransaction transaction;
    private View view;
    private int tChallenge;

    private RecyclerView mRecyclerDates;
    private RecyclerAdapterRetos mDates;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_challenge, container, false);
        Activity_Principal.toolbar.setTitle("Mis Retos");
        mn.inicializatedFireBase();
        transaction = getFragmentManager().beginTransaction();
        if(getArguments() != null){
            tChallenge = getArguments().getInt(Activity_Principal.TYPE_CHALLENGE);
        }

        initXml();
        return view;
    }

    private void initXml(){
        mRecyclerDates = view.findViewById(R.id.rv_desafíos) ;
        mRecyclerDates.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mDates = new RecyclerAdapterRetos(view.getContext(), mData,transaction);
        mRecyclerDates.setAdapter( mDates);
        selectData();
    }

    private void selectData(){
        mn.databaseReference.child("Retos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mData.clear();
                if(tChallenge == 1) {
                    for (DataSnapshot retos : dataSnapshot.getChildren()) {
                        Reto reto = retos.getValue(Reto.class);
                        Log.e("reto", reto.toString());
                        mData.add(reto);
                    }
                } else{
                    for (DataSnapshot retos : dataSnapshot.getChildren()) {
                        Reto reto = retos.getValue(Reto.class);
                        Log.e("reto", reto.toString());
                        //m if(existInAula(reto.getLstIntegrantes())) mData.add(reto);

                    }
                }
                mDates = new RecyclerAdapterRetos(view.getContext(), mData, transaction);
                mRecyclerDates.setAdapter(mDates);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean existInAula(ArrayList<String> lst){
        for (String user:lst) {
            if(mn.firebaseUser.getEmail().equals(user)) return true;
        }
        return false;
    }
}
