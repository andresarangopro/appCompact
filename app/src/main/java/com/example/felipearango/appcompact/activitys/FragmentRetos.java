package com.example.felipearango.appcompact.activitys;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
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
 * Created by Felipe Arango on 5/12/2017.
 */

public class FragmentRetos extends Fragment implements View.OnClickListener {

    /////////////////////////////////////
    //Variables
    ////////////////////////////////////

    private EditText txtNombre, txtDescripcion, txtFecha, txtNumIntegrante;
    private Spinner spnTipoReto, spnPrivacidad, spnTipoEntrega, spnIndividualGrupo;
    private Button btnPublicarReto, addDate;
    private String [] tiposReto = {"Seleccione el tipo de reto", "elite", "aula", "rally"};
    private String [] tiposPrivacidad = {"Seleccione la privacidad del reto", "publico", "privado"};
    private String [] tiposEntrega = {"Seleccione el formato de entrega", "video", "imagenes", "documentos"};
    private  String [] individualGrupo = {"Reto individual o en grupo?", "individual", "grupo"};
    private ManejoUser mu = new ManejoUser();
 //   private ArrayList<String> lstFechas = new ArrayList<>();
   // private ArrayList<String> lstEntregas = new ArrayList<>();
    //private ArrayList<Entregable> listEntregable = new ArrayList<>();
    private FirebaseAuth mAuth;


    private RecyclerView mRecyclerDates;
    private RecyclerAdapterDates mDates;
    private ArrayList<Entregable> listEntregable = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private Calendar myCalendar = Calendar.getInstance();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_retos, container, false);
       // NavegacionActivity.toolbar.setTitle("");
        initXml();
        mu.inicializatedFireBase();
        return view;
    }


    public void initXml(){
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

        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates = view.findViewById(R.id.rv_fechas);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mDates = new RecyclerAdapterDates(view.getContext(), listEntregable);
        mRecyclerDates.setAdapter(mDates);

        //Calendar -------------------------------------------
    }

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


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnPublicarReto:{
                if(validarCampos()) {
                    String idEntregable = mu.databaseReference.push().getKey();
                    Entregable entregable = new Entregable(idEntregable, txtFecha.getText().toString(),
                            spnTipoEntrega.getSelectedItem().toString(),listEntregable.size());
                    listEntregable.add(entregable);
                   // llenarListas();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.e("Tam", listEntregable.size()+"");
                    final String idReto = mu.databaseReference.push().getKey();
                    Reto reto = new Reto(user.getEmail(), txtNombre.getText().toString(),
                            txtDescripcion.getText().toString(), spnTipoReto.getSelectedItem().toString(),
                            txtNumIntegrante.getText().toString(), spnPrivacidad.getSelectedItem().toString(),
                            spnIndividualGrupo.getSelectedItem().toString(),idReto);



                    listEntregable = mu.insertarEntregable("Retos", idReto, reto, listEntregable);

                   // listEntregable.clear();
                   // lstEntregas.clear();
                  //  lstFechas.clear();
                    Toast.makeText(view.getContext(), "Reto publicado", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.btnAdd:{
                String idEntregable = mu.databaseReference.push().getKey();

                if(txtFecha.getText().toString().equals("")){
                    txtFecha.setError("Ingrese fecha!");
                }if( spnTipoEntrega.getSelectedItemPosition() == 0){
                    txtFecha.setError("Seleccione tipo de entrega acá abajo");
                }else{
                    int position = 0;
                    Entregable entregable = new Entregable(idEntregable,txtFecha.getText().toString(),
                            spnTipoEntrega.getSelectedItem().toString(),listEntregable.size());
                    listEntregable.add(position,entregable);

                    mDates.notifyItemInserted(position);
                    mDates.notifyDataSetChanged();
                    mRecyclerDates.scrollToPosition(position);
                    Toast.makeText(view.getContext(), "Agregada entrega", Toast.LENGTH_SHORT).show();
                    //  txtFecha.setText("");
                    break;
                }
            }
            case R.id.etDates:{
                pickData();
                break;
            }
        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtFecha.setText(sdf.format(myCalendar.getTime()));
    }

    private void pickData(){
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

        new DatePickerDialog(view.getContext(), AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
