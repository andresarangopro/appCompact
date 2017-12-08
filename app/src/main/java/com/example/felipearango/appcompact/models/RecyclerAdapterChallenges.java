package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.activitys.Activity_Principal;
import com.example.felipearango.appcompact.activitys.FragmentChooseChallenge;
import com.example.felipearango.appcompact.activitys.FragmentVisualizarRetos;
import com.example.felipearango.appcompact.clases.Reto;

import java.util.ArrayList;

/**
 * Created by Sebastian Luna R on 12/3/2017.
 */

public class RecyclerAdapterChallenges extends RecyclerView.Adapter<RecyclerAdapterChallenges.ViewHolder> {

    private ArrayList<Reto> mDataSet;
    private Context mContext;
    public static Reto retoChallenge = null;

    public RecyclerAdapterChallenges(Context context, ArrayList<Reto> mDataSet){
        this.mContext = context;
        this.mDataSet = mDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvType, tvGroup;
        private View view;

        public ViewHolder(View v){
            super(v);
            tvNombre = v.findViewById(R.id.tvNombreReto);
            tvType = v.findViewById(R.id.tvPrivacidad);
            tvGroup = v.findViewById(R.id.tvIndividualGrupo);
            this.view = v;
        }
    }

    @Override
    public RecyclerAdapterChallenges.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_choose_challenge,parent,false);
        return new RecyclerAdapterChallenges.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterChallenges.ViewHolder holder, final int position) {
        holder.tvNombre.setText(mDataSet.get(position).getNombre());
        holder.tvType.setText(mDataSet.get(position).getPrivacidad());
        holder.tvGroup.setText(mDataSet.get(position).getIndividualOGrupo());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retoChallenge = mDataSet.get(position);
                Activity_Principal activity = (Activity_Principal)view.getContext();
                FragmentVisualizarRetos fragmentVisualizarRetos = new FragmentVisualizarRetos();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragments, fragmentVisualizarRetos).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
