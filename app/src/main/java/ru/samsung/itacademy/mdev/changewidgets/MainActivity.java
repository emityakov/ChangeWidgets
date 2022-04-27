package ru.samsung.itacademy.mdev.changewidgets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    boolean running = false;
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        View [] views = {new CheckBox(this),
                new Button(this),
                new RadioButton(this)};

        Thread t = new Thread() {
            public void run() {
                while (running) {
                    i++;
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((LinearLayout) findViewById(R.id.layout)).removeAllViews();
                                ((LinearLayout) findViewById(R.id.layout)).addView(views[i % 3]);
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running) {
                    running = true;
                    t.start();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                t.interrupt();
            }
        });
    }
}