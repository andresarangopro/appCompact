package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.Fragments.FragmentVisualizarRetos;
import com.example.felipearango.appcompact.util.Keys;
import com.example.felipearango.appcompact.clases.Reto;

import java.util.ArrayList;

/**
 * Created by Felipe Arango on 8/12/2017.
 */

public class RecyclerAdapterRetos extends RecyclerView.Adapter<RecyclerAdapterRetos.RecyclerAdapterHolder>{

    private ArrayList<Reto> listRetos;
    private Context mContext;
    private Bundle args = new Bundle();
    private FragmentTransaction transaction;

    public static class RecyclerAdapterHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView privacidad;
        public TextView tamaño;
        private View thisView;

        public RecyclerAdapterHolder(View v) {
            super(v);
            nombre = itemView.findViewById(R.id.tvNombreReto);
            privacidad = itemView.findViewById(R.id.tvPrivacidad);
            tamaño = itemView.findViewById(R.id.tvIndividualGrupo);
            thisView = itemView;
        }
    }

    public RecyclerAdapterRetos(Context context, ArrayList<Reto> retos,  FragmentTransaction transaction){
        this.listRetos = retos;
        this.mContext = context;
        this.transaction = transaction;
    }

    @Override
    public RecyclerAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_choose_challenge, parent, false);
        return new RecyclerAdapterHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterHolder holder, final int position) {
        holder.nombre.setText(listRetos.get(position).getNombre());
        holder.privacidad.setText(listRetos.get(position).getPrivacidad());
        holder.tamaño.setText(listRetos.get(position).getIndividualOGrupo());
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentVisualizarRetos();
                args.putString(Keys.idReto,listRetos.get(position).getId());
                fragment.setArguments(args);
                transaction.replace(R.id.FrFragments, fragment);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRetos.size();
    }



}