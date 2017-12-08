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

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.models.RecyclerAdapterAddStudent;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;

import java.util.ArrayList;


public class FragmentClassroomCode extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<String> mData= new ArrayList<>();
    private View view;
    private Button btnAdd;
    private EditText etEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_classroom_code, container, false);

        // Inflate the layout for this fragment
        setRecycler();
        initComponents();
        return view;
    }

    private void setRecycler(){

        mData.add("HOLA");
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
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnAdd: {
                int position = 0;
                mData.add(position,etEmail.getText().toString());
                adapter.notifyItemInserted(position);
                adapter.notifyDataSetChanged();
                recycler.scrollToPosition(position);
                etEmail.setText("");

                break;
            }
        }
    }
}
