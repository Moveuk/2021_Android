package org.techtown.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
                // 리소스에 정의한 애니메이션 액션 로딩
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);

                // 뷰 애니메이션 시작
                v.startAnimation(anim);

                // 모든 뷰에는 애니메이션을 실행시킬 수 있는 메소드가 있음. 그래서 버튼에서 받아오는 매개변수 v에서 메소드를 호출함.
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 리소스에 정의한 애니메이션 액션 로딩
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale2);

                // 뷰 애니메이션 시작
                v.startAnimation(anim);
            }
        });



    }
}