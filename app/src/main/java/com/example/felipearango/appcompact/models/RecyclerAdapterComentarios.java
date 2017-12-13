package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.DatosC;

import java.util.ArrayList;

/**
 * Created by Felipe Arango on 8/12/2017.
 */

public class RecyclerAdapterComentarios extends RecyclerView.Adapter<RecyclerAdapterComentarios.RecyclerAdapterHolder>{

    private ArrayList<DatosC> listRetos;

    private Context mContext;
    private  FragmentTransaction transaction;

    public static class RecyclerAdapterHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView comentario;
        public ImageView iVPerfil;
        private View thisView;

        public RecyclerAdapterHolder(View v) {
            super(v);
            nombre = (TextView) itemView.findViewById(R.id.tvNameC);
            comentario = (TextView) itemView.findViewById(R.id.tvComent);
            iVPerfil = (ImageView) itemView.findViewById(R.id.imVPerfilC);
            thisView = itemView;
        }
    }

    public RecyclerAdapterComentarios(Context context, ArrayList<DatosC> comentario){
        this.listRetos = comentario;
        this.mContext = context;

    }

    @Override
    public RecyclerAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_comentarios, parent, false);
        return new RecyclerAdapterHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterHolder holder, final int position) {
        holder.nombre.setText(listRetos.get(position).getName());
        holder.comentario.setText(listRetos.get(position).getComentario());
       // holder.iVPerfil.setText(listRetos.get(position).getIndividualOGrupo());
       /* holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentVisualizarRetos();
                args.putString(Keys.idReto,listRetos.get(position).getId());
                fragment.setArguments(args);
                transaction.replace(R.id.FrFragments, fragment);
                transaction.commit();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listRetos.size();
    }



}