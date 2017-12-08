package com.example.felipearango.appcompact.activitys;

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
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.RecyclerAdapterRetos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentVisualizarRetos extends Fragment {

    /////////////////////////////////////
    //Variables
    ////////////////////////////////////

    private RecyclerView mRecyclerDates;
    private RecyclerAdapterRetos mDates;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Reto> mData = new ArrayList<>();
    private ManejoUser mn =new  ManejoUser();
    private FragmentTransaction transaction;


    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_visualizar_retos, container, false);
        transaction = getFragmentManager().beginTransaction();
        initXml();
        mn.inicializatedFireBase();
        selectData();

        return view;

    }

    private void initXml(){

        mRecyclerDates = (RecyclerView)view.findViewById(R.id.rv_fechas) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);

        mDates = new RecyclerAdapterRetos(view.getContext(), mData,transaction);
        mRecyclerDates.setAdapter( mDates);
    }

    private void selectData(){
        mn.databaseReference.child("Retos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mData.clear();
                for (DataSnapshot retos : dataSnapshot.getChildren() ) {
                    Reto reto = retos.getValue(Reto.class);
                    Log.e("reto",reto.toString());
                    mData.add(reto);
                }

                mDates = new RecyclerAdapterRetos(view.getContext(), mData,transaction);
                mRecyclerDates.setAdapter( mDates);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
