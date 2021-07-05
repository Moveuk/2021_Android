package org.techtown.mission15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCustomerInputActivity();
            }
        });
    }
    public void goToCustomerInputActivity() {
        // 액티비티 화면 전환
        Intent intent = new Intent(getApplicationContext(),CustomerInputActivity.class);
        startActivity(intent);
        // 매개변수 1 매개변수2
        // 첫 액티비티의 애니메이션에 대한 설정, 목표 액티비티가 어떻게 따라나올지에 대한 설정
        // 거꾸로 놓으면 목표액티비티부터 움직여서 첫 액티비티로 온 후 다시 목표로 갑자기 이동함.
        overridePendingTransition(R.anim.entry,R.anim.exit);
    }
}