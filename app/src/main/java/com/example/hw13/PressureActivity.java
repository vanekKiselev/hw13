package com.example.hw13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PressureActivity extends AppCompatActivity {

    private static final String TAG = "PressureActivity";

    private Map<String, Patient> patients = PatientDataActivity.patients;
    EditText dateInput;
    EditText upperPressureInput;
    EditText lowerPressureInput;
    EditText pulseInput;
    CheckBox tachicardyBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dateInput = findViewById(R.id.dateInput);
        upperPressureInput = findViewById(R.id.upperPressureInput);
        lowerPressureInput = findViewById(R.id.lowerPressureInput);
        pulseInput = findViewById(R.id.pulseInput);
        tachicardyBox = findViewById(R.id.tachicardyBox);

    }

    public void goBack(View view) {
        finish();
    }

    public void setCurrentDate(View view) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM HH:mm");
        date.getTime();
        dateInput.setText(df.format(date));
    }

    public void savePressureData(View view) {
        boolean errorFlag = false;
        int upperPressure = 0;
        int lowerPressure = 0;
        int heartRate = 0;
        boolean tachycardia = false;
        Date measureDate = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM HH:mm");



        try {
            upperPressure = Integer.parseInt(upperPressureInput.getText().toString());
            makeRightColor(upperPressureInput);
        } catch (NumberFormatException e) {
            errorFlag = true;
            makeWrongColor(upperPressureInput);
            Log.e(TAG, "Неверный формат (верхнее давление)!");
        }

        try {
            lowerPressure = Integer.parseInt(lowerPressureInput.getText().toString());
            makeRightColor(lowerPressureInput);
        } catch (NumberFormatException e) {
            errorFlag = true;
            makeWrongColor(lowerPressureInput);
            Log.e(TAG, "Неверный формат (нижнее давление)!");
        }

        try {
            heartRate = Integer.parseInt(pulseInput.getText().toString());
            makeRightColor(pulseInput);
        } catch (NumberFormatException e) {
            errorFlag = true;
            makeWrongColor(pulseInput);
            Log.e(TAG, "Неверный формат (пульс)!");
        }

        try {
            measureDate = df.parse(dateInput.getText().toString());
            makeRightColor(dateInput);
        } catch (ParseException e) {
            errorFlag = true;
            makeWrongColor(dateInput);
            Log.e(TAG, "Неверный формат (дата)!");
        }
        tachycardia = tachicardyBox.isChecked();

        if (errorFlag) {
            Toast.makeText(this, "Проверьте ввод!", Toast.LENGTH_SHORT).show();
            return;
        }




        patients.get(Patient.currentName).pressureDataList.add(new Patient.PressureData(heartRate, tachycardia, upperPressure, lowerPressure, measureDate));
        Log.i(TAG, String.format("Добавлены параметры ССС пациента %s. Длина списка: %d", Patient.currentName, patients.get(Patient.currentName).pressureDataList.size()));
        Toast.makeText(this, "Записано!", Toast.LENGTH_SHORT).show();

    }

    public void makeWrongColor(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorWrongData));
    }

    public void makeRightColor(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorRightData));
    }


}