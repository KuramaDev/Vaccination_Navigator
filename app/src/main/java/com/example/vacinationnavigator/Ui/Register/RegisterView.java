package com.example.vacinationnavigator.Ui.Register;

import com.example.vacinationnavigator.Ui.Base.BaseView;

public interface RegisterView extends BaseView {

   void RegisterCompleted();
   void RegisterFailure(String message);
}
