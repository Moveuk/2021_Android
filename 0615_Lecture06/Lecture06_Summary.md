# Lecture06 : 여러 화면 간 전환하기
Key Word : 레이아웃 인플레이션(Inflation) - 부분 화면 구성, [액티비티를 불러오는 방법 : 인텐트(Intent), 컴포넌트(Component)], ACTION_VIEW, intent.putExtra(), getIntent, 직렬화(Parcelable)
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
   `setContentView()` 메서드는 액티비티의 화면 전체(메인 레이아웃)를 설정하는 역할만을 수행한다. 즉, `setContentView()` 메서드로는 부분 화면(부분 레이아웃)을 메모리에 객체화할 수는 없다. 부분 화면을 메모리에 객체화 하려면 인플레이터를 사용해야 한다. 안드로이드는 이를 위해 시스템 서비스로 `LayoutInflater` 라는 클래스를 제공한다. 그런데 `LayoutInflater` 클래스는 시스템 서비스로 제공하는 클래스이브로 다음 `getSystemService()` 메서드를 이용하여 `LayoputInflater` 객체를 참조한 후 사용해야한다.


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
   
 위의 코드에서 마지막 `container`라는 id를 가진 `LinearLayout`이 부분 레이아웃의 부모, 프레임이 될 레이아웃이다.

<br/><br/>
<hr/>
   
 4. MainActivity와 MenuActivity 확인 및 불러오는 구조 확인하기.   
	 `app > manifests > AndroidManifest.xml` 파일을 보자.    
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
   
 안드로이드 앱의 네 가지 구성 요소로는 '액티비티(Activity)', '서비스(Service)', '브로드캐스트 수신자(Broadcast Receiver)', '내용 제공자(Content Provider)'가 있다. 어플리케이션을 실행 후 안드로이드 시스템은 앱을 구동하기 위하여 이 네 요소에 대한 정보를 요구한다. 이 정보에 대한 내용이 적혀 있는 파일이 `Manifest.xml`이다. 그렇기 때문에 새 액티비티를 생성하게 되면 `Manifest.xml` 파일에 추가하여 시스템이 인지할 수 있도록 해야한다.   
   
 ### 교재 247p : 액티비티를 띄우는 방법.   
   
 액티비티를 소스 코드에서 띄울 때는 startActivity() 메서드를사용하면 된다. 이 메서드는 단순히 액티비티를 띄워 화면에 보이도록 만든다. 하지만 실제로 앱을 만들다 보면 메인 액티비티에서 
   
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


### getApplicationContext()에 대한 이야기

 컨텍스트에 대해서 구글링해보다 나온 자료이다.
```
그 어떤 컨텍스트(Context)보다 오래 유지되는 컨텍스트(Context)가 필요할때에만 getApplicationContext()를 사용하십시오.
```
   
 여기서 `getApplicationContext()`는 this로 바뀔 수 있는데 this보다 오래 유지되려면 `getApplicationContext()` 자체로 사용하라는 것이다. 결국, `getApplicationContext()`는 현재 어플리케이션의 `맥락, 문맥`에서 어떤 정보, 클래스 등등을 찾고자 할 때 쓰이는 것 같다.


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
    //돌아오면 자동으로 콜백되어 호출되는 함수 작성.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 첫번째 인자 : 요청 코드(REQUEST_CODE_MENU), 두번째 인자 : 결과코드(Result_OK)  세번째 : intent 정보를 받음.
        // 패키지 형태로 정보다 갔다가 돌아옴.
//세번째 매개변수에서 메뉴엑티비티에서 보낸 intent를 전달받음
//리퀘스트코드는 메뉴화면을 띄울떄 전달한 101코드가 메뉴화면으로갓다가 다시 여기로 requestCode로 전달됨.
//즉 requestCode로 어떤화면으로부터 왔는지 알 수 있음

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
 `돌아가기` 버튼을 누르면 다시 MainActivity로 돌아가는 것을 확인할 수 있다. 이 후 토스트 메세지가 뜬다.   
   
 주석을 통해 코드를 이해하자. 3번째 인자를 통해 intent를 전달 받고 5번에서 `startActivityForResult(intent, REQUEST_CODE_MENU);` 을 통해 인텐트와 내 객체의 코드를 보내면 intent 내부에 MenuActivity 주소값을 가지고 있기 때문에 액티비티를 받게 되고, 띄우게 된다. 

 돌아오게 되면 콜백으로 `protected void onActivityResult(int requestCode, int resultCode, Intent data) {`가 자동으로 호출되고 돌아온 패키지 정보를 토대로 if문이 실행되는 구조이다. 그렇기 때문에 name, mike라는 값을 토스트에 띄울 수 있는 것이다.

<br/><br/>
<hr/>
   
 ## 교재 254p : 04-3 인텐트 살펴보기

 ### 교재 254p : 인텐트  
 1. 인텐트 설명
   
 내가 이해한 바로는 A 액티비티에서 B 액티비티로 넘어가게 해주는 기능과 그 기능에 붙어있는 패키지(다양한 정보들)를 말한다. 한마디로 폴더나 박스 같은 것.





 **액션과 데이터를 사용하는 대표적인 예**
 | 속성 | 설 명 |
 |---|---|
 | ACTION_DIAL tel:01077881234 | 주어진 전화번호를 이용해 전화걸기 화면을 보여줌. |
 | ACTION_VIEW tel:01077881234 | 주어진 전화번호를 이용해 전화걸기 화면을 보여줌. URI 값의 유형에 따라 VIEW 액션이 다른 기능을 수행함. |
 | ACTION_EDIT content://contackt/preple/2 | 전화번호부 데이터 베이스에 있는 정보 중에서 ID 값이 2인 정보를 편집하기 위한 화면을 보여줌. |
 | ACTION_VIEW content://contacts/people | 전화번호부 데이터 베이스의 내용을 보여줌. |
   
 **256p 인텐트의 생성자들**
 ```
[Reference]
 Intent()
 Intent(Intent o)
 Intent(String action[,Uri uri])
 Intent(Context packageContext, Class<?> cls)
 Intent(String action,Uri uri, Context packageContext,Class<?> cls)
```
<br/><br/>
<hr/>

### 교재 257p : 디바이스의 기존 인텐트를 이해하기 위한 예제.   
   
 1. 프로젝트 SampleCallIntent 생성 및 디자인
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical">

<!-- 전화번호를 입력할 상자 정의 -->
    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="010-1000-1000"
        android:textSize="24sp"/>

<!-- 전화걸기 버튼 정의 -->
        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="전화걸기"/>
</LinearLayout>
```
   
**구성 화면**   
<img src="https://user-images.githubusercontent.com/84966961/121985494-cc090680-cdcf-11eb-9632-473349f4f912.png" width="50%">

     
<br/><br/>
<hr/>
   
 2. 통화 다이얼을 띄우기 위한 기능 구현.
   
```java
public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 뷰 객체 참조

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();    // 입력상자에 입력된 전화번호 확인

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));    //전화걸기 화면을 보여줄 인텐트 객체 생성.
                startActivity(intent); // 액티비티 띄우기
            }
        });
    }
}
```
   
**실행화면**   
<img src="https://user-images.githubusercontent.com/84966961/121987081-b6e1a700-cdd2-11eb-83f0-d7498bc898ec.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/121987961-7125de00-cdd4-11eb-8562-5e891b443161.png" width="40%">
   
**%네이버 띄우기**   

 intent 의 data를 웹페이지 주소로 바꿔주면 웹 브라우저가 실행되게 된다. 실제로 우리가 만든 어플리케이션도 넣어주게되면 작동한다.
```
Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
```
<img src="https://user-images.githubusercontent.com/84966961/121987968-7420ce80-cdd4-11eb-8639-01f2b7f38330.png" width="40%">

<br/><br/>
<hr/>
   
 3. 교재 259p : 액티비티를 컴포넌트 방식으로 실행하기
    
 `MenuActivity` 라는 새로운 액티비티를 생성한 후 `MainActivity.java` 파일에 컴포넌트 형태로 생성하여 실행할 수 있는 코드를 작성한다. 또한, 기본 디자인에 버튼을 하나 더 추가하여 id를 button2로 만든다.   
    
```java
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // 컴포넌트 이름을 지정할 수 있는 객체 생성.
                ComponentName name = new ComponentName("org.techtown.samplecallintent",
                        "org.techtown.samplecallintent.MenuActivity");
                // 첫번째 인자 : 패키지 이름, 두번째 인자 : 클래스 이름
                intent.setComponent(name);  //인텐트 객체에 컴포넌트 지정
                startActivityForResult(intent, 101);    //액티비티 띄우기
            }
        });
    }
```
**실행 화면**   
<img src="https://user-images.githubusercontent.com/84966961/121988984-4b99d400-cdd6-11eb-9d98-0b19f6d96b03.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/121989030-5eaca400-cdd6-11eb-84e5-8bab387fac6b.png" width="40%">   
   
 실행 화면처럼 버튼 클릭시 MenuActivity로 넘어가는 것을 볼 수 있다.

<br/><br/>
<hr/>
   
### Mission : 다시 main으로 돌아오게 하기.
   
 MenuActivity에 버튼 만들고 그냥 간단하게 컴포넌트 만들어서 다시 패키지 이름, 액티비티 이름 넣어서 돌아오게 했음.

```java
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName name = new ComponentName("org.techtown.samplecallintent",
                        "org.techtown.samplecallintent.MainActivity");
                intent.setComponent(name);  //인텐트 객체에 컴포넌트 지정
                startActivityForResult(intent, 101);
            }
        });
    }
}
```


<br/><br/>
<hr/>
   
### Main에서 Menu로 데이터와 함께 넘어가기 : intent.putExtra()


```java
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // 컴포넌트 이름을 지정할 수 있는 객체 생성.
                ComponentName name = new ComponentName("org.techtown.samplecallintent",
                        "org.techtown.samplecallintent.MenuActivity");
                // 첫번째 인자 : 패키지 이름, 두번째 인자 : 클래스 이름
                intent.putExtra("username","park");
                intent.putExtra("password","asdf");
                intent.setComponent(name);  //인텐트 객체에 컴포넌트 지정
                startActivityForResult(intent, 101);    //액티비티 띄우기
            }
        });
```

 Main에서 클릭시 함께 `intent.putExtra("username","park");  intent.putExtra("password","asdf");` 코드로 정보를 넘겨주었다. 이후 Menu에서 다음과 같은 코드로 받아 변수, 필드에 넣어줄 수 있다.   
    
```java
        Intent receiveIntent = getIntent();
        String username = receiveIntent.getStringExtra("username");
        String password = receiveIntent.getStringExtra("password");
	
	Toast.makeText(this,"username : "+username+", password : "+password,Toast.LENGTH_LONG).show();

```

getIntent 객체를 만들고 이름과 패스워드 변수를 만들어 들어오는 값을 받아주고 들어온 값을 토스트 메세지로 출력해서 MenuActivity에서 받았는지 확인해 보았다.   
    
**실행 결과**   
<img src="https://user-images.githubusercontent.com/84966961/121997498-6cb5f100-cde5-11eb-81be-7f5abcafb34e.png" width="40%">   

<br/><br/>
<hr/>
   
### 교재 266p : 직렬화(Parcelable)   
   
 1. SimpleData 클래스 만든후 implements Parcelable 의 구현을 하고 오버라이드 함.
```java
public class SimpleData implements Parcelable {

    int number;
    String message;

    public SimpleData(int num, String msg) {  // 원하는 구조로 생성자 오버로딩 생성.
        number = num;
        message = msg;
    }

    protected SimpleData(Parcel in) {
        number = in.readInt();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleData> CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };
}
```

<br/><br/>
<hr/>
   
### 교재 268p : 직렬화(Parcelable)한 데이타 주고 받기
   
**MainActivity**
```java
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                SimpleData data = new SimpleData(100, "Hello Android!");    // SimpleData 객체 생성
                intent.putExtra(KEY_SIMPLE_DATA, data);                 // 인텐트에 부가 데이터 넣기
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
}
```
   
**MenuActivity**
```java
public class MenuActivity extends AppCompatActivity {
    TextView textView;

    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name","mike");
                setResult(RESULT_OK,intent);

                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA);
            if (intent != null) {
                textView.setText("전달 받은 데이터\nNumber : "+ data.number + "\nMessage : "+ data.message);
            }
        }
    }
}
```

**실행 결과**   
<img src="https://user-images.githubusercontent.com/84966961/122004036-d7b7f580-cdee-11eb-85d2-c631e18321b1.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/122004054-dd154000-cdee-11eb-8c3e-137fb78e3eb2.png" width="40%">  
