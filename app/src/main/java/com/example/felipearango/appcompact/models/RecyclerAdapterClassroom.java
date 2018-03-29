package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.felipearango.appcompact.Fragments.FragmentClassroomCode;
import com.example.felipearango.appcompact.Fragments.FragmentCreateClassroom;
import com.example.felipearango.appcompact.Fragments.FragmentVisualizarRetos;
import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Aula;
import com.example.felipearango.appcompact.util.Keys;

import java.util.ArrayList;

/**
 * Created by Sebastian Luna R on 12/9/2017.
 */

public class RecyclerAdapterClassroom extends RecyclerView.Adapter<RecyclerAdapterClassroom.ViewHolder> {

    ///////////////////////////////////////////////
    ///Variables///////////////////////////////////
    ///////////////////////////////////////////////

    /**
     * Lista con los datos a ser mostrados
     */
    private ArrayList<Aula> mDataSet;

    /**
     * Constexto de la aplicación
     */
    private Context mContext;

    /**
     * Boolean que indica si se debe de mostrar el botón de agregar estudiantes al aula.
     * True para mostrar botón, false o null en caso contrario.
     */
    public static Boolean add;

    /**
     * Fragment para hacer la transacción de un framgent a otro desde el recycler
     */
    private FragmentTransaction transaction;

    /**
     * Argumentos a ser enviados al otro fragment
     */
    private Bundle args = new Bundle();

    ///////////////////////////////////////////////
    ///Costructor//////////////////////////////////
    ///////////////////////////////////////////////

    /**
     * Contructor por defecto
     * @param context contexto de la aplicación
     * @param mDataSet Datos para ser mostrados en el recycler
     */
    public RecyclerAdapterClassroom(Context context, ArrayList<Aula> mDataSet, FragmentTransaction fragmentTransaction){
        this.mContext = context;
        this.mDataSet = mDataSet;
        this.add = false;
        this.transaction = fragmentTransaction;
    }

    /**
     * Constructor si se necesita mostrar el botón de agregar estudiantes
     * @param context Contexto de la aplicación
     * @param mDataSet Datos a ser mostrados en el recycler
     * @param add boolean que indica si se debe mostrar el botón de agregar, true para mostrarlo,
     *            false o null si no se debe mostrar.
     */
    public RecyclerAdapterClassroom(Context context, ArrayList<Aula> mDataSet, Boolean add, FragmentTransaction fragmentTransaction){
        this.mContext = context;
        this.mDataSet = mDataSet;
        this.add = add;
        this.transaction = fragmentTransaction;
    }

    /**
     * Clase interna que contiene los datos del XML
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvProfesor, tvIntegrantes;
        private Button btnAdd;
        private ImageView imageView;

        public ViewHolder(View v){
            super(v);
            tvNombre = v.findViewById(R.id.tvNombreAula);
            tvProfesor = v.findViewById(R.id.tvProfesorEncargado);
            tvIntegrantes = v.findViewById(R.id.tvNumeroIntegrantes);
            btnAdd = v.findViewById(R.id.btnAddStudents);
            imageView = v.findViewById(R.id.imageIcon);
            if(RecyclerAdapterClassroom.add){
                LinearLayout.LayoutParams forButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        0, 1);
                btnAdd.setLayoutParams(forButtonParams);
                imageView.setLayoutParams(forButtonParams);
            }else{
                LinearLayout.LayoutParams forButtonParams = new LinearLayout.LayoutParams(0,
                        0, 0);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        0, 2);
                btnAdd.setLayoutParams(forButtonParams);
                imageView.setLayoutParams(forButtonParams);
            }
        }
    }

    ///////////////////////////////////////////////
    ///Métodos ////////////////////////////////////
    ///////////////////////////////////////////////

    @Override
    public RecyclerAdapterClassroom.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_aula,parent,false);
        return new RecyclerAdapterClassroom.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterClassroom.ViewHolder holder, final int position) {
        holder.tvNombre.setText(mDataSet.get(position).getNombre());
        holder.tvProfesor.setText(mDataSet.get(position).getProfesor());
        holder.tvIntegrantes.setText("Intregrantes: " + mDataSet.get(position).getLstIntegrantes().size());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCreateClassroom.nombre_aula = mDataSet.get(position).getNombre();
                FragmentCreateClassroom.descripcion_aula = mDataSet.get(position).getDescripcion();
                FragmentCreateClassroom.key_aula = mDataSet.get(position).getKey();
                args.putBoolean("datos", true);
                args.putString("key", mDataSet.get(position).getKey());
                args.putStringArrayList("integrantes", mDataSet.get(position).getLstIntegrantes());
                Fragment fragment = new FragmentClassroomCode();
                fragment.setArguments(args);
                transaction.replace(R.id.FrFragments, fragment);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
