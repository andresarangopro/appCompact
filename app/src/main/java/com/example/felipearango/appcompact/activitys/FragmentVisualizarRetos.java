package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.RecyclerAdapterVisulizeDates;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentVisualizarRetos extends Fragment {

    /////////////////////////////////////
    //Variables
    ////////////////////////////////////
    TextView tvEncargado, tvNombre, tvDescripcion, tvTipo, tvFechas, tvEntregas, tvIndividualGrupo,
                tvNumIntegrantes, tvPrivacidad;
    ArrayList<Reto> lstRetos = new ArrayList<>();
    String rpta;

    private RecyclerView mRecyclerDates;
    private RecyclerAdapterVisulizeDates mDates;
    private ArrayList<Entregable> mData = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<TextView> etData = new ArrayList<>();

    public DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference RetosRef = databaseReference.child("Retos");

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_visualizar_retos, container, false);
        initXml();
        selectData();
        return view;
    }

    private void initXml(){

        tvEncargado =  view.findViewById(R.id.tvEmailEncargado);
        tvNombre =  view.findViewById(R.id.tvNombreReto);
        tvDescripcion =  view.findViewById(R.id.tvDescripcionReto);
        tvTipo =  view.findViewById(R.id.tvTipoReto);
        /*tvFechas =  view.findViewById(R.id.tvFechas);
        tvEntregas = view.findViewById(R.id.tvEntregas);*/
        tvIndividualGrupo = view.findViewById(R.id.tvIndividualGrupo);
        tvNumIntegrantes = view.findViewById(R.id.tvNumIntegrantes);
        tvPrivacidad =  view.findViewById(R.id.tvPrivacidad);

        etData.add(tvEncargado);
        etData.add(tvNombre);
        etData.add(tvDescripcion);
        etData.add(tvIndividualGrupo);
        etData.add(tvNumIntegrantes);
        etData.add(tvPrivacidad);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerDates = view.findViewById(R.id.rv_fechas);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mDates = new RecyclerAdapterVisulizeDates(getContext(), mData);
    }

    private void selectData(){
        RetosRef.orderByChild("nombre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Reto reto = dataSnapshot.getValue(Reto.class);
                Entregable entregable = new Entregable(reto.getNombre(), reto.getTipoReto());
                mData.add(entregable);
                lstRetos.add(reto);
                lstRetos.clear();
                mRecyclerDates.setAdapter(mDates);
                mData.clear();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
