# Test3 Web View & Stack
Key Word : Web View, Stack

<hr/>

## 시험 설명

 웹뷰로 액티비티 내에 주소창과 버튼을 이용하여 웹사이트를 띄우고 이전, 다음 버튼을 통하여 웹페이지를 앞으로 뒤로 이동시키는 것이 목적이다.   

**예시 화면**    
<img src="https://user-images.githubusercontent.com/84966961/124856146-fae85600-dfe4-11eb-9e6e-df39bfd2f8ad.png" width="60%">   

   
<br><br>
<hr>
   
### layout code

 Linear Layout 에서 하단에 레이아웃을 붙이기 위해서는 하단 레이아웃 위에 뷰에 weight를 1 주고 width 혹은 height를(붙이려는 방향에 따라) 0dp를 주면 바닥에 붙게 된다.   


```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity" >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="4dp"
            android:text=" 열기 "
            android:textSize="20sp" />

        <EditText
            android:id="@+id/editText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textSize="20dp" />
    </LinearLayout>

    <WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/button2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="이전" />

        <Button
            android:id="@+id/button3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="다음" />
    </LinearLayout>
</LinearLayout>
```


<br><br>
<hr>
   
### activity code

 기존 back, forward 스택만 사용하면 문제가 생기는데 스택 끝단으로 갔을 때 맨 위의 스택이 화면에 나오므로 다시 반대로 돌아갈 때 마지막 스택이 2번 구현되는 오류가 발생된다.   
   
<img src="https://user-images.githubusercontent.com/84966961/124857160-c5446c80-dfe6-11eb-9e80-e3427943b027.png" width="60%">    
     
 그래서 Current 스택을 하나 더 만들어 URL 값들이 이동하도록 했다.    
   
![image](https://user-images.githubusercontent.com/84966961/124857332-181e2400-dfe7-11eb-9d67-b776c3c36af2.png)   

   
```java
package org.techtown.test7_webviewnstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;
    String CurrentURL;

    public static Stack back = new Stack<>();
    public static Stack forward = new Stack<>();
    public static Stack current = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        // back, forward 만 쓰면 스택 끝으로 갔을 때 두번 반복해서 웹페이지가 실행되는 오류가 있어서
        // current 를 따로 만들어서 했습니다.
        // 기존 방식과 똑같고 현재 페이지 주소만 current에 존재하게끔 만들었습니다.
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentURL = editText.getText().toString();
                if (!current.empty()){
                    back.push(current.pop());
                }
                webView.loadUrl(CurrentURL);
                current.push(CurrentURL);
                if (!forward.empty()) {
                    forward.clear();
                    Log.d("Stack : URL",editText.getText().toString());
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!back.empty()) {
                    forward.push(current.pop());
                    CurrentURL = (String) current.push(back.pop());
                    webView.loadUrl(CurrentURL);
                    editText.setText(CurrentURL);
                    Log.d("Stack : URL",CurrentURL);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!forward.empty()) {
                    back.push(current.pop());
                    CurrentURL = (String) current.push(forward.pop());
                    webView.loadUrl(CurrentURL);
                    editText.setText(CurrentURL);
                    Log.d("Stack : URL",CurrentURL);
                }
            }
        });
    }

}
```

   
<br><br>
<hr>
   
### 이외 설정 

 웹뷰에 인터넷 사용을 위하여 permission을 앱에 넣어줘야 한다. 

**Manifests.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="org.techtown.test7_webviewnstack">
    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:usesCleartextTraffic="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Test7_WebViewNStack">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```


**결과 화면**    
<img src="https://user-images.githubusercontent.com/84966961/124857568-7e0aab80-dfe7-11eb-90c7-2ca30458adb1.png" width="40%">
<img src="https://user-images.githubusercontent.com/84966961/124857572-7fd46f00-dfe7-11eb-9781-5b61f4133550.png" width="40%">



<br><br>
<hr>
   



