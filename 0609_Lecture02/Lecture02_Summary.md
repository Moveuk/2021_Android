# Lecture02 : Layout
 Key Word : 안드로이드 스튜디오 화면 설명(코드창, 디자인창, 블루프린트, 등), 뷰의 개념, 레이아웃의 개념, 제약 조건과 레이아웃   
<hr/>
   
### 114p : 02 레이아웃 익히기.

 #### 02-1 안드로이드에 포함된 대표적인 레이아웃 살펴보기.
 
 안드로이드에서 제공하는 레이아웃들은 다음과 같다.
 
 1. 제약 레이아웃 - 제약 조건(Constraint) 기반 모델, 제약 조건을 사용해 화면을 구성하는 방법.
 2. 리니어 레이아웃 - 박스(Box) 모델, 한 쪽 방향으로 차례대로 뷰를 추가하며 화면을 구성하는 방법.
 3. 상대 레이아웃 - 규칙(Rule) 기반 모델, 부모컨테이너나 다른 뷰와의 상대적 위치로 화면을 구성하는 방법.
 4. 프레임 레이아웃 - 싱글(Single) 모델, 가장 상위에 있는 하나의 뷰 또는 뷰그룹만 보여주는 방법.
 5. 테이블 레이아웃 - 격자(Grid) 모델, 격자 모양의 배열을 사용하여 화면을 구성하는 방법.

![image](https://user-images.githubusercontent.com/84966961/121297314-19e3c180-c92d-11eb-9865-2f8297a7783a.png)
   
      
 또한, **뷰**는 **마진, 패딩, 보더**와 같은 뷰의 영역으로 구성되어 있고, **배경색** 또한 지정해 줄 수 있다.

  #### 120p : 02-2 리니어 레이아웃 사용하기.
    
 새로운 프로젝트를 생성하여 진행하였다. 프로젝트 제목은 SampleLinearLayout 이다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121297525-6202e400-c92d-11eb-82a0-778fd2caf1fe.png)

 프로젝트 생성 후 activity_main.xml 의 코드를 보면 1강의 내용처럼 현재 constraintLayout 즉 제약 레이아웃이 디폴트값으로 되어있는 것을 알 수 있다.
 
 코드를 통해 변경 할 수 있지만 우리는 Design 패널을 통하여 변경해주었다.

 ![image](https://user-images.githubusercontent.com/84966961/121297814-d8074b00-c92d-11eb-9cbd-c2eb065d18f1.png)

 디자인 화면에서 Component Tree에 현재는 제약 레이아웃이 되어있는 것이 보인다.
 이 것을 우클릭하여 Convert View 기능을 통해 아래와 같이 Linear Layout으로 바꿔줄 수 있다.   
    
<img src = "https://user-images.githubusercontent.com/84966961/121298139-5368fc80-c92e-11eb-8422-69b8a776474e.png" width="30%"> -> <img src = "https://user-images.githubusercontent.com/84966961/121298274-90cd8a00-c92e-11eb-85e9-6bd26f721fa9.png" width="30%"> -> <img src = "https://user-images.githubusercontent.com/84966961/121298314-9dea7900-c92e-11eb-8ef4-7b6ddf883143.png" width="30%">

 그 후 Layout의 방향(orientation)을 수직(Vertical)으로 바꿔주고 버튼 3개를 Component Tree에 계층적으로 넣었다.
   
 **방향(orientation)** 이란 레이아웃 내부의 뷰들이 어떤 방향(아래로 혹은 오른쪽)으로 배치될지 정하는 것이다.

<img src = "https://user-images.githubusercontent.com/84966961/121298419-cc685400-c92e-11eb-800f-2f04f5c40109.png" width="50%"><img src = "https://user-images.githubusercontent.com/84966961/121298539-05a0c400-c92f-11eb-8226-db589db92351.png" width="50%">

 방향을 수평으로 바꿔주게 되면 버튼의 크기 속성(match_parent)으로 인해 화면을 넘어가버리게 되므로(화면 오른쪽 너머에 존재한다는 이야기이다.) 모두 wrap_content로 바꿔주면 버튼 3개가 모두 잘보이게 된다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121299149-ec4c4780-c92f-11eb-8c4b-e707957693fd.png)
 ![image](https://user-images.githubusercontent.com/84966961/121299185-fc642700-c92f-11eb-9646-e8fb39d086c9.png)


 이런 과정들을 XML로 할 수 있지만, 동적인 요소와 같은 기능을 사용하기 편하려면 java 파일을 만들어 사용할 수 있다.

<img src = "https://user-images.githubusercontent.com/84966961/121299405-5369fc00-c930-11eb-9323-74214d6cfc28.png" width="50%">

 위의 그림처럼 MainActivity를 복사하여 LatoutCodeActivity로 만들어주고 열게 되면 아래와 같은 코드가 생성되어 있다.
 
 127p code 입력을 통하여 레이아웃 설정을 변경할 수 있다.
 ```java
 package org.techtown.samplelinearlayout;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LayoutCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    LinearLayout mainLayout = new LinearLayout(this);
    mainLayout.setOrientation(LinearLayout.VERTICAL);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );

    Button button1 = new Button(this);
    button1.setText("Button1");
    button1.setLayoutParams(params);
    mainLayout.addView(button1);

    setContentView(mainLayout);    
    }
}
 ```
 
 위의 코드 중 6번째 라인의 AppCompatActivity를 상속받아 안드로이드는 실행되며 안드로이드는 Activity 단위로 구성되어 어플리케이션이 구동된다.
 
 즉, 다양한 기능의 Activity가 모여 한 어플리케이션을 구성하게 된다.
 
 **Activity** : 어플리케이션에서 하나의 화면
 
    
    
 코드작성을 마무리한 후에 실행을 해도 코드 작성대로 표시가 되지 않는데 이유는 manifests.AndroidManifest.Xml 파일에 설정을 해주지 않았기 때문이다.
 
 manifests.AndroidManifest.Xml에는 어플리케이션에 필요한 액티비티들을 설정하는 파일이다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121302213-536bfb00-c934-11eb-882d-8ee365510731.png)

  위 코드의 "activity android:name=".MainActivity"를 "activity android:name=".LayoutCodeActivity"로 바꾸어주면 잘 실행되는 것을 볼 수 있다.
  
```
  <?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="org.techtown.samplelinearlayout">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.SampleLinearLayout">
        <activity android:name=".LayoutCodeActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
  ```
  실행화면
  <img src = "https://user-images.githubusercontent.com/84966961/121317746-8ec2f580-c945-11eb-8099-c3bdb6371ba8.png" width="30%">
   
      
  <hr/>
     
        
 #### 130p : 뷰 정렬하기 Gravity
 
  layout_gravity  : 부모 컨테이너의 여유 공간에 뷰가 모두 채워지지 않아 여유 공간이 생겼을 때 여유 공간 안에서 뷰를 정렬함.    
  gravity         : 뷰안에 표시하는 내용물을 정렬함.    
                    (텍스트뷰의 경우 내용물은 글자가 되고, 이미지뷰의 경우 내용물은 이미지가 됨)
  
   Gravity xml 을 만들어준다.
  
 ![image](https://user-images.githubusercontent.com/84966961/121303067-924e8080-c935-11eb-860e-976c8a7edd27.png)
  
 버튼 3개를 넣어주고 레이아웃은 방향을 vertical로 설정해주고 각각 버튼의 너비 높이 속성을 wrap_content로 설정하고, **layout_gravity** 값을 left, center, right로 설정해주면 다음과 같은 실행 화면이 되게 된다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121303899-975fff80-c936-11eb-93ce-ed83bcf613b6.png)

  다음은 텍스트 3개를 넣어 Left, Right, Center 내용을 text 속성에 넣어준 후, gravity 속성값에 각각 left / right / center_hor..., center_ver... 을 주었다.
  
 ![image](https://user-images.githubusercontent.com/84966961/121304869-db9fcf80-c937-11eb-8ac8-625d071af934.png)

  다음과 같이 Main.Activity.java 에 시작 xml을 gravity로 바꿔준 후 실행하면 다음과 같은 화면이 나온다.
  
  
```java
  public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gravity); } }
```
  
<img src = "https://user-images.githubusercontent.com/84966961/121305480-96c86880-c938-11eb-802a-9aa810d3580c.png" width="40%">
  
 
  <hr/>
  
 #### 135p : 다양한 gravity 속성값들

 <img src = "https://user-images.githubusercontent.com/84966961/121306766-33d7d100-c93a-11eb-96a8-11a841c7ffe6.png" >
![image](https://user-images.githubusercontent.com/84966961/121306833-47833780-c93a-11eb-8597-2f0a1736d1e8.png)
 
 
 <hr/>
 
 #### 139p : 뷰의 마진과 패딩 설정하기
   <img src = "https://user-images.githubusercontent.com/84966961/121306920-6386d900-c93a-11eb-8c45-259b5b0e7fde.png"  width="35%">

 padding.xml 생성
 
 ![image](https://user-images.githubusercontent.com/84966961/121307979-82d23600-c93b-11eb-89d5-53be79f06f3f.png)
 
 
   아래 코드와 화면처럼 padding값 margin값을 넣어줄 수 있고, 패딩과 마진값이 과도하게 커 레이아웃을 벗어나려 할 경우 모양이 찌그러지며 제 형상을 온전히 가지고 있지 못하게 된다. 따라서, 적절한 사이즈를 가지고 있는 것이 좋다.
 

```
    <TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:layout_weight="1"
        android:background="#FFEB3B"
        android:padding="20dp"
        android:text="TextView"
        android:textColor="#FF0000"
        android:textSize="24sp" />
```
    
 ![image](https://user-images.githubusercontent.com/84966961/121309674-6fc06580-c93d-11eb-9152-52e08f0c2966.png)
   
    
 <hr/>
 
  #### 142p : 여유 공간을 분할하는 layout_weight 속성
 
![image](https://user-images.githubusercontent.com/84966961/121311191-22dd8e80-c93f-11eb-9d9a-7c31ee7e0312.png)

 ```xml
     <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:orientation="horizontal">

        <TextView
...
            android:layout_weight="1"
... />

        <TextView
...
            android:layout_weight="1"
.../>
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1">

        <TextView
...
            android:layout_weight="1"
... />

        <TextView
...
            android:layout_weight="2"
... />

    </LinearLayout>
 ```
 
 코드의 내용 처럼 weight의 크기를 통해 뷰들을 분할할 수 있다.
 
  <hr/>   
  
  **실행화면**
 ![image](https://user-images.githubusercontent.com/84966961/121311791-b616c400-c93f-11eb-92d4-450e4b331f2d.png)

     
 
  <hr/>   
   
 #### 147p : 상대 레이아웃 사용하기.
 <img src = "https://user-images.githubusercontent.com/84966961/121312430-51a83480-c940-11eb-8878-93131da99325.png" width="30%">


  **프로젝트 SampleRealativeLayout 생성**
    
    Component Tree의 레이아웃을 상대 레이아웃(RelativeLayout) 으로 Convert Virw.. 해준다.
     
 <img src = "https://user-images.githubusercontent.com/84966961/121312852-c0858d80-c940-11eb-86c4-aa433a9b7e0e.png" width="50%">

   이후 버튼 3개로 layout_alignParentleft, layout_below, ... 등과 같은 속성으로 버튼을 다음과 같이 정렬해주었다.   
    
 ![image](https://user-images.githubusercontent.com/84966961/121315755-9c777b80-c943-11eb-87f0-36567fcafebd.png)

 <hr/>
 
 
 **상대 레이아웃에서 부모 커네이너와의 상대적 위치를 이용하는 속성**   
 
 <img src = "https://user-images.githubusercontent.com/84966961/121315887-badd7700-c943-11eb-9774-4f848841491a.png" width="50%">
    
```
layout_alignParentTop    : 부모 컨테이너의 위쪽과 뷰의 위쪽을 맞춤

layout_alignParentBottom : 부모 컨테이너의 아래쪽과 뷰의 아래쪽을 맞춤 

layout_alignParentLeft   : 부모 컨테이너의 왼쪽 끝과 뷰의 왼쪽 끝을 맞춤

layout_alignParentRight  : 부모 컨테이너의 오른쪽 끝과 뷰의 오른쪽 끝을 맞춤 

layout_centerHorizontal  : 부모 컨테이너의 수평 방향 중앙에 배치함 

layout_centerVertical    : 부모 컨테이너의 수직 방향 중앙에 배치함 

layout_centerlnParent    : 부모 컨테이너의 수평과 수지 방향 중앙에 배치함
```

이미지 출처 : http://jwandroid.tistory.com/m/post/158
    
     
<hr/>
         
 **상대 레이아웃에서 다른 뷰와의 상대적 위치를 이용하는 속성**   
     
       
<img src = "https://user-images.githubusercontent.com/84966961/121316029-dc3e6300-c943-11eb-9f1f-89274e4df799.png" width="50%">

```
layout_above         : 지정한 뷰의 위쪽에 배치함

layout_below         : 지정한 뷰의 아래쪽에 배치함 

layout_toLeftOf      : 지정한 뷰의 왼쪽에 배치함 

layout_toRightOf     : 지정한 뷰의 오른쪽에 배치함

layout_alignTop      : 지정한 뷰의 위쪽과 맞춤 

layout_alignBottom   : 지정한 뷰의 아래쪽과 맞춤 

layout_alignLeft     : 지정한 뷰의 왼쪽과 맞춤 

layout_alignRight    : 지정한 뷰의 오른쪽과 맞춤 

layout_alignBaseLine : 지정한 뷰의 내용물의 아래쪽 기준선(baseline)을 맞춤 
```

이미지 출처 : http://jwandroid.tistory.com/m/post/158
 <hr/>
