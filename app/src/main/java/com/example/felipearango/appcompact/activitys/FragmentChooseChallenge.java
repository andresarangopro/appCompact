package com.example.felipearango.appcompact.activitys;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Reto;
import com.example.felipearango.appcompact.models.RecyclerAdapterChallenges;
import com.example.felipearango.appcompact.models.RecyclerAdapterDates;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentChooseChallenge.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentChooseChallenge#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChooseChallenge extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mRecyclerChallenges;
    private RecyclerAdapterChallenges adapterChallenges;

    private RecyclerAdapterDates mDates;

    private ArrayList<Reto> mData = new ArrayList<>();

    private ArrayList<Entregable> mDataTest = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentChooseChallenge() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentChooseChallenge.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChooseChallenge newInstance(String param1, String param2) {
        FragmentChooseChallenge fragment = new FragmentChooseChallenge();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_challenge, container, false);

        Entregable entregable = new Entregable("Hoa", "Hoax2");
      //  mDataTest.add(entregable);

        mLinearLayoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerChallenges = view.findViewById(R.id.rv_desafíos);
        mRecyclerChallenges.setLayoutManager(mLinearLayoutManager);
        inicializeArray();
        mDates = new RecyclerAdapterDates(getContext(), mDataTest);
        mRecyclerChallenges.setAdapter(mDates);

        Toast.makeText(getContext(), mRecyclerChallenges.getAdapter().toString(), Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_challenge, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void inicializeArray(){
        ArrayList<String> asd = new ArrayList<>();
        asd.add("Hola");
        //Recycler -----------------------------
        Reto reto = new Reto("Hola", "dos", "tres", "Cuatro", asd, "Seis", asd, "Ocho", "Nueve");
        mData.add(reto);

    }

    private void inicializeRecycler(){



        inicializeArray();

        adapterChallenges.notifyItemInserted(0);
        adapterChallenges.notifyDataSetChanged();
        mRecyclerChallenges.scrollToPosition(0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
