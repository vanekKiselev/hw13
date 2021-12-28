package com.example.hw13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Map;

public class LifeParametersActivity extends AppCompatActivity {

    private static final String TAG = "LifeParametersActivity";
    private Map<String, Patient> patients = PatientDataActivity.patients;

    EditText weightInput;
    EditText stepsInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_parameters);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        weightInput = findViewById(R.id.weightInput);
        stepsInput = findViewById(R.id.stepsInput);

    }

    public void goBack(View view) {
        finish();
    }

    public void saveParameters(View view) {
        float weight = 0;
        int steps = 0;
        boolean errorFlag = false;

        try {
            weight = Float.parseFloat(weightInput.getText().toString());
            makeRightColor(weightInput);
        } catch (NumberFormatException e) {
            errorFlag = true;
            makeWrongColor(weightInput);
            Log.e(TAG, "Неверный формат (вес)!");
        }

        try {
            steps = Integer.parseInt(stepsInput.getText().toString());
            makeRightColor(stepsInput);
        } catch (NumberFormatException e) {
            errorFlag = true;
            makeWrongColor(stepsInput);
            Log.e(TAG, "Неверный формат (кол-во шагов)!");
        }

        if (errorFlag) {
            Toast.makeText(this, "Проверьте ввод!", Toast.LENGTH_SHORT).show();
            return;
        }

        patients.get(Patient.currentName).lifeParamsList.add(new Patient.LifeParams(weight, steps));
        Log.i(TAG, String.format("Добавлены физ. параметры пациента %s. Длина списка: %d", Patient.currentName, patients.get(Patient.currentName).lifeParamsList.size()));
        Toast.makeText(this, "Записано!", Toast.LENGTH_SHORT).show();

    }

    public void makeWrongColor(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorWrongData));
    }

    public void makeRightColor(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.colorRightData));
    }


}