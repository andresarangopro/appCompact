package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.felipearango.appcompact.Entregable;
import com.example.felipearango.appcompact.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Luna R on 11/30/2017.
 */

public class RecyclerAdapterDates extends RecyclerView.Adapter<RecyclerAdapterDates.ViewHolder> {

    private ArrayList<Entregable> mDataSet;
    private Context mContext;

    public RecyclerAdapterDates(Context context, ArrayList<Entregable> mDataSet){
        this.mContext = context;
        this.mDataSet = mDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private EditText et, etType;
        private Button btnRemove;


        public ViewHolder(View v){
            super(v);
            et = v.findViewById(R.id.etDates);
            etType = v.findViewById(R.id.etType);
            et.setEnabled(false);
            etType.setEnabled(false);
            btnRemove = v.findViewById(R.id.btnAdd);
            btnRemove.setText("-");

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_addfecha,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.et.setText(mDataSet.get(position).getFecha());
        holder.etType.setText(mDataSet.get(position).getTipo());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSet.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataSet.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
