package com.example.vacinationnavigator.Ui.Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.R;
import com.example.vacinationnavigator.Ui.Base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppointmentActivity extends BaseActivity implements AppointmentView, LocationListener , View.OnClickListener {

    LocationManager locationManager;
    TextView test;
    AppointmentPresenterImp<AppointmentView> presenter ;
    Location  currentLocation;

    TextView title;
    TextView descr;
    TextView address;
    TextView city;

    TextView secDate;
    EditText picker;

    Button confirm;

    DatePickerDialog dpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //test = findViewById(R.id.Test);
        title = findViewById(R.id.ctitle);
        descr = findViewById(R.id.cdesc);
        address = findViewById(R.id.caddress);
        city = findViewById(R.id.ccity);
        secDate = findViewById(R.id.secDate);
        picker = findViewById(R.id.editText1);
        confirm = findViewById(R.id.confirm);
        picker.setOnClickListener(this);
        picker.setShowSoftInputOnFocus(false);
        confirm.setOnClickListener(this);
        currentLocation = null ;
        OpenGPS();
        InitializePresenter();
        setUpView();
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void InitializePresenter() {
        presenter = new AppointmentPresenterImp<>();
        presenter.onAttach(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location ;
        //test.setText("Lat:" + String.valueOf(currentLocation.getLatitude()) + ", Long:" + String.valueOf(currentLocation.getLongitude()));
        presenter.LoadCenter(currentLocation);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 5432 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
            }
        }
        else{
            Toast.makeText(this, "We need access to your location for making the appointment. Go to Settings -> Permissions", Toast.LENGTH_LONG).show();
        }

    }

    public void OpenGPS(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5432);
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        }
    }

    @Override
    public void UpdateCenter(Center center) {
        title.setText(center.getTitle());
        descr.setText(center.getDescription());
        address.setText(center.getAddress());
        city.setText(center.getCity());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.editText1:
                PickerClicked();
                break;
            case R.id.confirm:
               MakeAppointment();
               break;
        }
    }

    void PickerClicked(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        dpicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        picker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        Calendar cal = Calendar.getInstance();
                        cal.set(dayOfMonth,monthOfYear,dayOfMonth);
                        cal.add(Calendar.DATE , 28);
                        secDate.setText(cal.get(Calendar.DATE) +"/" +
                                (cal.get(Calendar.MONTH)+1));

                    }
                }, year, month, day);
        dpicker.show();
    }

    void MakeAppointment(){
        Toast.makeText(this,"Appointment confirmed", Toast.LENGTH_LONG).show();
        presenter.SendConfirm(picker.getText().toString());

    }
}

