# Lecture07 : 프래그먼트 이해하기
Key Word : 프래그먼트(Fragment)   
   
<hr/>
   
# 교재 287p : 05-1 프래그먼트란?   
   
 액티비티를 이용하여 하나의 액티비티 안에 여러 개의 액티비티를 부분 화면으로 올려서 보여주는 방법을 이용할 수 있지만 액티비티는 하나의 화면을 독립적으로 구성할 때 필요한 여러 가지 속성들을 사용하게 되며, 안드로이드 시스템에서 관리하는 앱 구성 요소이기 때문에 액티비티 안에 다른 액티비티를 넣는 것은 단말의 리소스를 많이 사용하는 비효율적인 방법이다. 그렇기 때문에 하나의 화면을 여러 부분으로 나눠서 보여주거나 각각의 부분 화면 단위로 바꿔서 보여주고 싶을 때 사용하는 것이 `프래그먼트(Fragment)`이다.   
   
![image](https://user-images.githubusercontent.com/84966961/122336968-9ce1c900-cf78-11eb-8a27-7f57b41e5a26.png)    
출처 : 안드로이드 공식 개발 문서
   
```
프래그먼트의 사용 목적
	- 분할된 화면들을 독립적으로 구성하기 위해 사용함.
	- 분할된 화면들의 상태를 관리하기 위해 사용함.
```
   
 프래그먼트는 자바의 인터페이스처럼 프래그먼트 단독으로는 실행이 불가능하다. 반드시 액티비티 내부에서 존재하며 내부에서만 실행이 가능하다. 또한 액티비티 간 이동을 할때 액티비티 매니저를 이용하는 것처럼 프래그먼트도 프래그먼트매니저를 만들어 관리하도록 한다.


<br/><br/>
<hr/>
   
## 교재 294p ~ 302p : 프래그먼트 만들어 화면에 추가하기   
   
**activity_main.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/container"
    tools:context=".MainActivity">

    <fragment
        android:id="@+id/mainFragment"
        android:name="org.techtown.fragment.MainFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```
   
**fragment_main.xml**
   
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainFragment" >

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="메인 프래그먼트"
        android:textSize="30sp" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="메뉴 화면으로" />
</LinearLayout>
```
   
**fragment_menu.xml**
   
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/holo_orange_light"
    android:orientation="vertical"
    tools:context=".MainFragment">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="메뉴 프래그먼트"
        android:textSize="30sp" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="메인 화면으로" />
</LinearLayout>
```
   
**MainActivity.java**
   
```java
package org.techtown.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();
    }

    public void onFragmentChanged(int idx){
        if(idx == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,menuFragment).commit();
        }else if(idx == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
        }
    }
}
```
   
**MainFragment.java**
   
```java
package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         ViewGroup rootView =  (ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);

         Button button = rootView.findViewById(R.id.button);

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 MainActivity activity = (MainActivity)getActivity();
                 activity.onFragmentChanged(0);
             }
         });

         return rootView;
    }
}
```
   
**MenuFragment.java**
   
```java
package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =  (ViewGroup)inflater.inflate(R.layout.fragment_menu, container, false);

        Button button = rootView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.onFragmentChanged(1);
            }
        });

        return rootView;
    }
}
```



<br/><br/>
<hr/>
