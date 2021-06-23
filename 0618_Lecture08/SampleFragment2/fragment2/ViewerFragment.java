package org.techtown.fragment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewerFragment extends Fragment {
    ImageView imageView;
    int[] images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = rootView.findViewById(R.id.imageView);

        images = {rootView.findViewById()ById(R.drawable.dream01),rootView.findViewById(R.drawable.dream02),
                rootView.findViewById(R.drawable.dream03)};


        return rootView;
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);  // 이미지 바꿔주는 메소드
    }
}