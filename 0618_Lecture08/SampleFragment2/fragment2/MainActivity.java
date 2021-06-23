package org.techtown.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelection{
    ListFragment listFragment;          // 리스트 프래그먼트 선언
    ViewerFragment viewerFragment;      // 뷰어 프래그먼트 선언

    int[] images = {R.drawable.dream01,R.drawable.dream02,R.drawable.dream03};  //드림01의 주소값이 16진수 정수형 타입임.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.ListFragment);
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.ViewerFragment);
    }


    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}