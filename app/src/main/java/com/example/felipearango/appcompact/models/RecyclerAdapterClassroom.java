package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Aula;

import java.util.ArrayList;

/**
 * Created by Sebastian Luna R on 12/9/2017.
 */

public class RecyclerAdapterClassroom extends RecyclerView.Adapter<RecyclerAdapterClassroom.ViewHolder> {

    private ArrayList<Aula> mDataSet;
    private Context mContext;


    public RecyclerAdapterClassroom(Context context, ArrayList<Aula> mDataSet){
        this.mContext = context;
        this.mDataSet = mDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvProfesor, tvIntegrantes;
        private View view;

        public ViewHolder(View v){
            super(v);
            tvNombre = v.findViewById(R.id.tvNombreAula);
            tvProfesor = v.findViewById(R.id.tvProfesorEncargado);
            tvIntegrantes = v.findViewById(R.id.tvNumeroIntegrantes);
            this.view = v;
        }
    }

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
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
