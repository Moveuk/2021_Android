package org.techtown.mission03;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_master extends AppCompatActivity {
    ScrollView scrollView;
    ScrollView scrollView2;
    ImageView imageView;
    ImageView imageView2;
    Button button;
    Button button2;
    BitmapDrawable bitmap;
    BitmapDrawable bitmap2;
    int imageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById((R.id.imageView));
        imageView2 = findViewById((R.id.imageView2));

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            // OnClickListener()인터페이스임.
            @Override
            public void onClick(View v) {
                moveImageUp();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            // OnClickListener()인터페이스임.
            @Override
            public void onClick(View v) {
                moveImageDown();
            }
        });

    }

    private void moveImageDown() {
        imageView.setImageResource(R.drawable.image01);
        imageView2.setImageResource(R.drawable.image02);

        imageView.invalidate(); //이미지 화면에 그려주는 것.
        imageView2.invalidate(); //이미지 화면에 그려주는 것.
    }

    private void moveImageUp() {
        imageView.setImageResource(R.drawable.image02);
        imageView2.setImageResource(R.drawable.image01);

        imageView.invalidate(); //이미지 화면에 그려주는 것.
        imageView2.invalidate(); //이미지 화면에 그려주는 것.
    }
}