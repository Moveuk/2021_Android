package org.techtown.mission03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ScrollView scrollView2;
    ImageView imageView;
    ImageView imageView2;
    BitmapDrawable bitmap;
    BitmapDrawable bitmap2;
    int image1Index=0;
    int image2Index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        imageView = findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;

        scrollView2 = findViewById(R.id.scrollView2);
        imageView2 = findViewById(R.id.imageView2);
        scrollView2.setHorizontalScrollBarEnabled(true);

        bitmap2 = (BitmapDrawable) res.getDrawable(R.drawable.image02);
        int bitmapWidth2 = bitmap2.getIntrinsicWidth();
        int bitmapHeight2 = bitmap2.getIntrinsicHeight();

        imageView2.setImageDrawable(bitmap2);
        imageView2.getLayoutParams().width = bitmapWidth2;
        imageView2.getLayoutParams().height = bitmapHeight2;
    }

    public void onButtonUpClicked(View v) {
        changeImage();
    }

    private void changeImage() {
        if (image1Index == 0) {
            Resources res = getResources();
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            image1Index = 1;
        } else if (image1Index == 1) {
            Resources res = getResources();
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            image1Index = 0;
        }
        if (image2Index == 0) {
            Resources res = getResources();
            bitmap2 = (BitmapDrawable) res.getDrawable(R.drawable.image01);
            int bitmapWidth2 = bitmap2.getIntrinsicWidth();
            int bitmapHeight2 = bitmap2.getIntrinsicHeight();

            imageView2.setImageDrawable(bitmap2);
            imageView2.getLayoutParams().width = bitmapWidth2;
            imageView2.getLayoutParams().height = bitmapHeight2;
            image1Index = 1;
        } else if (image2Index == 1) {
            Resources res = getResources();
            bitmap2 = (BitmapDrawable) res.getDrawable(R.drawable.image02);
            int bitmapWidth2 = bitmap2.getIntrinsicWidth();
            int bitmapHeight2 = bitmap2.getIntrinsicHeight();

            imageView2.setImageDrawable(bitmap2);
            imageView2.getLayoutParams().width = bitmapWidth2;
            imageView2.getLayoutParams().height = bitmapHeight2;
            image2Index = 0;
        }
    }
}