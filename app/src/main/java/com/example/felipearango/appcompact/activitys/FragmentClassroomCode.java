package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Usuario_estudiante;
import com.example.felipearango.appcompact.models.ManejoUser;
import com.example.felipearango.appcompact.models.RecyclerAdapterAddStudent;
import com.example.felipearango.appcompact.models.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentClassroomCode extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<String> mData= new ArrayList<>();
    private View view;
    private Button btnAdd, btnPublicarAula;
    private EditText etEmail;
    private TextView tvKey;
    private ManejoUser mn = new ManejoUser();
    final ArrayList<Usuario_estudiante> lstUser = new ArrayList<>();
    final ArrayList<String> lstEstudiantes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_classroom_code, container, false);

        // Inflate the layout for this fragment
        setRecycler();
        initComponents();
        initListStudents();
        return view;
    }

    private void setRecycler(){

        recycler = (RecyclerView) view.findViewById(R.id.rv_estudiantes);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new RecyclerAdapterAddStudent(view.getContext(), mData);
        recycler.setAdapter(adapter);
    }

    private void initComponents(){
        etEmail = view.findViewById(R.id.etEmail);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnPublicarAula = view.findViewById(R.id.btnPublicarAula);
        tvKey = view.findViewById(R.id.tvKeyAula);
        mn.inicializatedFireBase();
        tvKey.setText(FragmentCreateClassroom.key_aula);
        btnAdd.setOnClickListener(this);
        btnPublicarAula.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        switch (view.getId()){
            case R.id.btnAdd: {
                if(!validateEmpty(email)) {
                    if(userExist(email)) {
                        int position = 0;
                        mData.add(position, etEmail.getText().toString());
                        adapter.notifyItemInserted(position);
                        adapter.notifyDataSetChanged();
                        recycler.scrollToPosition(position);
                        etEmail.setText("");
                    } else{
                        etEmail.setError("El usuario no esta registrado");
                    }
                }
                break;
            }
            case R.id.btnPublicarAula:{
                mn.registrarAula(FragmentCreateClassroom.nombre_aula, FragmentCreateClassroom.descripcion_aula,
                        mData, FragmentCreateClassroom.key_aula, lstEstudiantes);
                Toast.makeText(getContext(), "Aula agregada correctamente", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private boolean validateEmpty(String email) {
        return email.equals("");
    }

    private void initListStudents(){
        mn.databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user:dataSnapshot.getChildren()) {
                    Usuario_estudiante u = user.getValue(Usuario_estudiante.class);
                    lstUser.add(u);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    /**
     * Metodo para validar que el usuario que se va agregar al RecyclerView este registrado en la BD
     * @param email
     * @return
     */
    private boolean userExist(String email){
        for (Usuario_estudiante user: lstUser) {
            if(user.getCorreo().equalsIgnoreCase(email)) {
                lstEstudiantes.add(user.getUid());
                return true;
            }
        }
        return false;
    }
}
