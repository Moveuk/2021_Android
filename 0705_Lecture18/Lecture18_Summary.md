# Lecture17 : 앱 화면에 웹브라우저 넣기
Key Word : 웹뷰, WebViewClient, WebSettings, 인터넷 허용, Permission for Internet, Mission15, page 전환

<hr/>

## 교재 458p : 08-3 앱 화면에 웹브라우저 넣기

 앱에서 웹사이트를 띄우는 방법으로는 인텐트에 주소값을 넣어 띄우는 방법이 있다. 하지만 사용자의 편의성을 위해 조금 더 자연스럽고 일관성있는 화면으로 인식하게 만들기 위해 앱 내부에 웹사이트 화면을 넣을 수 있다. 그럴 때 필요한 것이 `웹뷰(WebView)`이다. 웹뷰를 사용하게 되면 별도의 화면으로 갑자기 나타나는 웹 브라우저보다 더 자연스러운 화면을 보여줄 수 있다. 웹 뷰를 만들어 보자.
 
 1. 새로운 프로젝트 `SampleWeb`을 만들고 메인 sml 파일에 웹뷰 태그를 이용하여 만들어보자.

 첫줄에는 주소를 입력하여 이동할 수 있는 버튼과 텍스트 뷰를 놓고 두번째 줄에는 웹뷰로 버튼을 눌러 이동한 화면을 띄우는 역할을 하도록 디자인 해주었다.

**layout_main.xml**
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
            android:text=" 열기 "
            android:padding="4dp"
            android:textSize="20sp"/>

        <EditText
            android:id="@+id/editText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textSize="20dp"/>
    </LinearLayout>

    <WebView
        android:id="@+id/webView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>
```

**결과 화면**   
<img src="https://user-images.githubusercontent.com/84966961/124423690-a48ad580-dda0-11eb-92bb-698bc8f543b6.png" width="40%">   
   

<br><br>
<hr>

 2. 이제는 MainActivity.java 파일에 웹뷰에 코드를 넣어 띄워주는 기능을 구현해보자.

**MainActivity.java**
```java
public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        // 웹뷰의 설정 수정하기
        WebSettings webSettings = webView.getSettings();
        // 웹페이지에서 스크립트 기능들이 정상적으로 작동하게 해주는 설정
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 웹사이트 로딩
                webView.loadUrl(editText.getText().toString());
            }
        });
    }

    private class ViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            view.loadUrl(url);

            return true;
        }
    }
}
```

<br><br>
<hr>

 3. 이후 앱의 인터넷 사용 권한을 Mainfest 파일에 넣어줘야한다.   

**AdroidManifest.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="org.techtown.web">
<!-- 이 부분이 인터넷 사용 권한 -->
    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:usesCleartextTraffic="true"
<!-- 여기까지 -->
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.SampleWeb">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```


고백 고포워드 기능을 활용하고싶으면 히스토리를 저장해야한다

스택방식으로 되며 중간에 있는 값을 받고 싶다면 중간값을 꺼내야 한다.

![image](https://user-images.githubusercontent.com/84966961/124427268-20d3e780-dda6-11eb-8aed-ae81428516e5.png)



<br><br>
<hr>

## 교재 470p : 도전! 15



**activity_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_horizontal"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="100dp"
        android:text="입력 화면으로" />
</LinearLayout>
```

**activity_customer_input.xml**
```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="20dp"
        android:layout_centerInParent="true"
        android:background="#aaffffff"
        >
        <LinearLayout
            android:id="@+id/contentsLayout"
            android:orientation="vertical"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentTop="true"
            android:layout_margin="4dp"
            >
            <LinearLayout
                android:orientation="horizontal"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="4dp"
                >
                <TextView
                    android:id="@+id/nameLabel"
                    android:text="  이      름  "
                    android:textColor="#ff222222"
                    android:textSize="16sp"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    />
                <EditText
                    android:id="@+id/nameInput"
                    android:layout_width="120dp"
                    android:layout_height="wrap_content"
                    android:layout_alignBaseline="@id/nameLabel"
                    android:inputType="textPersonName"
                    />
            </LinearLayout>
            <LinearLayout
                android:orientation="horizontal"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="4dp"
                >
                <TextView
                    android:id="@+id/ageLabel"
                    android:text="  나      이  "
                    android:textColor="#ff222222"
                    android:textSize="16sp"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    />
                <EditText
                    android:id="@+id/ageInput"
                    android:layout_width="120dp"
                    android:layout_height="wrap_content"
                    android:layout_alignBaseline="@id/ageLabel"
                    android:inputType="numberDecimal"
                    android:maxLength="3"
                    />
            </LinearLayout>
            <LinearLayout
                android:orientation="horizontal"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="4dp"
                >
                <TextView
                    android:id="@+id/birthLabel"
                    android:text="  생 년 월 일  "
                    android:textColor="#ff222222"
                    android:textSize="16sp"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    />
            </LinearLayout>
            <LinearLayout
                android:orientation="vertical"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="4dp"
                >
                <Button
                    android:id="@+id/birthButton"
                    android:layout_width="180dp"
                    android:layout_height="wrap_content"
                    android:layout_gravity="right"
                    android:text=""
                    />
            </LinearLayout>
        </LinearLayout>
        <Button
            android:id="@+id/saveButton"
            android:layout_width="120dp"
            android:layout_height="wrap_content"
            android:layout_below="@+id/contentsLayout"
            android:layout_centerHorizontal="true"
            android:layout_marginTop="30dp"
            android:text="저 장"
            />
    </RelativeLayout>

</RelativeLayout>
```

**MainActivity.java**
```java
package org.techtown.mission15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                goToCustomerInputActivity();
            }
        });
    }
    public void goToCustomerInputActivity() {
        // 액티비티 화면 전환
        Intent intent = new Intent(getApplicationContext(),CustomerInputActivity.class);
        startActivity(intent);
        // 매개변수 1 매개변수2
        // 첫 액티비티의 애니메이션에 대한 설정, 목표 액티비티가 어떻게 따라나올지에 대한 설정
        // 거꾸로 놓으면 목표액티비티부터 움직여서 첫 액티비티로 온 후 다시 목표로 갑자기 이동함.
        overridePendingTransition(R.anim.entry,R.anim.exit);
    }
}
```

**CustomerInputActivity.java**
```java
package org.techtown.mission15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_input);

        Button button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }
    public void goToMainActivity() {
        // 액티비티 화면 전환
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        // 매개변수 1 매개변수2
        // 첫 액티비티의 애니메이션에 대한 설정, 목표 액티비티가 어떻게 따라나올지에 대한 설정
        // 거꾸로 놓으면 목표액티비티부터 움직여서 첫 액티비티로 온 후 다시 목표로 갑자기 이동함.
        overridePendingTransition(R.anim.entry,R.anim.exit);
    }
}
```

**entry.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:fromXDelta="100%"
        android:toXDelta="0%"
        android:duration="1500"/>
</set>
```

**exit.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:fromXDelta="0%"
        android:toXDelta="-100%"
        android:duration="1500"/>
</set>
```





























































