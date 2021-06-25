# Lecture12 서비스와 수신자 이해하기
Key Word :     

<hr/>
   
## 교재 352p : 06 서비스와 수신자 이해하기

### 교재 353p : 06-1 서비스

서비스 : 백그라운드에서 실행되는 앱의 구성 요소.	
 보이지 않는 다고 사라져있는 개념이 아니고 주로 데이터 처리를 위한 방식이다. 예를 들어 카카오톡은 화면에서 사라져 있지만 메시지가 온 것을 받을 수 있고 알림을 받을 수 있다. 이는 서비스가 뒷단 또는 백그라운드에서 실행중이기 때문이다. 	
	
수신자(Broadcast Receiver) : 앱 간에 또는 구성 요소 간에 메시지를 주고받을 수 있도록 한 것으로 서비스와 마찬가지로 화면이 없는 상태에서 인텐트 안에 포함된 메시지를 주고받을 때 사용된다.   	
	
 <br/><br/>
 <hr/>

### 교재 354p : 서비스 예제

1. SampleService 프로젝트 생성.	
	
2. app > new > service를 만든다.	
	
app에 Service 파일을 만들어도 xml파일이 생기지 않는다. 화면이 없는 파일이기 때문

서비스의 파일에서 `ctrl + O` 를 누르게 되면 다음과 같은 창이 뜨게 된다.   
	
![image](https://user-images.githubusercontent.com/84966961/123376292-39354c80-d5c5-11eb-9da1-ab927b8cd02e.png)
	
동그라미 부분에 생명 주기에 관련된 메소드들이 있는 것을 알 수 있다. 	
	
또한, 서비스는 액티비티에 종속적이므로 액티비티가 하나 이상 있어야 한다.	
	
그 다음 onCreate, onDestroy, onStartCommand를 오버라이드 시켜준다.  	
	
![image](https://user-images.githubusercontent.com/84966961/123376761-faec5d00-d5c5-11eb-9225-b694dcfde0ab.png)
	
3. MainActivity 코드를 수정해준다.	
	
```java
public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("command","show");
                intent.putExtra("name",name);   // editText 내용을 name으로 intent 에 저장함.

                startService(intent);
            }
        });
    }
}
```

4. MyService.java 파일을 수정한다.		
	
 앞서 오버라이드해 두었던 onCreate, onDestroy, onStartCommand 생명 주기 확인을 위한 `Log.d`를 찍어본다.

**MyService**
```java
package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";


    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate() 호출");  // 생명주기를 보기위해서 로그를 찍음.
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand() 호출"); // 생명주기를 보기위해서 로그를 찍음.

        // 인텐트가 null이 아니면 processCommand로 인텐트를 넘겨 처리하도록 하는 기능.
        if (intent == null) {
            return Service.START_STICKY;
            // Service가 종료가 되었을 경우 자동으로 재시작 시켜주는 명령어.
        } else {
            processCommand(intent); // intent를 처리하는 메소드
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        // 메인액티비티에서 저장해둔 정보를 intent에서 부가데이터 가져오기
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra(":name");

        Log.d(TAG,"command : "+command+" / name : "+ name);
        // 제대로 들어왔는지 로그 찍어보기
        Toast.makeText(getApplicationContext(),Log.d(TAG,"command : "+command+" / name : "+ name),Toast.LENGTH_LONG).show();

        for (int i = 0; i <5; i++)  {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {};

            Log.d(TAG,"Waiting "+i+" seconds.");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() 호출");// 생명주기를 보기위해서 로그를 찍음.
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
```
	
**결과 **	
<img src="https://user-images.githubusercontent.com/84966961/123380204-22ddbf80-d5ca-11eb-8fe0-8c53c05c574a.png" width="40%">	
	
**LogCat**	
	
![image](https://user-images.githubusercontent.com/84966961/123380100-fe81e300-d5c9-11eb-86d6-1339e44d9836.png)
	

	
 <br/><br/>
 <hr/>

## 교재 362p : 06-2 브로드캐스트 수신자 이해하기		
	
 안드로이드에서 **브로드캐스팅(Broadcasting)**이란 **메시지를 여러 객체에 전달하는 것**을 말한다. 안드로이드도 여러앱 구성 요소에 메시지를 전달할 때 브로드캐스팅을 사용한다.	
	
 예를 들어, 다른 사람으로부터 문자를 받았을 때 이 문자를 SMS 수신 앱에 알려줘야 한다면 브로드캐스팅으로 전달하면 된다. 이런 메시지 전달 방식은 단말 전체에 적용할 수 있다. 그래서 이런 메시지 전달 방식을 `글로벌 이벤트(Global Event)`이라 부른다. 글로벌 이벤트의 대표적인 예로는 '전화가 왔습니다', '문자 메시지가 도착했습니다.'와 같은 사용자 알림을 들 수 있다.		
 	
 앱에서 브로드캐스팅 메시지를 받고 싶다면 브로드캐스트 수신자(Broadcast Receiver)를 만들어 앱에 등록하면 된다. 다시 말해, 기기 안에서 동작하는 다른 앱 A로부터 특정 메시지를 받기 위해 여러분이 만든 앱에 브로드캐스트 수신자를 등록하면 A 앱의 메시지가 여러분이 만든 앱으로 전달된다. 이때 서비스와 마찬가지로 브로드캐스트 수신자도 앱 구성 요소이다. 따라서 새로운 브로드캐스트 수신자를 만들면 매니페스트 파일에 등록해야 사용할 수 있다.	
 
 <br/>
 
#### **앱 4대 구성 요소 생성시 주의사항**	
 마법사를 이용해 액티비티, 서비스, 브로드캐스트 수신자, 내용 제공자 등을 만들때는 자동으로 `Manifest`에 등록되지만 따로 만들 때는 반드시 등록해주어야지만 앱이 정상적으로 인식하고 작동한다.	
	
![image](https://user-images.githubusercontent.com/84966961/123381496-c8ddf980-d5cb-11eb-8092-047791b13505.png)



 <br/><br/>
 <hr/>

### 브로드 캐스트 수신자 등록하고 사용하기	
	
 브로드캐스트 수신자에는 onReceive() 메서드를 정의해야 한다. 이 메서드는 원하는 브로드캐스트 메시지가 도착하면 자동으로 호출된다. 하지만 시스템의 모든 메시지를 받을 수는 없다. 만약 원하는 메시지만 받으려면 어떻게 해야 할까? 모든 메시지는 인텐트 안에 넣어 전달되므로 원하는 메시지는 인텐트 필터를 사용해 시스템에 등록하면 된다. 구체적인 내용은 예제를 통해 알아보자.	
	
 1. SampleReceiver 프로젝트를 만든다. 그 후 `MyService`와 `SmsReceiver`를 만들어 Manifest 파일에도 추가 되었는지 확인하자.
	
 2. 이 후 Manifest 파일에서 reciever 태그 내부에 다음과 같이 SMS만 알리도록 필터를 걸어준다.	
	
![image](https://user-images.githubusercontent.com/84966961/123383164-cf6d7080-d5cd-11eb-84ec-11240cc31e2f.png)
	

 <br/><br/>

 3. 이후 SmsReceiver.java 파일 코드를 구성한다.	
	
```java
public class SmsReceiver extends BroadcastReceiver {
    public static  final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨");

        Bundle bundle = intent.getExtras(); // 인텐트에서 Bundle 객체 가져오기
        SmsMessage[] messages = parseSmsMessage(bundle);    // parseSmsMessage() 메서드 호출

        if (messages != null && messages.length > 0){
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : " + sender);

            String contents = messages[0].getMessageBody();
            Log.i(TAG, "SMS contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG, "SMS received date : " + receivedDate.toString());

        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {

        // Bundle 객체에 들어가 있는 부가 데이터 중에서 pdus 가져오기
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i = 0; i < smsCount; i++) {
            // 단말 OS 버전에 따라 다른 방식으로 메서드 호출하기.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i],format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
```

 코드를 본 후 이해해보자.	
	
 SMS를 받으면 `onReceive()`가 호출되므로 우리는 `onReceive()`를 가로채서 override 시켜 내부 intent로부터 정보를 빼내오고 우리가 원하는 기능을 추가시켜주려고 한다. 그리고 파라미터로 전달되는 Intent 객체 안에 SMS 데이터가 들어있다. 먼저 onReveive() 메서드가 호출 되었는지 알 수 있도록 onReceive() 메서드 안에 로그 메시지를 출력하는 한줄을 추가한다. 그리고 인텐트 객체 안에 들어 있는 Bundle 객체를 getExtra() 메서드로 참조한다. 이 Bundle 객체 안에는 부가 데이터가 들어 있으며, parseSmsMessage() 메서드를 호출하여 SMS 메시지 객체를 만들도록 한다. parseSmsMessage() 메서드는 직접 정의한 메서드로 SmsMessage라는 자료형으로 된 배열 객체를 반환 하도록 되어 있다. 이 SmsMessage 객체에는 SMS 데이터를 확인할 수 있는 메서드들이 정의되어 있다. 	
	 
 parseSmsMessage() 메서드는 한 번 입력해 놓으면 다른 앱을 만들 때도 재사용할 수 있다. 왜냐하면 SMS 데이터를 확인할 수 있도록 안드로이드 API에 정해둔 코드를 사용하므로 수정될 일이 거의 없기 때문이다. 인텐트 객체 안에 부가 데이터로 들어 있는 SMS 데이터를 확인하려면 SmsMessage 클래스의 createFromPdu() 메서드를 사용하여 SmsMessage 객체로 변환하면 SMS 데이터를 확인할 수 있다. 이때 Build.VERSION.SDK_INT는 단말의 OS 버전을 확인할 때 사용한다. 안드로이드 OS는 계속 업데이트되면서 새로운 기능이 추가되어왔으므로 단말의 OS 버전에 따라 코드가 약간씩 달라져야 할 때가 있다. 다름과 같은 코드가 버전에 따라 다른 코드를 넣을 때 사용하는 전형적인 코드 중 일부이다.	
	 
`if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ...`	
	
 Build.VERSION.SDK_INT에는 안드로이드 OS 버전별로 상수가 정의도어 있다. 앞서 살펴본 코드는 OS가 마시멜로(첫글자 M) 버전과 같거나 그 이후 버전일 때 중괄호 안의 코드를 실행하겠다는 뜻이다.	
 	
 이제 다시 onReceive() 메서드로 돌아와 SmsMessage 객체에서 SMS 데이터를 확인하기 위한 메서드가 들어 있다. 발신자 번호를 확인하려면 getOriginatingAddress() 메서드를 호출한다. 그리고 문자 내용을 확인하려면 getMessageBody().toString() 코드를 사용한다. SMS를 받은 시각도 확인할 수 있다. 이런 데이터를 로그로 출력하도록 하자.
 
 그런데 이런 SMS를 수신하려면 이 앱에 권한이 있어야 한다.

 <br/><br/>

4. 단순히 코드만 완성되어도 앱에서 SMS를 수신하지 못하는데 이유는 Manifest에서 SMS 수신할 권한을 주지 않았기 때문이다. 따라서 Manifest에 권한을 주도록 하자.

![image](https://user-images.githubusercontent.com/84966961/123386303-79023100-d5d1-11eb-8556-952d7aa71f52.png)



 <br/><br/>

 5. 위험 권한을 수락할 수 있도록 외부라이브러리를 위한 코드를 gradle에 넣어주자.
	
![image](https://user-images.githubusercontent.com/84966961/123387290-971c6100-d5d2-11eb-9454-8b97870512ff.png)	
	
 이 후 sync Now를 눌러 싱크를 해주자.	
 

 <br/><br/>

 6. MainActivity 로 돌아와 `AutoPermissionsListener`를 구현해주어 위험 권한을 자동으로 부여할 수 있는 기능을 구현해주자.


![image](https://user-images.githubusercontent.com/84966961/123387520-d5b21b80-d5d2-11eb-90e3-4329812ba79f.png)

```java
public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions,this);
    }

    @Override
    public void onDenied(int i, String[] permissions) {
        Toast.makeText(getApplicationContext(),"permissions denied : "+ permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] permissions) {
        Toast.makeText(getApplicationContext(),"permissions granted : "+ permissions.length, Toast.LENGTH_LONG).show();
    }
}
```
 <br/><br/>

7. 교재대로 하면 androidx 이외의 라이브러리를 사용하지 못해서 오류가 나게 된다. 이 때 다음과 같이 gradle.properties에 들어가서 다음 코드를 추가시켜주면 된다.		
	
![image](https://user-images.githubusercontent.com/84966961/123391842-60951500-d5d7-11eb-8453-e526bb3fa69c.png)


 <br/><br/>

8. 에뮬에서 문자 보내는 법

![image](https://user-images.githubusercontent.com/84966961/123391209-bd440000-d5d6-11eb-9dfa-d9433e690be1.png)


**로그창 결과화면**

![image](https://user-images.githubusercontent.com/84966961/123392517-19f3ea80-d5d8-11eb-9cf5-ddb33678669b.png)







 <br/><br/>
<hr/>


