package com.example.felipearango.appcompact.activitys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.ManejoUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRetos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRetos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRetos extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentRetos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRetos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRetos newInstance(String param1, String param2) {
        FragmentRetos fragment = new FragmentRetos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /////////////////////////////////////
    //Variables
    ////////////////////////////////////
    EditText txtNombre, txtDescripcion, txtFecha, txtIntegrante;
    Spinner spnTipoReto, spnPrivacidad, spnTipoEntrega;
    Button btnPublicarReto;
    String [] tiposReto = {"Seleccione el tipo de reto", "Reto elite", "Reto aula", "Reto rally"};
    String [] tiposPrivacidad = {"Seleccione la privacidad del reto", "Publico", "privado"};
    String [] tiposEntrega = {"Seleccione el formato de entrega", "Video", "Imagenes", "Documentos"};
    String tReto, tPrivacidad, tEntrega;
    ManejoUser mu = new ManejoUser();
    ArrayList<String> integrantes = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_retos, container, false);

        txtNombre = (EditText) view.findViewById(R.id.txtNombreReto);
        txtDescripcion = (EditText) view.findViewById(R.id.txtDescripcionReto);
        txtFecha = (EditText) view.findViewById(R.id.txtFechaEntrega);
        txtIntegrante = (EditText) view.findViewById(R.id.txtIntegrante);
        spnTipoReto =(Spinner) view.findViewById(R.id.spnTipoReto);
        spnTipoEntrega =(Spinner) view.findViewById(R.id.spnTipoEntrega);
        spnPrivacidad =(Spinner) view.findViewById(R.id.spnPrivacidad);
        btnPublicarReto = (Button) view.findViewById(R.id.btnPublicarReto);
        btnPublicarReto.setOnClickListener(this);
        initSpinners();
        integrantes.add("yo");
        integrantes.add("el");
        integrantes.add("ella");

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
     * Metodo para identificar que item me seleccionan en cada spinner
     * @param parent idSpinner
     * @param view
     * @param position
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        switch(parent.getId()){
            case R.id.spnTipoEntrega:
                switch (position){
                    case 0:
                        tEntrega = null;
                        break;
                    case 1:
                        tEntrega = "video";
                        break;
                    case 2:
                        tEntrega = "imagenes";
                        break;
                    case 3:
                        tEntrega = "documentos";
                        break;
                }
            case R.id.spnPrivacidad:
                switch (position){
                    case 0:
                        tPrivacidad = null;
                        break;
                    case 1:
                        tPrivacidad = "publico";
                        break;
                    case 2:
                        tPrivacidad = "privado";
                        break;
                }
            case R.id.spnTipoReto:
                switch (position){
                    case 0:
                        tEntrega = null;
                        break;
                    case 1:
                        tReto = "elite";
                        break;
                    case 2:
                        tReto = "aula";
                        break;
                    case 3:
                        tReto = "rally";
                        break;
                }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPublicarReto:
                Toast.makeText(getContext(), "Funciona", Toast.LENGTH_LONG).show();
                Reto reto = new Reto(txtNombre.getText().toString(), txtDescripcion.getText().toString(), tReto, txtFecha.getText().toString(),
                        integrantes, tEntrega, tPrivacidad);
                String idJob = mu.databaseReference.push().getKey();
                mu.insertar("Retos", idJob, reto);
        }
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

    /**
     * Metodo para inicializar los spinners
     */
    private void initSpinners(){
        spnTipoReto.setOnItemSelectedListener(this);
        spnPrivacidad.setOnItemSelectedListener(this);
        spnTipoEntrega.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposReto);
        spnTipoReto.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposPrivacidad);
        spnPrivacidad.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposEntrega);
        spnTipoEntrega.setAdapter(adapter2);
    }
}
