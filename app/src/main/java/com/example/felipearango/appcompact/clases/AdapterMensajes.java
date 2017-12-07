package com.example.felipearango.appcompact.clases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.felipearango.appcompact.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Usuario on 6/12/2017.
 */

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje> {

    private ArrayList<MensajeRecibir> lstMensaje = new ArrayList<>();
    private Context c;

    public AdapterMensajes(Context c, ArrayList mensajes) {
        this.c = c;
        this.lstMensaje = mensajes;
    }

    public void addMensaje(MensajeRecibir m){
        lstMensaje.add(m);
        notifyItemInserted(lstMensaje.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes, parent, false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(lstMensaje.get(position).getNombre());
        holder.getMensaje().setText(lstMensaje.get(position).getMensaje());
        if(lstMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(lstMensaje.get(position).getUrlFoto()).into(holder.getFotoMensaje());
        } else if(lstMensaje.get(position).getType_mensaje().equals("1")){
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        if(lstMensaje.get(position).getFotoPerfil().isEmpty()){
            holder.getFotoMensajePerfil().setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(c).load(lstMensaje.get(position).getFotoPerfil()).into(holder.getFotoMensajePerfil());
        }


        Long codigoHora = lstMensaje.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");//a pm o am
        holder.getHora().setText(sdf.format(d));
    }



    @Override
    public int getItemCount() {
        return lstMensaje.size();
    }
}
