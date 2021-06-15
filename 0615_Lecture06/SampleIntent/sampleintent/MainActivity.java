package org.techtown.sampleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  static final int REQUEST_CODE_MENU = 101;   // 내가 호출한 액티비티를 확인하기 위한 상수(임의 정의)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class); // 우리가 필요한 정보를 설정하는 객체.
//응답을 받을 경우는 startActivityForResult를 사용한다.
//아니면 그냥 StartActivity(intent)라 하면된다.
//즉 둘다 액티비티를 화면에 띄우고 인텐트를 전달해주는 역할
                startActivityForResult(intent, REQUEST_CODE_MENU); // 새로운 화면 전환을 startActivity를 이용해서 할 수 있다.
            }
        });
    }

    //돌아오면 자동으로 콜백되어 호출되는 함수 작성.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 첫번째 인자 : 요청 코드(REQUEST_CODE_MENU), 두번째 인자 : 결과코드(Result_OK)  세번째 : intent 정보를 받음.
        // 패키지 형태로 정보다 갔다가 돌아옴.
//세번째 매개변수에서 메뉴엑티비티에서 보낸 intent를 전달받음
//리퀘스트코드는 메뉴화면을 띄울떄 전달한 101코드가 메뉴화면으로갓다가 다시 여기로 requestCode로 전달됨.
//즉 requestCode로 어떤화면으로부터 왔는지 알 수 있음


        // menu에서 main으로 돌아올 때 토스트 메세지 .
        if(requestCode == REQUEST_CODE_MENU){   // 내가 정한 코드랑 같으면 토스트로 확인 메시지를 띄워라.
            // 내가 보낸 리퀘스트인지 확인할 수 있음.
            Toast.makeText(getApplicationContext(),
                    "onActivityResult 메서드 호출됨. 요청 코드 : " + requestCode +
                    ", 결과 코드 : "+ resultCode, Toast.LENGTH_LONG).show();

            if (resultCode == RESULT_OK) {      // 완료 후 토스트 메세지
                String name = data.getStringExtra("name");
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}