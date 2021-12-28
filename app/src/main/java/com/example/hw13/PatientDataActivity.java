package com.example.hw13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;


public class PatientDataActivity extends AppCompatActivity {
    private static final String TAG = "PatientDataActivity";
    public static final Map<String, Patient> patients = new HashMap<>();
    EditText nameInputZone;
    EditText ageInputZone;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientdata);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nameInputZone = findViewById(R.id.nameInputZone);
        ageInputZone = findViewById(R.id.ageInputZone);

        Log.i(TAG, "Ввод персональных данных пациента.");


    }

    public void gotoPressureActivity(View view) {
        if (patients.isEmpty()) {
            Log.w(TAG, "Список пациентов пуст!");
            Toast.makeText(this, "Сначала введите данные пациента!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(PatientDataActivity.this, PressureActivity.class);
        startActivity(intent);
        Log.i(TAG, "Переход к записи параметров ССС (сердечно-сосудистой системы).");
    }

    public void gotoLifeParametersActivity(View view) {
        if (patients.isEmpty()) {
            Log.w(TAG, "Список пациентов пуст!");
            Toast.makeText(this, "Сначала введите данные пациента!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(PatientDataActivity.this, LifeParametersActivity.class);
        startActivity(intent);
        Log.i(TAG, "Переход к записи физических параметров организма.");
    }

    public void savePatient(View view) {
        String name = nameInputZone.getText().toString();
        int age = 0;
        boolean errorFlag = false;


        if (name.isEmpty()) {
            errorFlag = true;
            makeWrongColor(nameInputZone);
            Log.e(TAG, "Пустое поле ФИО!");
        }
        else {
            makeRightColor(nameInputZone);
        }

        try {
            age = Integer.parseInt(ageInputZone.getText().toString());
            makeRightColor(ageInputZone);

        } catch (NumberFormatException e) {
            errorFlag = true;
            makeWrongColor(ageInputZone);
            Log.e(TAG, "Неверно ввели числовые данные!");
        }
        if(errorFlag == true) {
            Toast.makeText(this, "Проверьте ввод!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (patients.containsKey(name)) {
            Log.w(TAG, "Попытка повторной записи пациента.");
            Toast.makeText(this, "Пациент с этим именем уже записан!", Toast.LENGTH_SHORT).show();
            return;
        }


        patients.put(name, new Patient(name, age));
        Toast.makeText(this, String.format("Добавлен пациент %s.", name), Toast.LENGTH_SHORT).show();
        Log.i(TAG, String.format("Добавлен пациент %s. Размер списка: %d", name, patients.size()));
    }


    public void makeWrongColor(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorWrongData));
    }

    public void makeRightColor(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorRightData));
    }


}

