package org.techtown.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value = 0;
    
    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클래스이므로 new로 객체를 생성해야한다. method처럼 사용하면 안됨.
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });


    }

    class BackgroundThread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<100; i++){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value++;
                Log.d("Thread","value"+value);

                // 이렇게 바로 처리할 수 있다.
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(String.valueOf(value));
                    }
                });

            }

        }
    }

}