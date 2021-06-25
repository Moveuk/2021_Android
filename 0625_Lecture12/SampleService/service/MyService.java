package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";


    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate() 호출");  // 생명주기를 보기위해서 로그를 찍음.
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand() 호출"); // 생명주기를 보기위해서 로그를 찍음.

        // 인텐트가 null이 아니면 processCommand로 인텐트를 넘겨 처리하도록 하는 기능.
        if (intent == null) {
            return Service.START_STICKY;
            // Service가 종료가 되었을 경우 자동으로 재시작 시켜주는 명령어.
        } else {
            processCommand(intent); // intent를 처리하는 메소드
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        // 메인액티비티에서 저장해둔 정보를 intent에서 부가데이터 가져오기
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG,"command : "+command+" / name : "+ name);
        // 제대로 들어왔는지 로그 찍어보기
//        Toast.makeText(getApplicationContext(),TAG+"command : "+command+" / name : "+ name,Toast.LENGTH_LONG).show();

        for (int i = 0; i <5; i++)  {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {};

            Log.d(TAG,"Waiting "+i+" seconds.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() 호출");// 생명주기를 보기위해서 로그를 찍음.
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}