package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.felipearango.appcompact.R;

import java.util.ArrayList;

/**
 * Created by Sebastian Luna R on 12/8/2017.
 */

public class RecyclerAdapterAddStudent extends RecyclerView.Adapter<RecyclerAdapterAddStudent.ViewHolder> {


    private ArrayList<String> mDataSet;
    private Context mContext;

    public RecyclerAdapterAddStudent(Context context, ArrayList<String> mDataSet){
        this.mContext = context;
        this.mDataSet = mDataSet;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView et;
        private Button btnRemove;

        public ViewHolder(View v){
            super(v);
            et = v.findViewById(R.id.tvEmail);
            btnRemove = v.findViewById(R.id.btnAdd);
            btnRemove.setText("-");
        }
    }

    @Override
    public RecyclerAdapterAddStudent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_add_students,parent,false);
        return new RecyclerAdapterAddStudent.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterAddStudent.ViewHolder holder, final int position) {
        holder.et.setText(mDataSet.get(position));
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
