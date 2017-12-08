package com.example.felipearango.appcompact.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.RecyclerAdapterChallenges;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;

import java.util.ArrayList;


public class FragmentChooseChallenge extends Fragment {

    private RecyclerView mRecyclerChallenges;
    private RecyclerAdapterChallenges adapterChallenges;

    private RecyclerAdapterDates mDates;

    private ArrayList<Reto> mData = new ArrayList<>();

    private ArrayList<Entregable> mDataTest = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;


    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_choose_challenge, container, false);

        Entregable entregable = new Entregable("Hoa", "Hoax2");
      //  mDataTest.add(entregable);

      /*  mLinearLayoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerChallenges = view.findViewById(R.id.rv_desafíos);
        mRecyclerChallenges.setLayoutManager(mLinearLayoutManager);
        inicializeArray();
        mDates = new RecyclerAdapterDates(getContext(), mDataTest);
        mRecyclerChallenges.setAdapter(mDates);

        Toast.makeText(getContext(), mRecyclerChallenges.getAdapter().toString(), Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment*/
        dra();
        return view;
    }

    private void dra(){
        inicializeArray();
        recycler = (RecyclerView) view.findViewById(R.id.rv_desafíos);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new RecyclerAdapterChallenges(view.getContext(), mData);
        recycler.setAdapter(adapter);
    }

    private void inicializeArray(){
        ArrayList<String> asd = new ArrayList<>();
        asd.add("Hola");
        asd.add("Hola 2");
        //Recycler -----------------------------
        Reto reto = new Reto("Hola", "dos", "tres", "Cuatro", asd, "Seis", asd, "Ocho", "Nueve");
        mData.add(reto);

    }
}
