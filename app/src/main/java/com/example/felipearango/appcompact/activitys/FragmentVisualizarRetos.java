package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.Keys;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
    private RecyclerAdapterDates mDates;
    private LinearLayoutManager mLinearLayoutManager;

    private ManejoUser mn =new  ManejoUser();
    private FragmentTransaction transaction;
    private ArrayList<Entregable> mDataTest = new ArrayList<>();
    private Reto reto= null;
    private Bundle args;


    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_visualizar_retos, container, false);
        transaction = getFragmentManager().beginTransaction();
        mn.inicializatedFireBase();
        initXml();
        selectEntregables();
        return view;
    }

    private void initXml(){
       mRecyclerDates = (RecyclerView)view.findViewById(R.id.rv_fechas) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);

        mDates = new  RecyclerAdapterDates(view.getContext(), mDataTest);
        mRecyclerDates.setAdapter( mDates);

        tvEncargado =  view.findViewById(R.id.tvEmailEncargado);
        tvNombre =  view.findViewById(R.id.tvNombreReto);
        tvDescripcion =  view.findViewById(R.id.tvDescripcionReto);
        tvTipo =  view.findViewById(R.id.tvTipoReto);
        /*tvFechas =  view.findViewById(R.id.tvFechas);
        tvEntregas = view.findViewById(R.id.tvEntregas);*/
        tvIndividualGrupo = view.findViewById(R.id.tvIndividualGrupo);
        tvNumIntegrantes = view.findViewById(R.id.tvNumIntegrantes);
        tvPrivacidad =  view.findViewById(R.id.tvPrivacidad);
    }


    private void textTextView(){
        tvEncargado.setText(reto.getEmailResponsable());
        tvNombre.setText(reto.getNombre());
        tvDescripcion.setText(reto.getDescripcion());
        tvTipo.setText(reto.getTipoReto());
        tvIndividualGrupo.setText(reto.getIndividualOGrupo());
        tvNumIntegrantes.setText(reto.getNumIntegrante());
        tvPrivacidad.setText(reto.getPrivacidad());
       // mData = setArray(reto.getFechasEntrega(), reto.getTipoEntrega());
    }

    private void selectEntregables(){
        args = getArguments();
        if (args != null) {
            String idReto = args.getString(Keys.idReto);
            mn.databaseReference.child("Retos").child(idReto).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot entregable : dataSnapshot.child("entregable").getChildren()) {
                        Entregable ent = entregable.getValue(Entregable.class);
                        mDataTest.add(ent);
                    }
                    reto = dataSnapshot.getValue(Reto.class);
                    textTextView();
                    mDates  = new RecyclerAdapterDates(view.getContext(), mDataTest);
                    mRecyclerDates.setAdapter(mDates);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}
