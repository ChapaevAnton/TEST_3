package com.example.mytask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setShowToastColdStart(3, "Холодный старт", savedInstanceState);
    }



    /**
     * @param setIterationStart - на каком холодном запуске показывать Toast
     * @param msg - произвольное сообщение Toast
     * @param getColdStart - состояние текущего экземляра Activity
     */

    private void setShowToastColdStart(final int setIterationStart, final CharSequence msg, Bundle getColdStart) {
        Log.d("TEST", "onCreate: ok ");
        if (getColdStart == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final String START = "start";
                    final String COUNT = "count";
                    SharedPreferences coldStart = getSharedPreferences(START, MODE_PRIVATE);
                    int numberStart = coldStart.getInt(COUNT, 1);
                    Log.d("TEST", "onCreate:" + numberStart);
                    if (numberStart == setIterationStart) {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        Log.d("TEST", "showToast: show");
                    }
                    SharedPreferences.Editor writeMode = coldStart.edit();
                    writeMode.putInt(COUNT, ++numberStart);
                    writeMode.commit();
                }
            });
        } else Log.d("TEST", "onCreate: rotation ");
    }

}