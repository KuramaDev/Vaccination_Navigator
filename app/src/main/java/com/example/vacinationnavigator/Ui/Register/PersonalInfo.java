package com.example.vacinationnavigator.Ui.Register;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vacinationnavigator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfo extends Fragment implements View.OnClickListener{

    Button register;
    EditText name;
    EditText phone;
    EditText amka;
    EditText age;
    RegisterFragmentListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfo newInstance(String param1, String param2) {
        PersonalInfo fragment = new PersonalInfo();
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
        // Inflate the layout for this fragment

        View root =  inflater.inflate(R.layout.fragment_personal_info, container, false);
        name  =(EditText) root.findViewById(R.id.name);
        phone =(EditText) root.findViewById(R.id.phone);
        amka = (EditText) root.findViewById(R.id.amka);
        age = (EditText) root.findViewById(R.id.age);
        register = (Button) root.findViewById(R.id.register);
        register.setOnClickListener(this);
        return root;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (RegisterFragmentListener) context;
    }
    @Override
    public void onClick(View view) {
        listener.OnRegisteredClicked(name.getText().toString(),phone.getText().toString(),amka.getText().toString(),age.getText().toString());
    }
}