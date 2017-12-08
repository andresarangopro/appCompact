package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.R;

import java.util.ArrayList;

/**
 * Created by Sebastian Luna R on 12/2/2017.
 */

public class RecyclerAdapterVisulizeDates extends RecyclerView.Adapter<RecyclerAdapterVisulizeDates.ViewHolder> {

    private ArrayList<Entregable> mDataSet;
    private Context mContext;


    public RecyclerAdapterVisulizeDates(Context context, ArrayList<Entregable> mDataSet){
            this.mContext = context;
            this.mDataSet = mDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private EditText et, etType;

        public ViewHolder(View v){
            super(v);
            et = v.findViewById(R.id.etDates);
            etType = v.findViewById(R.id.etType);
            et.setFocusable(false);
            etType.setFocusable(false);
        }
    }

    @Override
    public RecyclerAdapterVisulizeDates.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_visulize_dates,parent,false);
        return new RecyclerAdapterVisulizeDates.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterVisulizeDates.ViewHolder holder, final int position) {
        holder.et.setText(mDataSet.get(position).getFecha());
        holder.etType.setText(mDataSet.get(position).getTipoEntrega());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
