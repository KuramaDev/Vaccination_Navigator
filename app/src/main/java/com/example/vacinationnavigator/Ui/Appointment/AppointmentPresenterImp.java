package com.example.vacinationnavigator.Ui.Appointment;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.example.vacinationnavigator.Model.Appointment;
import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.Services.FirebaseService;
import com.example.vacinationnavigator.Services.rtDBAppoint;
import com.example.vacinationnavigator.Services.rtDBResponse;
import com.example.vacinationnavigator.Ui.Base.BasePresenterImp;

public class AppointmentPresenterImp<V extends AppointmentView> extends BasePresenterImp<V> implements AppointmentPresenter<V> , rtDBAppoint {

    FirebaseService firebaseService;
    Center currentCenter;

    public AppointmentPresenterImp(){
        firebaseService = new FirebaseService();
        firebaseService.AttachRtDBAppointReceiver(this);
        currentCenter = new Center();
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
    }

    @Override
    public V getView() {
        return super.getView();
    }

    public void LoadCenter(Location currentLocation){
        firebaseService.getCenter(currentLocation);
    }

    @Override
    public void CenterFetched(Center center) {
        Log.d("CENTER FETCHED" , center.getTitle());
        getView().UpdateCenter(center);
        currentCenter.setTitle(center.getTitle());
        currentCenter.setAddress(center.getAddress());
        currentCenter.setCity(center.getCity());
        currentCenter.setDescription(center.getDescription());
    }


    public void SendConfirm(String date){
        if(date != null){
            Appointment  appointment = new Appointment();
            appointment.setUserId("123456789");
            appointment.setCenterAddress(currentCenter.getAddress());
            appointment.setDate(date);
            appointment.setCenterAddress(currentCenter.getAddress());
            appointment.setCenterCity(currentCenter.getCity());
            appointment.setCenterTitle(currentCenter.getTitle());
            firebaseService.MakeAppointment(appointment);
        }
    }
}
