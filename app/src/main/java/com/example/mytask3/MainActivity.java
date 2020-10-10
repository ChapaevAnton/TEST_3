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

        ColdStart(3);
    }


    private void ColdStart(final int setIterationStart) {
        Thread coldStart = new Thread(new Runnable() {
            @Override
            public void run() {
                final int numberStart = getNumberStart();
                setNumberStart(numberStart);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(numberStart, setIterationStart);
                    }
                });
                Log.d("TEST", "onCreate:" + numberStart);
            }
        });
        coldStart.start();
    }

    private void showToast(int number, int iteration) {
        if (number == iteration - 1) {
            Toast.makeText(MainActivity.this, "Холодный старт!!!", Toast.LENGTH_LONG).show();
            Log.d("TEST", "showToast: show");
        }
    }

    private void setNumberStart(int number) {
        number++;
        setShared();
        SharedPreferences.Editor writeMode = coldStart.edit();
        writeMode.putInt(COUNT, number);
        writeMode.commit();
    }

    private int getNumberStart() {
        setShared();
        return coldStart.getInt(COUNT, 0);
    }

    private void setShared() {
        coldStart = getSharedPreferences(START, MODE_PRIVATE);
    }
}