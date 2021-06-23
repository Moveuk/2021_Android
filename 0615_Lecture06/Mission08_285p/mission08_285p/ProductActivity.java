package org.techtown.mission08_285p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                intent.putExtra("data","메뉴 화면");
                setResult(RESULT_OK,intent);

                finish();
            }
        });

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
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