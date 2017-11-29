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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.felipearango.appcompact.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRetos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRetos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRetos extends Fragment implements AdapterView.OnItemSelectedListener {
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
    String [] tiposReto = {"Seleccione el tipo de reto", "Reto elite", "Reto aula", "Reto rally"};
    String [] tiposPrivacidad = {"Seleccione la privacidad del reto", "Publico", "privado"};
    String [] tiposEntrega = {"Seleccione el formato de entrega", "Video", "Imagenes", "Documentos"};
    String tReto, tPrivacidad, tEntrega;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initComponents();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retos, container, false);
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

    private void initComponents(){
        txtNombre = (EditText) getView().findViewById(R.id.txtNombreReto);
        txtDescripcion = (EditText) getView().findViewById(R.id.txtDescripcionReto);
        txtFecha = (EditText) getView().findViewById(R.id.txtFechaEntrega);
        txtIntegrante = (EditText) getView().findViewById(R.id.txtIntegrante);
        spnTipoReto =(Spinner) getView().findViewById(R.id.spnTipoReto);
        spnTipoEntrega =(Spinner) getView().findViewById(R.id.spnTipoEntrega);
        spnPrivacidad =(Spinner) getView().findViewById(R.id.spnPrivacidad);
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
