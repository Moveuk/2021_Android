package org.techtown.mission08_285p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SalesActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                intent.putExtra("data","메뉴 화면");
                setResult(RESULT_OK,intent);

                finish();
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("data","로그인 화면");

                startActivityForResult(intent, REQUEST_CODE_MENU);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(getApplicationContext(),
                    "onActivityResult 메서드 호출됨. 요청 코드 : "+ requestCode
                            +", 결과 코드 : "+RESULT_OK,Toast.LENGTH_LONG).show();
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("data");
                Toast.makeText(getApplicationContext(), name + "버튼이 눌림.", Toast.LENGTH_LONG).show();
            }
        }
    }
}