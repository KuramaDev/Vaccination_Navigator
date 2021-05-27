package com.example.vacinationnavigator.Ui.Main.Centers;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Main.CentersAdapter;
import com.example.vacinationnavigator.Ui.Main.HomeFragmentListener;
import com.example.vacinationnavigator.Ui.Register.RegisterFragmentListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CenterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    HomeFragmentListener listener;
    CentersAdapter adapter;
    ArrayList<Center> centers;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CenterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CenterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CenterFragment newInstance(String param1, String param2) {
        CenterFragment fragment = new CenterFragment();
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
        centers= new ArrayList<Center>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =  inflater.inflate(R.layout.fragment_center, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv);
        adapter = new CentersAdapter(centers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        LoadCenters();
        return root;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (HomeFragmentListener) context;
    }

    void LoadCenters() {
        listener.FetchCenters();
    }

    public void CentersLoaded(List<Center> list){
        centers.addAll(list);
        adapter.notifyDataSetChanged();
    }
}