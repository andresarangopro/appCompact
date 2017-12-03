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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRetos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRetos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRetos extends Fragment implements View.OnClickListener {

    /////////////////////////////////////
    //Variables
    ////////////////////////////////////
    EditText txtNombre, txtDescripcion, txtFecha, txtNumIntegrante;
    Spinner spnTipoReto, spnPrivacidad, spnTipoEntrega, spnIndividualGrupo;
    Button btnPublicarReto, addDate;
    String [] tiposReto = {"Seleccione el tipo de reto", "elite", "aula", "rally"};
    String [] tiposPrivacidad = {"Seleccione la privacidad del reto", "publico", "privado"};
    String [] tiposEntrega = {"Seleccione el formato de entrega", "video", "imagenes", "documentos"};
    String [] individualGrupo = {"Reto individual o en grupo?", "individual", "grupo"};
    ManejoUser mu = new ManejoUser();
    ArrayList<String> lstFechas = new ArrayList<>();
    ArrayList<String> lstEntregas = new ArrayList<>();
    FirebaseAuth mAuth;


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

        mAuth = FirebaseAuth.getInstance();

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



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPublicarReto:{
                if(validarCampos()) {
                    Entregable entregable = new Entregable(txtFecha.getText().toString(), spnTipoEntrega.getSelectedItem().toString());
                    mData.add(0, entregable);
                    llenarListas();
                    FirebaseUser user = mAuth.getCurrentUser();

                    Reto reto = new Reto(user.getEmail(), txtNombre.getText().toString(), txtDescripcion.getText().toString(), spnTipoReto.getSelectedItem().toString(), lstFechas,
                            txtNumIntegrante.getText().toString(), lstEntregas, spnPrivacidad.getSelectedItem().toString(), spnIndividualGrupo.getSelectedItem().toString());
                    String idJob = mu.databaseReference.push().getKey();
                    mu.insertar("Retos", idJob, reto);
                    mData.clear();
                    lstEntregas.clear();
                    lstFechas.clear();
                    Toast.makeText(getContext(), "Reto publicado", Toast.LENGTH_LONG).show();
                }
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposReto);
        spnTipoReto.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposPrivacidad);
        spnPrivacidad.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, tiposEntrega);
        spnTipoEntrega.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, individualGrupo);
        spnIndividualGrupo.setAdapter(adapter3);
    }

    /**
     * Metodo para llenar las listas de fechas y tipos de entrega
     */
    private void llenarListas(){
        for (Entregable entregable:
             mData) {
            lstFechas.add(entregable.getFecha());
            lstEntregas.add(entregable.getTipo());
        }
    }

    private boolean validarCampos(){
        if(txtNombre.getText().toString().isEmpty()){
            txtNombre.setError("Por favor ingrese el nombre del reto");
            return false;
        } else if(txtDescripcion.getText().toString().isEmpty()){
            txtDescripcion.setError("Por favor ingrese la descripcion del reto");
            return false;
        } else if(spnTipoReto.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(), "Por favor seleccione el tipo de reto que va ingresar", Toast.LENGTH_LONG).show();
            return false;
        }else if(txtFecha.getText().toString().isEmpty()){
            txtFecha.setError("Por favor ingrese la fecha de entrega del reto");
            return false;
        } else if(spnTipoEntrega.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(), "Por favor seleccione el formato de entrega", Toast.LENGTH_LONG).show();
            return false;
        } else if (spnIndividualGrupo.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(), "Por favor seleccione si el reto se podra realizar individual o en grupo", Toast.LENGTH_LONG).show();
            return false;
        } else if (txtNumIntegrante.getText().toString().isEmpty()){
            txtNumIntegrante.setError("Por favor ingrese el numero de integrantes que podran participar en este reto");
            return false;
        } else if(spnPrivacidad.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(), "Por favor seleccione la privacidad de su reto", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }
}
