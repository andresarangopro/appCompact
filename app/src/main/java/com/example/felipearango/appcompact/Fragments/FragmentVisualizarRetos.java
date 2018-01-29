package com.example.felipearango.appcompact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Comentario;
import com.example.felipearango.appcompact.clases.DatosC;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.clases.Usuario_estudiante;
import com.example.felipearango.appcompact.models.RecyclerAdapterComentarios;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;
import com.example.felipearango.appcompact.util.Keys;
import com.example.felipearango.appcompact.util.ManejoUser;
import com.example.felipearango.appcompact.util.Util;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentVisualizarRetos extends Fragment implements View.OnClickListener {

    /////////////////////////////////////
    //Variables
    ////////////////////////////////////
    TextView tvEncargado, tvNombre, tvDescripcion, tvTipo, tvFechas, tvEntregas, tvIndividualGrupo,
                tvNumIntegrantes, tvPrivacidad;
    EditText etComentario;
    ArrayList<Reto> lstRetos = new ArrayList<>();
    String rpta;

    private RecyclerView mRecyclerDates;
    private RecyclerAdapterDates mDates;
    private LinearLayoutManager mLinearLayoutManager;

    private RecyclerView mRecyclerComent;
    private RecyclerAdapterComentarios mComent;
    private LinearLayoutManager mLinearLayoutMan;

    private ManejoUser mn =new  ManejoUser();
    private ImageView iVsendComentario;
    private FragmentTransaction transaction;
    private ArrayList<Entregable> mDataTest = new ArrayList<>();
    private ArrayList<DatosC> listComents = new ArrayList<>();
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
        mRecyclerDates = view.findViewById(R.id.rv_fechas) ;
        mRecyclerDates.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mDates = new  RecyclerAdapterDates(view.getContext(), mDataTest);
        mRecyclerDates.setAdapter( mDates);
        listComents.add(new DatosC("Nombre","Comentario","img"));
        mRecyclerComent= (RecyclerView)view.findViewById(R.id.rv_coment) ;
        mRecyclerComent.setHasFixedSize(true);
        mLinearLayoutMan = new LinearLayoutManager(view.getContext());
        mRecyclerComent.setLayoutManager(mLinearLayoutMan);
        mComent = new RecyclerAdapterComentarios(view.getContext(), listComents);
        mRecyclerComent.setAdapter( mComent);
        tvEncargado =  view.findViewById(R.id.tvEmailEncargado);
        tvNombre =  view.findViewById(R.id.tvNombreReto);
        tvDescripcion =  view.findViewById(R.id.tvDescripcionReto);
        tvTipo =  view.findViewById(R.id.tvTipoReto);
        tvIndividualGrupo = view.findViewById(R.id.tvIndividualGrupo);
        tvNumIntegrantes = view.findViewById(R.id.tvNumIntegrantes);
        tvPrivacidad =  view.findViewById(R.id.tvPrivacidad);
        etComentario = view.findViewById(R.id.etComentar);
        iVsendComentario = view.findViewById(R.id.iVsendComentario);
        iVsendComentario.setOnClickListener(this);
    }


    private void textTextView(){
        tvEncargado.setText(reto.getEmailResponsable());
        tvNombre.setText(reto.getNombre());
        tvDescripcion.setText(reto.getDescripcion());
        tvTipo.setText(reto.getTipoReto());
        tvIndividualGrupo.setText(reto.getIndividualOGrupo());
        tvNumIntegrantes.setText(reto.getNumIntegrante());
        tvPrivacidad.setText(reto.getPrivacidad());
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
                    selectComentarios(reto.getId());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void selectComentarios(String idReto){
        mn.databaseReference.child("Retos").child(idReto).child("comentarios").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Comentario comentario = dataSnapshot.getValue(Comentario.class);
                mn.getUser(comentario.getUidUser()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Usuario_estudiante ue = dataSnapshot.child(comentario.getUidUser()).getValue(Usuario_estudiante.class);
                        if(ue != null){
                            DatosC datosAdapterComent = new DatosC(ue.getCorreo(),comentario.getComentario(),ue.getFoto());
                            listComents.add(datosAdapterComent);
                            mComent = new RecyclerAdapterComentarios(view.getContext(), listComents);
                            mRecyclerComent.setAdapter( mComent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iVsendComentario:{
                FirebaseUser user = mn.firebaseAuth.getCurrentUser();
                Comentario comentario = new Comentario(Util.geteTxt(etComentario), user.getUid());

                mn.addComentario("Retos",reto.getId(),"comentarios",comentario);

            }
        }
    }
}
