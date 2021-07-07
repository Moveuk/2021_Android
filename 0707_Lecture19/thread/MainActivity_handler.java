package org.techtown.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_handler extends AppCompatActivity {

    int value = 0;
    
    TextView textView;

    MainHandler handler;

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

        // 메인 쓰레드가 핸들러에 대한 정보를 가지게 된다.
        handler = new MainHandler();
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
                // 스레드가 GUI 객체에 접근할 수 없기 때문에 오류 발생.
                // textView.setText("횟수 : "+value);

                Message message = handler.obtainMessage();
                // 안드로이드에서 제공하는 Map과 같은 컬렉션이다. Key-Value 쌍의 데이터 집합이다.
                // 번들 컬렉션 객체 생성
                Bundle bundle = new Bundle();
                // 번들에 Key-Value 로 데이터 put
                bundle.putInt("value",value);
                // 메세지의 메소드로 이 번들 데이터를 Message Queue로 보냄.
                // MainHandler 에서 처리함.
                message.setData(bundle);

//                 메세지 보내면 자동으로 콜백함수가 받음.
                handler.sendMessage(message);
            }

        }
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 이 것 또한 콜백 함수이다. 자동으로 호출되어짐.
            super.handleMessage(msg);

            // Bundle 타입으로 받아온 것을 객체에 넣어줌.
            Bundle bundle = msg.getData();
            // 번들을 getInt 메소드로 받아옴.
            int value = bundle.getInt("value");
            // 매 초(혹은 sleep한 시간)마다 반복되고 이것은 콜백 함수에 의해서 매번 불려서 사용됨.
            textView.setText("value 값 : "+value);
            // wrapping을 통해서 int를 String으로 바꾸어 주었다.
//            textView.setText(String.valueOf(value));
            // value만 넣으면 int형인지라 setText는 String만 받아서 오류 생길수도 있음
        }
    }
}