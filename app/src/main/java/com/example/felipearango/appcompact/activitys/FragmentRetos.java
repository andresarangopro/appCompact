package com.example.felipearango.appcompact.activitys;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.felipearango.appcompact.Entregable;
import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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


    private RecyclerView mRecyclerDates;
    private RecyclerAdapterDates mDates;
    private ArrayList<Entregable> mData = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private Calendar myCalendar = Calendar.getInstance();

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
    EditText txtNombre, txtDescripcion, txtFecha, txtNumIntegrante;
    Spinner spnTipoReto, spnPrivacidad, spnTipoEntrega, spnIndividualGrupo;
    Button btnPublicarReto, addDate;
    String [] tiposReto = {"Seleccione el tipo de reto", "Reto elite", "Reto aula", "Reto rally"};
    String [] tiposPrivacidad = {"Seleccione la privacidad del reto", "Publico", "privado"};
    String [] tiposEntrega = {"Seleccione el formato de entrega", "Video", "Imagenes", "Documentos"};
    String [] individualGrupo = {"Reto individual o en grupo?", "Individual", "Grupo"};
    String tReto, tPrivacidad, tEntrega, individualOGrupo;
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
        txtFecha = (EditText) view.findViewById(R.id.etDates);
        txtFecha.setOnClickListener(this);
        addDate = view.findViewById(R.id.btnAdd);
        addDate.setOnClickListener(this);
        txtNumIntegrante = (EditText) view.findViewById(R.id.txtNumIntegrante);
        spnTipoReto =(Spinner) view.findViewById(R.id.spnTipoReto);
        spnTipoEntrega =(Spinner) view.findViewById(R.id.spnTipoEntrega);
        spnPrivacidad =(Spinner) view.findViewById(R.id.spnPrivacidad);
        spnIndividualGrupo = (Spinner) view.findViewById(R.id.spnIndividualGrupo);
        btnPublicarReto = (Button) view.findViewById(R.id.btnPublicarReto);
        btnPublicarReto.setOnClickListener(this);
        initSpinners();

        //Recycler -----------------------------

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerDates = view.findViewById(R.id.rv_fechas);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mDates = new RecyclerAdapterDates(getContext(), mData);
        mRecyclerDates.setAdapter(mDates);

        //Calendar -------------------------------------------



        return view;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtFecha.setText(sdf.format(myCalendar.getTime()));
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
            case R.id.spnIndividualGrupo:
                switch (position){
                    case 0:
                        individualOGrupo = null;
                        break;
                    case 1:
                        individualOGrupo = "individual";
                        break;
                    case 2:
                        individualOGrupo = "grupo";
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
            case R.id.btnPublicarReto:{
                mData.add(0, txtFecha.getText().toString());
                Toast.makeText(getContext(), mData.toString(), Toast.LENGTH_LONG).show();
                Reto reto = new Reto(txtNombre.getText().toString(), txtDescripcion.getText().toString(), tReto, mData,
                        txtNumIntegrante.getText().toString(), tEntrega, tPrivacidad, individualOGrupo);
                String idJob = mu.databaseReference.push().getKey();
                mu.insertar("Retos", idJob, reto);
                break;
            }
            case R.id.btnAdd:{

                if(txtFecha.getText().toString().equals("")){
                    txtFecha.setError("Ingrese fecha!");
                }if( spnTipoEntrega.getSelectedItemPosition() == 0){
                    txtFecha.setError("Seleccione tipo de entrega acá abajo");
                }else{
                    int position = 0;
                    Entregable entregable = new Entregable(txtFecha.getText().toString(), spnTipoEntrega.getSelectedItem().toString());
                    mData.add(position,entregable);
                    mDates.notifyItemInserted(position);
                    mDates.notifyDataSetChanged();
                    mRecyclerDates.scrollToPosition(position);
                    Toast.makeText(getContext(), "Agregada entrega", Toast.LENGTH_SHORT).show();
                  //  txtFecha.setText("");
                    break;
                }

            }
            case R.id.etDates:{

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };

                new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
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
        spnIndividualGrupo.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposReto);
        spnTipoReto.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposPrivacidad);
        spnPrivacidad.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposEntrega);
        spnTipoEntrega.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, individualGrupo);
        spnIndividualGrupo.setAdapter(adapter3);
    }
}
