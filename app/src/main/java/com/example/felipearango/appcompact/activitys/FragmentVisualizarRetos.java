package com.example.felipearango.appcompact.activitys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.RecyclerAdapterVisulizeDates;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentVisualizarRetos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentVisualizarRetos#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentVisualizarRetos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentVisualizarRetos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentVisualizarRetos newInstance(String param1, String param2) {
        FragmentVisualizarRetos fragment = new FragmentVisualizarRetos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_visualizar_retos, container, false);

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



        RetosRef.orderByChild("nombre").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Reto reto = dataSnapshot.getValue(Reto.class);
                Entregable entregable = new Entregable(reto.getNombre(), reto.getTipoReto());
                mData.add(entregable);
                lstRetos.add(reto);
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
        lstRetos.clear();
        mRecyclerDates.setAdapter(mDates);
        mData.clear();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
