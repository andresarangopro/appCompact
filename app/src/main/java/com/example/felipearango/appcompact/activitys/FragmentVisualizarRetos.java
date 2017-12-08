package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.RecyclerAdapterChallenges;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;
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

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference RetosRef = databaseReference.child("Retos");

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_visualizar_retos, container, false);
        initXml();

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

        textTextView();

        dra();
    }

    private void textTextView(){
        Reto reto = RecyclerAdapterChallenges.retoChallenge;
        tvEncargado.setText(reto.getEmailResponsable());
        tvNombre.setText(reto.getNombre());
        tvDescripcion.setText(reto.getDescripcion());
        tvTipo.setText(reto.getTipoReto());
        tvIndividualGrupo.setText(reto.getIndividualOGrupo());
        tvNumIntegrantes.setText(reto.getNumIntegrante());
        tvPrivacidad.setText(reto.getPrivacidad());
        mData = setArray(reto.getFechasEntrega(), reto.getTipoEntrega());
    }

    private ArrayList<Entregable> setArray(ArrayList<String> fechasEntrega, ArrayList<String> tipoEntrega) {
        ArrayList<Entregable> entregables = new ArrayList<>();

        try {
            for (int i = 0; i < fechasEntrega.size(); i++) {
                entregables.add(new Entregable(fechasEntrega.get(i), tipoEntrega.get(i)));
            }
        }catch (Exception e){
            Log.e(e.getMessage(), "No se cargó bien el arraylist");
        }
        return entregables;
    }
/*
    private void selectData(){
        RetosRef.orderByChild("nombre").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Reto reto = dataSnapshot.getValue(Reto.class);
                Entregable entregable = new Entregable(reto.getNombre(), reto.getTipoReto());
                mData.add(entregable);
                lstRetos.add(reto);
                lstRetos.clear();
                recycler.setAdapter(mDates);
                mData.clear();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/
    private void dra(){

        recycler =  view.findViewById(R.id.rv_fechas);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new RecyclerAdapterDates(view.getContext(), mData);
        recycler.setAdapter(adapter);
    }

}
