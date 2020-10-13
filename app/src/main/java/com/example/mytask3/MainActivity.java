package com.example.mytask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String START = "start";
    private final String COUNT = "count";
    private SharedPreferences coldStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TEST", "onCreate: ok ");
        if (savedInstanceState == null) {
            setColdStart(3);
        } else Log.d("TEST", "onCreate: rotation ");
    }


    private void setColdStart(int setIterationStart) {
        int numberStart = getNumberStart();
        setNumberStart(numberStart);
        showToast(numberStart, setIterationStart);
        Log.d("TEST", "onCreate:" + numberStart);
    }

    private void setNumberStart(int number) {
        coldStart = getSharedPreferences(START, MODE_PRIVATE);
        SharedPreferences.Editor writeMode = coldStart.edit();
        writeMode.putInt(COUNT, ++number);
        writeMode.commit();
    }

    private int getNumberStart() {
        coldStart = getSharedPreferences(START, MODE_PRIVATE);
        return coldStart.getInt(COUNT, 0);
    }


    private void showToast(int number, int iteration) {
        if (number == iteration - 1) {
            Toast.makeText(MainActivity.this, "Холодный старт!!!", Toast.LENGTH_LONG).show();
            Log.d("TEST", "showToast: show");
        }
    }

}