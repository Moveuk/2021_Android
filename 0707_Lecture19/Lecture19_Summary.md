# Lecture19 : 09 스레드와 핸들러 이해하기
Key Word : Thread, Handler, Handler.post, Message, Bundle(Key-Value)

<hr/>

## 교재 473p : 09-1 핸들러 이해하기

 만약 하나의 프로세서로 작업을 처리할 경우 대기 시간이 길어지는 네트워크 요청 등의 기능을 수행할 때 화면에 보이는 UI도 멈춤 상태로 있게 되는 문제가 생길 수 있다. 이런 문제를 해결하기 위해서 하나의 프로세스 안에서 여러 개의 작업이 동시 수행되는 **멀티 스레드 방식**을 사용하게 된다. `스레드(Thread)`는 **동시 수행이 가능한 작업 단위**이며, 현재 수행 중인 작업 이외의 기능을 동시에 처리할 때 새로운 스레드를 만들어 처리한다. 이런 **멀티 스레드 방식**은 같은 프로세스 안에 들어 있으면서 **메모리 리소스를 공유하므로 효율적인 처리가 가능**하다. 하지만 동시에 리소스에 접근할 때 **데드락(DeadLock)**이 발생하여 시스템이 비정상적으로 동작할 수도 있다.    





<br/><br/>
<hr/>

### 교재 474p : 스레드 사용하기
   
1. 새로운 프로젝트 `SampleThread`를 만들고 `activity_main.xml` 파일을 디자인한다.

**activity_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="value 값"
        android:textSize="34sp"
        app:layout_constraintBottom_toTopOf="@+id/button"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.7" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="348dp"
        android:text="스레드 시작"
        android:textSize="20sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

**화면**   
<img src="https://user-images.githubusercontent.com/84966961/124720098-bdcb8780-df42-11eb-80a6-a506164248f8.png" width="40%">    
   


<br/><br/>

2. `MainActivity.java`에 Thread를 하나 만들고 button 객체를 생성하여 onclick시 thread가 run되도록 코드를 짠다.   
    

**MainActivity.java**
```java
package org.techtown.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value = 0;
    
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클래스이므로 new로 객체를 생성해야한다. method처럼 사용하면 안됨.
                Backgrountthread thread = new Backgrountthread();
                thread.run();
            }
        });
    }

    class Backgrountthread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<100; i++){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value++;
                Log.d("Thread","value"+value);
            }

        }
    }
}
```
**로그 화면**   
![image](https://user-images.githubusercontent.com/84966961/124719378-0898cf80-df42-11eb-9962-56a766879717.png)   




<br/><br/>

 main이 GUI 객체를 사용할 때 스레드가 또 접근하여 꼬이지 않도록 하기 위해서 스레드가 GUI를 건들 수 없도록 설정해놓았기 때문에 오류가 발생한다. 그러므로 핸들러를 두고 사용해야 하며 실제 어플리케이션이 작동할 때에도 GUI객체들에 대하여 각각 따로따로 작동하게 된다.   

```java
    class Backgrountthread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<100; i++){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value++;
                Log.d("Thread","value"+value);
                // 스레드가 GUI 객체에 접근할 수 없기 때문에 오류 발생.
                // textView.setText("횟수 : "+value);
            }

        }
    }
```
**오류 화면**
![image](https://user-images.githubusercontent.com/84966961/124720931-9c1ed000-df43-11eb-9c98-ba5db9c91bc0.png)



<br/><br/>
	
### 교재 477p : 핸들러로 메시지 전송하기	
	
**핸들러를 사용할 때 필요한 세 가지 단계**    
![image](https://user-images.githubusercontent.com/84966961/124722781-595df780-df45-11eb-974d-b60f85c1394e.png)    


<br/><br/>

3. TextView를 세팅해줄 핸들러 클래스를 작성하자.

```java
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 이 것 또한 콜백 함수이다. 자동으로 호출되어짐.
            super.handleMessage(msg);

            // Bundle 타입으로 받아온 것을 객체에 넣어줌.
            Bundle bundle = msg.getData();
            // 번들을 getInt 메소드로 받아옴.
            int value = bundle.getInt("value");
            // 매 초(혹은 sleep한 시간)마다 반복되고 이것은 콜백 함수에 의해서 매번 불려서 사용됨.
            textView.setText("value 값 : "+value);
        }
    }
```



<br/><br/>

4. 메세지가 생기면 자동으로 콜백함수가 호출되고 BackgroundThread 메세지에 담겨온 번들 타입을 받아 텍스트뷰에 등록시켜준다.

```java
public class MainActivity extends AppCompatActivity {

    int value = 0;
    
    TextView textView;

    MainHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클래스이므로 new로 객체를 생성해야한다. method처럼 사용하면 안됨.
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        // 메인 쓰레드가 핸들러에 대한 정보를 가지게 된다.
        handler = new MainHandler();
    }

    class BackgroundThread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<100; i++){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value++;
                Log.d("Thread","value"+value);
                // 스레드가 GUI 객체에 접근할 수 없기 때문에 오류 발생.
                // textView.setText("횟수 : "+value);

                Message message = handler.obtainMessage();
                // 안드로이드에서 제공하는 Map과 같은 컬렉션이다. Key-Value 쌍의 데이터 집합이다.
                // 번들 컬렉션 객체 생성
                Bundle bundle = new Bundle();
                // 번들에 Key-Value 로 데이터 put
                bundle.putInt("value",value);
                // 메세지의 메소드로 이 번들 데이터를 Message Queue로 보냄.
                // MainHandler 에서 처리함.
                message.setData(bundle);

                // 메세지 보내면 자동으로 콜백함수가 받음.
                handler.sendMessage(message);
            }

        }
    }

    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 이 것 또한 콜백 함수이다. 자동으로 호출되어짐.
            super.handleMessage(msg);

            // Bundle 타입으로 받아온 것을 객체에 넣어줌.
            Bundle bundle = msg.getData();
            // 번들을 getInt 메소드로 받아옴.
            int value = bundle.getInt("value");
            // 매 초(혹은 sleep한 시간)마다 반복되고 이것은 콜백 함수에 의해서 매번 불려서 사용됨.
            textView.setText("value 값 : "+value);
            // wrapping을 통해서 int를 String으로 바꾸어 주었다.
//            textView.setText(String.valueOf(value));
            // value만 넣으면 int형인지라 setText는 String만 받아서 오류 생길수도 있음
        }
    }
}
```
	
**결과 화면**    
<img src="https://user-images.githubusercontent.com/84966961/124723528-1e0ff880-df46-11eb-9492-56e16e212995.png" width="40%">

   

<br/><br/>
---
	
### 교재 480p : Runnable 객체 실행하기
	
 핸들러 클래스를 만들지 않더라도 단순하게 Runnable 객체를 이용해서 만들 수 있다. 이 경우 코드 라인이 짧아지게 된다.    
    
```java
package org.techtown.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value = 0;
    
    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클래스이므로 new로 객체를 생성해야한다. method처럼 사용하면 안됨.
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });


    }

    class BackgroundThread extends Thread{
        @Override
        public void run() {
            super.run();
            for(int i=0; i<100; i++){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value++;
                Log.d("Thread","value"+value);

                // 이렇게 바로 처리할 수 있다.
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(String.valueOf(value));
                    }
                });

            }

        }
    }

}
```

**결과 화면**    
<img src="https://user-images.githubusercontent.com/84966961/124727049-4fd68e80-df49-11eb-937e-346cd04a87e2.png" width="40%">

<br/><br/>
<hr>

## 이해한 부분 총정리
    
 스레드는 꼬이는 것을 방지하기 위해 Main 스레드 빼고는 GUI 객체(뷰 객체 등..)에 접근할 수 없다. 그렇기 때문에 Thread 자체에서 뷰 객체 정보를 처리하지 않고 단순히 메세지를 통해 핸들러로 보내게 된다. 메세지는 콜백 함수가 있기 때문에 새로운 메세지가 생길 때마다(코드 상에서는 onclick에서 sleep이후 메세지가 작성될 때마다) 스레드에서 생겨난 데이터들을 Bundle 타입으로 받아오게 되고, 핸들러는 GUI 객체에 접근하여 데이터를 처리하게 된다.

 post() 메서드를 사용하여 Runnable로 익명 객체를 만들게 되면 더 간단하게 스레드의 작업 결과물을 처리할 수 있다.

<br/><br/>

**Bundle** : 안드로이드에서 제공하는 Map과 같은 컬렉션이다. Key-Value 쌍의 데이터 집합이다.




