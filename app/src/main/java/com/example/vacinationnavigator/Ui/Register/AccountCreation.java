package com.example.vacinationnavigator.Ui.Register;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vacinationnavigator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountCreation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountCreation extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RegisterFragmentListener listener;
    Button next;
    EditText email;
    EditText password;
    EditText repassword;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountCreation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountCreation.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountCreation newInstance(String param1, String param2) {
        AccountCreation fragment = new AccountCreation();
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
        View root = inflater.inflate(R.layout.fragment_account_creation, container, false);
        email =(EditText) root.findViewById(R.id.et_email);
        password =(EditText) root.findViewById(R.id.et_password);
        repassword = (EditText) root.findViewById(R.id.et_repassword);
        next = (Button) root.findViewById(R.id.btn_next);
        next.setOnClickListener(this);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (RegisterFragmentListener) context;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            if(!email.getText().toString().isEmpty() && email.getText().toString() != null && !password.getText().toString().isEmpty() && password.getText().toString() != null
                    && !repassword.getText().toString().isEmpty() && repassword.getText().toString() != null) {
                listener.OnNextClicked(email.getText().toString(), password.getText().toString(), repassword.getText().toString());
            }
            else{
                Toast.makeText(getActivity(), "Fields are mandatory", Toast.LENGTH_LONG).show();
            }
        }
    }
}