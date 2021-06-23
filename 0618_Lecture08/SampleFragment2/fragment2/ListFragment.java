package org.techtown.fragment2;

import android.content.Context;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListFragment extends Fragment {

    public static interface ImageSelection{
        public  void onImageSelected(int position);
    }

    public ImageSelection callback;

    @Override
    public void onAttach(Context context) {     // 프래그먼트가 액티비티에 붙는 순간에 대해서 오버라이드함.
        super.onAttach(context);

        if (context instanceof ImageSelection) {
            // context에 메인액티비티 넣을거고 이미지 섹션의 자식으로 메인엑티비티를 넣을거임. 그러면 true가 나옴
            callback = (ImageSelection)context; // callback은 메인액티비티임.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        Button button = rootView.findViewById(R.id.button);
        Button button2 = rootView.findViewById(R.id.button2);
        Button button3 = rootView.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(2);
            }
        });


        return rootView;

    }
}