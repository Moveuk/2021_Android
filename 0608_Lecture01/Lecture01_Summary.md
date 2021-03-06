# Lecture01 : Hello Android
Key Word : 안드로이드 스튜디오 설치 및 AVM 설치, 프로젝트 생성.   
   
<hr/>

Android Studio 설치 방법은 생락!

<hr/>

### 32p : Android Studio 초기 화면 설명.

<img src="https://user-images.githubusercontent.com/84966961/121198414-13156a00-c8ad-11eb-86cd-ecaab7203c56.jpg">
 Android Studio를 실행한 후 프로젝트를 만들면 다음과 같은 화면이 보이게 된다.   
 초기화면에는 MainActivity.java 파일이 실행되어있고, app 실행시 보이게 할 초기 화면을 구성하는 코드가 작성되어 있다.   
    
    왼쪽 패널에는 res(resource) 폴더가 보이며 내부에는 drawable(image폴더), layout(app화면 XML폴더), mipmap, values 등의 폴더가 들어있다.
 
 ```java
 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); } }
 ```
 setContentView(R.layout.activity_main)는 앱 초기화면을 보여주는 코드로 R.은 Resource의 약자로 왼쪽 목록의 res 폴더를 가리킨다.   
 즉 위의 코드는 res.layout 폴더 내부의 activity_main.xml을 호출한다.   
 
 다음은 MainActivity.java 파일 옆의 activity_main.xml 탭을 눌러보자.   
<img src="https://user-images.githubusercontent.com/84966961/121198423-1577c400-c8ad-11eb-89f4-9602292ca7c4.jpg">

 위의 그림은 activity_main.xml 파일의 디자인(design) 화면(오른쪽 위의 code, split, design)입니다.   
 스마트폰 화면처럼 보이는 두 개의 직사각형 중 왼쪽은 디자인 화면이고, 오른쪽 남색 화면은 화면의 구성 요소만을 보여주는 청사진 화면(Blue Print)입니다.
 
  디자인은 보이는 그대로 디자인을 위한 화면이며 블루 프린트는 겹쳐져 있는 요소들을 쉽게 식별하기 위한 화면이다.

   아래의 화면은 split 화면으로 코드와 디자인 화면이 동시에 보이는 것을 알 수 있다.

<img src="https://user-images.githubusercontent.com/84966961/121198421-14df2d80-c8ad-11eb-9fd4-ed49f1979eef.jpg">

### 35p : 에뮬레이터 실행, AVD(Android Virtual Device) 설치 및 생성

Android Emulator를 설치하려면 SDK Manager의 SDK Tools 탭에서 Android Emulator 구성요소를 선택하면 된다. 자세한 방법은 SDK Manager를 사용하여 도구 업데이트를 참조.

![image](https://user-images.githubusercontent.com/84966961/121202477-486f8700-c8b0-11eb-9adc-bbb4a1be9de3.png)

 위의 툴바와 같이 대상 기기를 정하거나 오른편의 핸드폰 버튼을 눌러 새로운 가상 디바이스를 생성하여 에뮬레이터를 실행할 수 있다.
    
 실행 후 다음과 같은 화면의 에뮬레이터가 실행되며 테스트를 해볼 수 있다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121210313-8a9bc700-c8b6-11eb-9351-7b4ec314d625.png)
 
   <hr/>
   
   ### 52p : 여러 버튼 추가

![image](https://user-images.githubusercontent.com/84966961/121211087-2af1eb80-c8b7-11eb-84b9-1edcce45b393.png)

 **Palette**
 
 Palette 패널을 통해 다음과 같은 BUTTON을 추가할 수 있다. 또한 오른쪽 Attribute(속성) 패널에서 다양한 값들을 조절할 수 있다.    
   
 onClick 기능을 활성화 하기 위해 onButton1Clicked 이라는 메소드 이름을 넣어준다.
 
 
**Component Tree**
 왼쪽에는 Componet Tree 패널이 존재한다.
 눈에 보이지는 않지만 이 화면에 어떤 뷰들이 어떤 계층 구조로 만들어져있는지 모여주는것.   
   
      
         

**onClick 기능 활성화**	

 그 다음 MainActivity.java 파일에서 다음과 같은 코드를 추가하면, 네이버로 이동하는 링크 버튼을 활성화 할 수 있다.   
 
  또한, 아래와 같은 코드를 통해 전화번호로 바로 연결 또한 가능하다.

```java
    public void onButton1Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }
    public void onButton2Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(myIntent);
    }
```
   
  Intent : 내가 하고자 하는 행위, 안드로이드 플랫폼에게 원하는 것을 말할 때 전달하는 우편물 같은 것.
  
  Intent를 통하여 네이버 사이트 접속, 전화 걸기 등이 가능해진다.
  
  <hr/>
  
  ### 60p : 실제 단말기를 통한 테스트.

   생략.
   
  <hr/>
  
  ### 86p : 뷰와 뷰의 크기 속성 이해하기

  뷰(View)는 일반적으로 컨트롤이나 위젯으로 불리는 UI 구성요소이다.
  즉, 사용자 눈에 보이는 화면의 구성 요소들이 바로 뷰이다.

![image](https://user-images.githubusercontent.com/84966961/121216007-7908ee00-c8bb-11eb-9a94-120801aa2610.png) <img src = "https://user-images.githubusercontent.com/84966961/121216045-7efecf00-c8bb-11eb-92d4-2f0cef8cca0a.png" width="30%">

화면의 구성 요소 : 뷰
사용자 눈에 보이는 컨트롤 역할을 하는 뷰 : 위젯

뷰를 담고 있는 그릇 : 뷰그룹
뷰그룹 안의 뷰를 배치하는 것 : 레이아웃


```xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
 --> 제약조건 레이아웃
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
```

 위의 코드는 레이아웃의 기본 코드이다.
 
 뷰는 반드시 위의 코드처럼 layout_width와 layout_height라는 필수 속성을 지정해 줘야한다.

    android:layout_height="match_parent"

 다음과 같은 크기 속성 종류가 존재한다.   
 
	match_parent : 창을 부모창에 맞춰서 보여줘라.

	wrap_content : 컨텐츠의 크기에 맞게 씌워라.

<hr/>

  ### 94p : 레이아웃 기초 익히기

   
  **제약조건**
  뷰가 레이아웃 안의 다른 요소와 어떻게 연결되는지 알려주는 것으로, 뷰의 연결점(Anchor Point)와 대상(Target)을 연결한다.
  
  뷰에는 핸들(Side Constraint Handle)이라 불리는 동그라미 모양의 버튼이 존재한다.

1. 버튼의 제약 조건을 성립하도록 기준점을 잡아준다.

2. 뷰의 위치 설정 magnet 표시 on/off
	-> 절대 위치, 상대 위치 설정 가능.

![image](https://user-images.githubusercontent.com/84966961/121217815-29c3bd00-c8bd-11eb-9377-8f768c511d54.png)

3. 뷰 미리보기 화면에서 <<(wrap_content : 내용에 맞춤), 지그재그(기준점까지 늘림), fixed 표시로 크기를 설정할 수 있다.

![image](https://user-images.githubusercontent.com/84966961/121218057-5d9ee280-c8bd-11eb-8a1f-669bf136a963.png)


4. 디자인 미리보기 화면 위의 탭에서 가이드라인을 통해
레이아웃 이외에도 기준점을 잡을 수 있도록 가이드라인을 설치할 수 있다. 
<img src = "https://user-images.githubusercontent.com/84966961/121218379-a5256e80-c8bd-11eb-92b0-fb5ed7a55615.png"> <img src = "https://user-images.githubusercontent.com/84966961/121218702-ef0e5480-c8bd-11eb-9947-434bc1979ed7.png">


아래 코드는 가이드 라인의 코드이며 코드 내부에는 가이드 라인의 방향 코드가 들어있다.

```
  --   <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
	--> 가이드 라인의 방향 코드 추가됨.
        app:layout_constraintGuide_begin="161dp" />
```

5. 뷰 코드에는 기준점을 어디로 잡았는지 어디로 붙였는지(Anchor) 코드에 나와있다.
```
    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline4"
	--> 버튼 3은 Start를 잡아서 스타트에 붙였고 주소값은 가이드라인 4임.
        app:layout_constraintTop_toTopOf="parent" />
	--> 버튼 3의 탑 기준점을 잡아서 부모 레이아웃의 탑에 붙였다.
```
