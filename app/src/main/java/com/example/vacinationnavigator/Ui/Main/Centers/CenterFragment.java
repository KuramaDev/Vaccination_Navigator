package com.example.vacinationnavigator.Ui.Main.Centers;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Main.CentersAdapter;
import com.example.vacinationnavigator.Ui.Main.HomeFragmentListener;
import com.example.vacinationnavigator.Ui.Register.RegisterFragmentListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CenterFragment extends Fragment implements OnMapReadyCallback , CentersAdapter.onItemClickedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Bundle mapViewBundle;
    private LatLng location;




    RecyclerView recyclerView;
    HomeFragmentListener listener;
    CentersAdapter adapter;
    ArrayList<Center> centers;

    MapView mapView;


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
        location=new LatLng(0,0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =  inflater.inflate(R.layout.fragment_center, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rv);

        adapter = new CentersAdapter(centers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        LoadCenters();
        mapView = (MapView) root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
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

    @Override
    public void onStart() {
        mapView.onStart();
        super.onStart();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(location).title("Marker"));

    }

    @Override
    public void onItemClicked(Center center) {
        Toast.makeText(getActivity(), center.getTitle().toString(), Toast.LENGTH_LONG).show();
        location = new LatLng(center.getLatitude(),center.getLongitude());
        
    }


}