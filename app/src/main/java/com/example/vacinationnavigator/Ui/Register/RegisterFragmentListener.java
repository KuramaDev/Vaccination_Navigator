package com.example.vacinationnavigator.Ui.Register;

import android.text.Editable;

public interface RegisterFragmentListener {
    void OnNextClicked(String email, String password);
    void OnRegisteredClicked(String name, String amka, String phone, String age);


}
