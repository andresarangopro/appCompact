package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.models.Keys;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentChooseChallenge extends Fragment {


    private ArrayList<Entregable> mDataTest = new ArrayList<>();

    private Bundle args;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ManejoUser mn = new ManejoUser();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_choose_challenge, container, false);
        mn.inicializatedFireBase();
        initXml();
        return view;
    }

    private void initXml(){
        recycler = (RecyclerView) view.findViewById(R.id.rv_desafíos);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new RecyclerAdapterDates(view.getContext(), mDataTest);
        recycler.setAdapter(adapter);

        inicializeArray();
    }

    private void inicializeArray(){
        args = getArguments();
        if (args != null) {
            String idReto = args.getString(Keys.idReto);
           mn.databaseReference.child("Retos").child(idReto).addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   for ( DataSnapshot entregable: dataSnapshot.child("entregable").getChildren()) {
                            Entregable ent = entregable.getValue(Entregable.class);
                            mDataTest.add(ent);
                   }
                   adapter = new RecyclerAdapterDates(view.getContext(), mDataTest);
                   recycler.setAdapter(adapter);
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });
        }
    }



}
