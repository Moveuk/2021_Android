package org.techtown.sliding;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedImageDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // 버튼이 현재 열려있는 건지 아닌건지를 확인하기 위한 전역 변수(플래그)를 선언함.
    // false 닫혀있음, true 열려있음. 토글 버튼. 토글 상태.
    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        button = findViewById(R.id.button);

        translateLeftAnim = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        // 애니메이션이 실행된 이후 애니메이션 이벤트를 감지해주는 리스너 장치
        // 버튼이 onClick 되었을 때 리스너가 감지하는 것처럼 애니메이션을 감지함
        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

        // 바로 익명객체로 만들 때는 반드시 이렇게 오버라이딩 해줘야함.
        // 하지만 책은 하나의 클래스를 구현해서 상속해서 사용함.
        // 이유는 같은 코드를 2번 작성할 이유가 없기 때문에 따로 클래스를 구현한다.
        // 하지만 버튼은 익명객체로 만든 것임.
//        translateRightAnim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // isPageOpen 플래그가 true면 닫히는 애니메이션
                if(isPageOpen){
                    page.startAnimation(translateRightAnim);
                } else {
                // isPageOpen 플래그가 false면 열리면서 보이는 애니메이션
                // View는 page이다. `gone`상태인 뷰를 `VISIBLE`하게 되어 보이게끔 해주고 왼쪽으로 열리게 해준다.
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnim);
                }
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            } else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}