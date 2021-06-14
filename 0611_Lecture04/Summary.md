# 03 기본 위젯과 드로어블 사용하기
 Key Word : 위젯(Widget), 드로어블 폴더(R.drawable), 버튼(Button)의 종류(radio, checkbox, ...), 에딧 텍스트(EditText, planeText), Pressed event(손가락 표시), View.OnTouchListener, View.OnkeyListner, OnTouch 기능
   
<hr/>
   
## 171p : 03-1 기본 위젯 다시 한 번 자세히 공부하기

1. SampleWidjet 프로젝트를 생성하여 수업 진행! 다음과 같은 계층 구조를 만들어 준다.
![image](https://user-images.githubusercontent.com/84966961/121638774-7f1eea80-cac6-11eb-816c-ffe1cc164c0c.png)
   
<br/><br/><br/>
<hr/>
   
2. 실제 앱을 실행해보면 다음과 같은 화면이 뜨게 되는 데 상단의 `SampleWidget`이라는 문자열은 `R.values.strings.xml` 파일에 들어있는 정보이며, `R.manifests.AndroidManifest.xml` 내부의 `label` 에 지정되어있다.    
    
**R.values.strings.xml**
```
<resources>
    <string name="app_name">SampleWidget</string>
</resources>
```
   
**R.manifests.AndroidManifest.xml**
```
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"  // 이 값. at string의 app_name 값을 label로 지정하라.
 ...
```   
 **실행화면**    
 ![image](https://user-images.githubusercontent.com/84966961/121640233-a1b20300-cac8-11eb-87f1-6b47fbd73337.png)
   
<br/><br/><br/>
<hr/>
   
3. 첫번째 텍스트뷰에 들어올 값을 `R.values.strings.xml` 파일에 추가하여 사람 이름을 참조 시켜보자.   
   
**R.values.strings.xml**
```
<resources>
    <string name="app_name">SampleWidget</string>
    <string name="person_name">이동욱</string>
</resources>
```
   
**R.layout.activity_main.xml**
```
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/person_name"/>
 ...
```    
   
 **실행화면**
![image](https://user-images.githubusercontent.com/84966961/121640345-bf7f6800-cac8-11eb-90ef-46ef01144530.png)
   
<br/><br/><br/>
<hr/>
   
4. 173p 부터 각종 텍스트 코드를 넣었다.   
   
**R.layout.activity_main.xml**    
```
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="#FFFF0000"
        android:textSize="40sp"
        android:textStyle="bold"
        android:typeface="serif"
        android:text="@string/person_name"/>

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="40sp"
        android:textStyle="bold"
        android:typeface="serif"
        android:maxLines="1"
        android:text="여기에 이름을 입력하세요. 이름은 한 줄로 표시됩니다."/>
  ```
     
 **실행화면**    
![image](https://user-images.githubusercontent.com/84966961/121640502-f8b7d800-cac8-11eb-8b85-9f689035dc8c.png)
   
<br/><br/><br/>
<hr/>
   
5. 176p 코드에 maxline = 1을 넣게 되면 한줄만 나오게됨.
   
```
android:maxLines="1"
```   
   
**실행화면**    
![image](https://user-images.githubusercontent.com/84966961/121640719-3b79b000-cac9-11eb-9b22-3bd576b166b1.png)
   
<br/><br/><br/>
<hr/>
   
6. 177p 버튼은 사용자가 클릭하면 클릭에 대한 반응을 하는 위젯입니다.    
   radio : 동그란 버튼   
   checkbox : 체크 박스 버튼   
   과 같이 다양한 버튼이 존재함.   
    
 **팔레트 패널에 보이는 다양한 버튼들**
![image](https://user-images.githubusercontent.com/84966961/121640858-6d8b1200-cac9-11eb-8eb3-c92bba729774.png)
   
<br/><br/><br/>
<hr/>
   7. 버튼들을 골라 다음과 같이 정렬하였다.    
   
**실행화면**    
 ![image](https://user-images.githubusercontent.com/84966961/121641231-e1c5b580-cac9-11eb-800d-46f238dbcb79.png)
   
<br/><br/><br/>
<hr/>
   
8. 안드로이드 스튜디오 xml 파일의 코드는 HTML 방식과 비슷하고 자동 완성 기능과 직관적인 문법 명령어를 사용하기 때문에 코딩을 통한 디자인도 쉬운 편이다.    
   
**R.layout.activity_main.xml**
```
 ...
    <RadioGroup
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"
        android:paddingLeft="10dp"
        android:paddingRight="10dp">

        <RadioButton
            android:id="@+id/radioButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:textColor="#FFFF0000"
            android:textSize="40sp"
            android:text="남성"/>

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:textColor="#FFFF0000"
            android:textSize="40sp"
            android:text="여성" />
 ...
```
   
<br/><br/><br/>
<hr/>
   
9. 177p ~ 179p : 예제 완성   
    
![image](https://user-images.githubusercontent.com/84966961/121643100-6580a180-cacc-11eb-9596-c29f4fdf9fd5.png)![image](https://user-images.githubusercontent.com/84966961/121643502-d922ae80-cacc-11eb-8671-bedd755d3fd8.png)
   
<br/><br/><br/>
<hr/>
   
10. 180p : **에디트 텍스트**   
```
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:paddingTop="28dp"
        android:gravity="center_horizontal">

        <EditText
            android:id="@+id/editTextTextPersonName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ems="10"                         // ems : 글자수 제한?
            android:inputType="numberSigned"         // inputType : 입력되는 글자의 유형 정의
            android:text="이름을 입력하세요."         // 기본 안내문의 hint 표시
    </LinearLayout>
```
![image](https://user-images.githubusercontent.com/84966961/121644819-7af6cb00-cace-11eb-8731-2c28ba72dede.png)
   
   
<br/><br/><br/>
<hr/>
   
## 187p : 03-2 드로어블 만들기   
   
1. SampleDrawable 프로젝트 작성.   
2. 189p : 드로어블에 대한 설명   





   
<br/><br/><br/>
<hr/>
   
3. 버튼에 기본 이미지를 삽입한 상태에서 버튼을 눌렀을 때 버튼의 이미지가 변화하는 기능을 추가하려고 한다. 이를 위해 R.drawable 폴더에 finger_drawable.xml 파일을 생성후 다음과 같은 코드를 작성해준다.   
   
**R.drawable.finger_drawable.xml**
   
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
        android:drawable="@drawable/finger_pressed" />

    <item android:drawable="@drawable/finger"/>
</selector>
```   
   
<br/><br/><br/>
<hr/>
   
4. 이 코드를 이제 버튼의 백그라운드 속성에 xml 파일을 직접 넣어주면 버튼이 작동한다.(HTML 구성과 비슷.)     
    
**핸드폰 이미지 변환 전 후**    
![image](https://user-images.githubusercontent.com/84966961/121647178-11c48700-cad1-11eb-9d60-301d1324c689.png)![image](https://user-images.githubusercontent.com/84966961/121647132-040f0180-cad1-11eb-8c83-1b669345ca9e.png)

   
   
<br/><br/><br/>
<hr/>
   
## 196p : 03-3 이벤트 처리 이해하기   
   
1. 이벤트 처리 방식   
   
 지난 수업에서 이벤트 핸들러를 이용한 다양한 이벤트들을 이미 접하였다. 하지만 다른 디바이스와는 다른 스마트폰에서만의 이벤트 특징은 터치 이벤트가 가능하다는 점이다. 그 외에도 키 이벤트, 제스처 이벤트, 포커스, 화면 방향 변경 같은 이벤트들이 있다.   
   
 또한, 이벤트에 대한 각각의 View.OnTouchListener, View.OnkeyListner 등 다양한 리스너가 존재하며 일어난 이벤트들을 감지한다.   
   
 감지한 이벤트들을 198p의 테이블에 존재하는 다양한 메소드들을 오버라이딩(Override)하여 기능을 추가하는 것이다.
   
<br/><br/><br/>
<hr/>
   
2. SampleEvent 를 작성하여 Event 기능의 예제를 만들어보자.     
 먼저 디자인을 완성시켰다.   
**activity_main.xml**
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <View
        android:id="@+id/view"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:background="@android:color/holo_blue_dark"
        android:layout_weight="1"/>

    <View
        android:id="@+id/view2"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:background="@android:color/holo_orange_light"
        android:layout_weight="1"/>

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:id="@+id/textView"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="TextView">

            </TextView>
        </LinearLayout>
    </ScrollView>
</LinearLayout>
```
   
**구성 화면**     
![image](https://user-images.githubusercontent.com/84966961/121653188-5eab5c00-cad7-11eb-960a-ecb16b7e47c4.png)   
   
<br/><br/><br/>
<hr/>
   
3. 첫번째 화면에 대한 기능 구현을 다음과 같이하였다.   
```java
public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);     // 텍스트 뷰 주소값을 찾아옴.

        View view = findViewById(R.id.view);        // 뷰 주소값을 찾아옴.
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) { //첫번째 뷰를 터치했을 때 동작되는 메소드.
                int action = motionEvent.getAction();   // 액션에 대한 값이 정수형으로 저장됨. if문 사용.

                float curX = motionEvent.getX();        // 출력을 위한 모션이벤트의 x값 저장.
                float curY = motionEvent.getY();        // 출력을 위한 모션이벤트의 y값 저장.

                if (action == MotionEvent.ACTION_DOWN) {        // 손가락이 눌렸을 때
                    println("손가락 눌림 : " + curX + ", " + curY); // 기본으로 제공되는 메소드가 아닌 아래 새로 만들어준 메소드.
                } else if (action == MotionEvent.ACTION_MOVE) { // 손가락이 눌린 상태로 움직였을 때
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {   // 손가락이 떼졌을 때
                    println("손가락 뗌 : " + curX + ", " + curY);
                }

                return true;
            }
        });
    }

    public void println(String data) {
        textView.append(data + "\n");       // textView에 데이터를 보여라(더해라) 라는 기능을 만들어 본문에서 사용함.
    }
}
```
   
**실행 화면**   
![image](https://user-images.githubusercontent.com/84966961/121654722-fc535b00-cad8-11eb-9f2c-cea03f440180.png)   
   
 첫번째 파란색 뷰를 터치할시 아래 텍스트 뷰에 각각 좌표와 기능들이 찍히는 것을 알 수 있다.


