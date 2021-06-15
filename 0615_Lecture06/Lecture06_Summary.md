# Lecture06 : 여러 화면 간 전환하기
Key Word : 
<hr/>
   
# 04 여러 화면 간 전환하기   
## 교재 235p : 04-1 레이아웃 인플레이션 이해하기   
   
 XML 레이아웃의 내용이 메모리에 객체화 되는 과정을 '인플레이션(Inflation)이라고 한다. XML 레이아웃은 앱이 실행되는 시점에 메모리에 객체화된다. 다음은 XML 레이아웃을 먼저 불러오지 않고 레이아웃 객체 내부의 뷰 id를 참조하는 오류 예제이다.

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        												// 먼저 XML 파일을 불러와서
        findViewById(R.id.textView); ...           	 	// 검색을 해야함.
        	
        
        setContentView(R.layout.activity_main);			// 먼저 레이아웃을 불러와야 ID를 보고 뷰를 find할 수 있음.
    }
}
```
   
 이렇게 오류가 발생하면 안드로이드 스튜디오의 Logcat 창에 빨간색 오류 로그가 출력된다.
   
`Caused by : java.lang.NullPointerException : Attmpt to invoke virtual method`   
   

<br/><br/>
<hr/>
      
 ### setContentView() 메서드   
   setContentView() 메서드는 액티비티의 화면 전체(메인 레이아웃)를 설정하는 역할만을 수행한다. 즉, setContentView() 메서드로는 부분 화면(부분 레이아웃)을 메모리에 객체화할 수는 없다. 부분 화면을 메모리에 객체화 하려면 인플레이터를 사용해야 한다. 안드로이드는 이를 위해 시스템 서비스로 LayoutInflater 라는 클래스를 제공한다. 그런데 LayoutInflater 클래스는 시스템 서비스로 제공하는 클래스이브로 다음 getSystemService() 메서드를 이용하여 LayoputInflater 객체를 참조한


<br/><br/>
<hr/>
   
 ### 부분 뷰를 만들어 부분 화면 표시하기
 1. SampleLayout 프로젝트를 생성한다.   

<br/><br/>
<hr/>
   
 2. 전체 액티비티 구성하기   
	 239p처럼 MenuActivity 라는 새로운 액티비티를 생성한다. (Empty Activity 생성)   
![image](https://user-images.githubusercontent.com/84966961/121975811-79beea00-cdbd-11eb-8f01-e7223a92727a.png)   
   
<br/><br/>
<hr/>
   
 3. Latout을 구성한다.   
![image](https://user-images.githubusercontent.com/84966961/121975873-9eb35d00-cdbd-11eb-90a2-36b83c44c7a1.png)   
```
    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="버튼을 눌러 부분 화면을 추가하세요."
        android:textSize="20sp"/>

    <Button
        android:id="@+id/Button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="추가하기"/>

    <LinearLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"/>
```
   
 위의 코드에서 마지막 container라는 id를 가진 LinearLayout이 부분 레이아웃의 부모, 프레임이 될 레이아웃이다.

<br/><br/>
<hr/>
   
 4. MainActivity와 MenuActivity 확인 및 불러오는 구조 확인하기.   
	 app > manifests > AndroidManifest.xml 파일을 보자.    
	 다음과 같이 코드를 바꿔주게되면 MenuActivity가 먼저 실행되게 된다.   
    
**변경 전 코드**   
![image](https://user-images.githubusercontent.com/84966961/121976004-e1753500-cdbd-11eb-8fda-a1a34654bfa9.png)    
**변경 후 코드**   
![image](https://user-images.githubusercontent.com/84966961/121976050-fb167c80-cdbd-11eb-88f6-352cab8e3d3f.png)   
   


5. 부분화면 뷰 생성하기   
	부분 화면에 배치할 뷰를 R.layout 에 `sub1.xml` 라는 이름으로 생성 후 다음과 같은 layout을 구성했다.(LinearLayout으로...)      
   
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="30sp"
        android:text="부분 화면 1" />

    <CheckBox
        android:id="@+id/checkBox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="동의합니다." />
</LinearLayout>
```
   
![image](https://user-images.githubusercontent.com/84966961/121976752-6dd42780-cdbf-11eb-94d3-487ae8d13616.png)   
   
<br/><br/>
<hr/>
   
 6. 전체 레이아웃(MenuActivity)에 부분 레이아웃(sub1) 추가하기   
   
 전체 레이아웃 파일인 `MenuActivity.java` 파일에 다음과 같은 코드를 작성하게 되면 버튼 클릭시 부분 레이아웃이 화면에 표출되는 것을 알 수 있다.   
```java
public class MenuActivity extends AppCompatActivity {
    LinearLayout container;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        container = findViewById(R.id.container);                   // 컨테이너 주소값 할당.

        Button button = findViewById(R.id.Button);                  // 버튼 주소값 할당.
        button.setOnClickListener(new View.OnClickListener() {      // 버튼이 클릭되면(이라는 메소드? 클래스?)
            @Override
            public void onClick(View v) {                           // 클릭시
                // 레이아웃 인플레이터를 시스템 서비스에서 불러와서
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
                // sub1을 컨테이너에 보이게 해라.
                inflater.inflate(R.layout.activity_menu,container, true);
                // 부분 화면 정의 후 checkbox text를 로딩되었습니다로 바꿔라.
                CheckBox checkBox = container.findViewById(R.id.checkBox);  // container에 있는 checkbox라고 특정지어 줘야함.
                checkBox.setText("로딩되었습니다.");
            }
        });
                
    }
}
```


<br/><br/>
<hr/>
   
## 교재 246p : 04-2 여러 화면 만들고 화면 간 전환하기   
   
   forResult	: 화면 전환을 통한 정보처리   
   forActivity	: 일반적인 화면 전환   
   
 1. SampleIntent 프로젝트 생성   

 Manifests 파일을 보면 액티비티가 아직 한 개 뿐인 것을 확인할 수 있다.    
    
 ![image](https://user-images.githubusercontent.com/84966961/121978781-e3da8d80-cdc3-11eb-8997-47de3f983cb0.png)
   

<br/><br/>
<hr/>
   
 2. 새로운 액티비티 : MenuActivity 생성   
   
 생성 이후 Manifests 를 확인해보게 되면 MenuActivity가 추가된 것을 확인할 수 있다. 생성 마법사를 사용하지 않았다면 수작업으로 코드를 작성해 주어야 한다.   
   
![image](https://user-images.githubusercontent.com/84966961/121979077-6bc09780-cdc4-11eb-914f-6ddf991a5763.png)   


<br/><br/>
<hr/>
   
 3. 교재 248p : 메세지창 형태로 액티비티를 띄우기   
   
 Manifests 코드에 다음과 같이 코드를 추가해주면 액티비티가 대화상자 형태로 나타납니다.   
    
 ![image](https://user-images.githubusercontent.com/84966961/121979280-d4a80f80-cdc4-11eb-85cd-c7f392ec8bcd.png)



<br/><br/>
<hr/>
   
 4. Activity_Menu.xml 에 '돌아가기' 버튼 추가하기   
   
 이 버튼은 Main에서 새로운 액티비티 창(Menu)을 띄운 후 새창에서 `돌아가기` 버튼을 누르면 대화상자가 사라지는 기능을 넣기 위해 만들었다.    
   
![image](https://user-images.githubusercontent.com/84966961/121979469-3799a680-cdc5-11eb-992c-13cb0f706bc3.png)   
    

<br/><br/>
<hr/>
   
 5. MainActivity에 화면 전환 기능 추가하기.
   
```java
public class MainActivity extends AppCompatActivity {

    public  static final int REQUEST_CODE_MENU = 101;   // 내가 호출한 액티비티를 확인하기 위한 상수(임의 정의)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class); // 우리가 필요한 정보를 설정하는 객체.
//응답을 받을 경우는 startActivityForResult를 사용한다.
//아니면 그냥 StartActivity(intent)라 하면된다.
//즉 둘다 액티비티를 화면에 띄우고 인텐트를 전달해주는 역할
                startActivityForResult(intent, REQUEST_CODE_MENU); // 새로운 화면 전환을 startActivity를 이용해서 할 수 있다.
            }
        });
    }
}
```
    
**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/121980630-3bc6c380-cdc7-11eb-8ab7-9f85314b66b0.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/121980632-3e291d80-cdc7-11eb-948e-0940cc59b0c9.png" width="40%">


<br/><br/>
<hr/>
   
 6. `돌아가기` 버튼 구현
   
```java
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name","mike"); // 정보를 넘겨줌. 인텐트 객체 생성하고 name의 값을 부가 데이터로 넣기
		// name = "mink"; 개념인듯? 정보를 보내서 저장해둠. main에서 찾아서 토스트 메세지에 넣음.
                setResult(RESULT_OK, intent); // 나를 호출한 객체에게 정보값을 보냄. 응답 보내기
                finish();  // 두개의 액티비티 일 땐 닫으면 다시 돌아감. 현재 액티비티 없애기
            }
        });
    }
}
```
   
**MainActivity.java 코드 추가**
```java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 첫번째 인자 : 요청 코드(REQUEST_CODE_MENU), 두번째 인자 : 결과코드(Result_OK)  세번째 : intent 정보를 받음.
        // 패키지 형태로 정보다 갔다가 돌아옴.

        // menu에서 main으로 돌아올 때 토스트 메세지 .
        if(requestCode == REQUEST_CODE_MENU){   // 내가 정한 코드랑 같으면 토스트로 확인 메시지를 띄워라.
            // 내가 보낸 리퀘스트인지 확인할 수 있음.
            Toast.makeText(getApplicationContext(),
                    "onActivityResult 메서드 호출됨. 요청 코드 : " + requestCode +
                    ", 결과 코드 : "+ resultCode, Toast.LENGTH_LONG).show();

            if (resultCode == RESULT_OK) {      // 완료 후 토스트 메세지
                String name = data.getStringExtra("name");
                Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
```
 `돌아가기` 버튼을 누르면 다시 MainActivity로 돌아가는 것을 확인할 수 있다. 이 후 토스트 메세지가 뜬다



<br/><br/>
<hr/>
   
7. 1







<br/><br/>
<hr/>
   
8.
