package org.techtown.sampleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name","mike"); // 정보를 넘겨줌. 인텐트 객체 생성하고 name의 값을 부가 데이터로 넣기
                // name = "mike"; 개념인듯? 정보를 보내서 저장해둠. main에서 찾아서 토스트 메세지에 넣음.
                setResult(RESULT_OK, intent); // 나를 호출한 객체에게 정보값을 보냄. 응답 보내기
                finish();  // 두개의 액티비티 일 땐 닫으면 다시 돌아감. 현재 액티비티 없애기
            }
        });
    }
}