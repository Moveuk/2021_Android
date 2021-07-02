# Lecture17 : 애니메이션과 다양한 위젯 사용하기.
Key Word : 애니메이션, Scale, translate, set, rotate, 슬라이딩 애니메이션(slidingAnimation)

<hr/>

# 교재 442p : 08 애니메이션과 다양한 위젯 사용하기.

## 교재 442p : 08-1 애니메이션 사용하기

 1. 애니메이션을 위한 새로운 프로젝트(SampleTweenAnmation)를 만들고, 리소스에 anim 폴더를 만든후 `scale.xml!`이라는 resourse 파일을 새로 만든다.

![image](https://user-images.githubusercontent.com/84966961/124223307-7312cf80-db3e-11eb-953e-1d71a83b22f1.png)
![image](https://user-images.githubusercontent.com/84966961/124223317-78701a00-db3e-11eb-91e4-db6b19121b2e.png)
![image](https://user-images.githubusercontent.com/84966961/124223369-8b82ea00-db3e-11eb-98a2-61dc739984ea.png)

 이 후 에니메이션 속성을 정의한다.   

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <scale
        android:duration="2500"
        android:pivotX="50%"
        android:pivotY="50%"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:toXScale="2.0"
        android:toYScale="2.0"/>
</set>
```

`<scale>`태그는 대상을 확대하거나 축소할 때 사용되는데, 크기를 변경하려는 축의 정보는 X축과 Y축에 대해 각각 pivotX,pivotY로 지정한다. fromXscale,fromYScale는 시작할 때의 확대/축소 비율이며 toXScale,toYScale는 끝날 때의 확대/축소 비율을 말한다. 여기서는 1.0배의 크기로 시작하여 2.0배의 크기로 끝나므로 원래 크기에서 2배크기로 확대되는 애니메이션이 수행된다.   


 | 속성 | 설명 | 
 |---|---|
 | startOffset | 애니메이션의 시작시간 | 
 | duration | 애니메이션의 지속시간 | 
 | pivotX,pivotY | X,Y축에 대한 크기 변경 | 
 | fromXscale,fromYScale | 시작할 때의 확대/축소 비율 |
 | toXScale,toYScale | 끝날 때의 확대/축소 비율 | 


<br><br>

---

 2. 메인액티비티에 기능 코드를 구성한다.

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 리소스에 정의한 애니메이션 액션 로딩
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);

                // 뷰 애니메이션 시작
                v.startAnimation(anim);

                // 모든 뷰에는 애니메이션을 실행시킬 수 있는 메소드가 있음. 그래서 버튼에서 받아오는 매개변수 v에서 메소드를 호출함.
            }
        });

    }
}
```


3. 이번에는 새로운 버튼에 적용할 `scale2.xml` 파일을 만들어 커졌다가 서서히 줄어드는 애니메이션을 만들어보자.   

![image](https://user-images.githubusercontent.com/84966961/124224965-9ab76700-db41-11eb-8b45-37fe9ab3e985.png)   
  
```
<set xmlns:android="http://schemas.android.com/apk/res/android">
<scale
    android:duration="2500"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fromXScale="1.0"
    android:fromYScale="1.0"
    android:toXScale="2.0"
    android:toYScale="2.0"/>

<scale
    android:startOffset="2500"
    android:duration="2500"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fromXScale="1.0"
    android:fromYScale="1.0"
    android:toXScale="0.5"
    android:toYScale="0.5"/>


</set>
```

 이후 메인액티비티에 버튼 기능을 넣어준다.   

```java
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 리소스에 정의한 애니메이션 액션 로딩
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale2);

                // 뷰 애니메이션 시작
                v.startAnimation(anim);
            }
        });
```


<br><br>
<hr>

### 교재 449p : 트윈 애니메이션으로 위치 이동 액션 효과 주기 : translate

참고 자료 : https://dwfox.tistory.com/26

![image](https://user-images.githubusercontent.com/84966961/124226055-6b095e80-db43-11eb-9925-a78c0bfc1690.png)

 Display가 액티비티 화면 중 핸드폰의 화면으로 보이는 부분을 말한다. Display의 좌상단 끝부분을 `(0,0)`좌표로 지정하여 액티비티의 좌상단 끝부분을 `x=-100% y=0`의 좌표로 두고 Display의 우상단 끝부분을  `x=100% y=0`의 좌표로 정의한다.





<br><br>
<hr>

### 교재 449p : 트윈 애니메이션으로 위치 회전 액션 효과 주기 : rotate



<br><br>
<hr>

### 교재 450p : 트윈 애니메이션으로 스케일 액션 효과 주기 : scale, fromXScale, toXScale

 스케일은 대상을 크게하거나 작게 할 수 있는 액션으로 확대/축소의 정도는 대상이 갖는 원래 크기에 대한 비율로 결정된다.


<br><br>
<hr>

### 교재 450p : 트윈 애니메이션으로 투명도 액션 효과 주기 : AlPha, fromAlpha, toAlpha

 투명도를 결정하는 알파 값도 뷰나 그리기 객체의 투명도를 점차적으로 바꿀 수 있는 애니메이션 액션으로 정의할 수 있다.



<br><br>
<hr>

### 교재 451p : 트윈 애니메이션으로 속도 조절하기

 애니메이션 효과가 지속되는 동안 빠르거나 느리게 효과가 진행되도록 만드는 방법은 인플레이터를 사용하면 된다. 인플레이터는 R.anim에 미리 정의된 정보를 사용해서 설정할 수 있는데 다음과 같은 대표적인 인플레이터를 사용할 수 있다.

| 속성 | 설명 |
|---|---|
| accelerate_interpolator | 효과를 점점 빠르게 나타나도록 만듬 |
| decelerate_interpolator | 효과를 점점 느르게 나타나도록 만듬 |
| accelerate_decelerate_interpolator | 효과를 점점 빠르다가 느리게 나타나도록 만듬 |
| anticipate_interpolator | 효과를 시작 위치에서 조금 뒤로 당겼다가 시작하도록 만듬 |
| overshoot_interpolator | 효과를 종료 위치에서 조금 지나쳤다가 종료되도록 만듬 |
| anticipate_overshoot_interpolator | 효과를 시작 위치에서 조금 뒤로 당겼다가 시작한 후 종료 위치에서 조금 지나쳤다가 종료 |
| bounce_interpolator | 종료 위치에서 튀도록 만듬 |






<br><br>
<hr>

## 교재 452p : 페이지 슬라이딩 사용하기
   
앞서 말한 기능들을 직접 만들어 볼 수도 있다. 그중에 페이지 슬라이딩은 버튼을 눌렀을 때 보이지 않던 뷰가 슬라이딩 방식으로 나타나는 기능이다. **여러 뷰를 중첩해두었다가 하나씩 전환**(프레임 레이아웃)하면서 보여주는 방식에 **애니메이션**(슬라이딩)을 결합한 것이다. 흔한 예시로 일전에 해보았던 햄버거 메뉴이다. 햄버거 메뉴, 바로가기 메뉴는 버튼 클릭시 슬라이딩하면서 메뉴가 나오게 된다.

![image](https://user-images.githubusercontent.com/84966961/124229091-0a305500-db48-11eb-854e-0bc062e1a7ab.png)

 전형적인 페이지 슬라이드 구현 방식을 보여주는 교재 이미지이다. 만약 두 개의 뷰를 중첩시켰다면 왼족의 뷰는 보이거나 보이지 않는 상태로 만들 수 있다. 오른쪽에서 왼쪽으로 보이는 애니메이션을 만들 때는 애니메이션 액션 정보를 XML에 저장한 후 로딩하여 위쪽의 뷰에 적용한다. 왼쪽에서 오른쪽으로 닫히는 애니메이션을 만들 때는 마찬가지 방법으로 적용하되 애니메이션 액션 정보가 반대 방향으로 정의된다. 이 두 가지 모두 뷰의 이동과 관련되므로 애니메이션을 위한 XML에는 `<translate>` 태그를 사용한다.


 새로운 프로젝트 `SamplePageSliding` 을 만들어 xml 파일 디자인을 한다. 화면이 전환되는 용도로 레이아웃을 사용할때 프레임 레이아웃을 사용하면 좋기 때문에 프레임 레이아웃을 구성한다.

**activity_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    
    <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff5555ff">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Base Area"
            android:textColor="#ffffffff"/>
    </LinearLayout>
    
    <LinearLayout
        android:id="@+id/page"
        android:orientation="vertical"
        android:layout_width="200dp"
        android:layout_height="match_parent"
        android:layout_gravity="right"
        android:background="#ffffff66"
        android:visibility="gone">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Area #1"
            android:textColor="#ff000000"/>
    </LinearLayout>
    
    <LinearLayout
        android:orientation="vertical"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="right|center_vertical"
        android:background="#00000000">
        
        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Open"/>
    </LinearLayout>
    
</FrameLayout>
```

**결과 화면**     
<img src="https://user-images.githubusercontent.com/84966961/124229442-92aef580-db48-11eb-8229-dbca3a094905.png" width="40%">   
   

두번째 레이아웃의 `android:visibility="gone"` 이라는 속성이 프레임 레이아웃 상에서 안보이게끔 하는 속성이다. 만약 `android:visibility="gone"`을 없애면 다음과 같이 보이게 된다.   
    
<img src="https://user-images.githubusercontent.com/84966961/124230020-72336b00-db49-11eb-8f6e-79cf54dc3c5d.png" width="40%">   

마지막 레이아웃의 알파값이 `android:background="#00000000"`에서 00 이므로 배경이 안보이게 설정해주었다. 하지만 이를 `ff`로 바꿔준다면 다음과 같이 배경 테두리가 보이게 된다.   
    
<img src="https://user-images.githubusercontent.com/84966961/124230275-b9216080-db49-11eb-8cfb-98696b12bdc6.png" width="40%">   
   
<br><br>

---

#### 좌표값

<img src="https://user-images.githubusercontent.com/84966961/124230020-72336b00-db49-11eb-8f6e-79cf54dc3c5d.png" width="40%">     
  
 현재 슬라이딩되는 노란 부분의 레이아웃에서 현재 모습이 좌표값이 (0,0)인 모습인 것이다. 이것을 오른쪽(양의 방향)으로 100% 이동시켜 사라지도록 만들 것이다.

<br><br>

---

### 교재 457p : translate 애니메이션 xml 파일 만들기
   
 X좌표의 시작 지점인 `fromXDelta`의 값을 100%p으로해서 화면에서 우상단 끝에서 애니메이션이 끝나면 `toXDelta`가 0%p인 지점으로 이동한다. `duraion`은 0.5로 주었다.


**translate_left.xml 왼쪽으로 꺼낼 때**
```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:Interpolator="@android:anim/accelerate_decelerate_interpolator">
    <translate
        android:fromXDelta="100%p"
        android:toXDelta="0%p"
        android:duration="500"
        android:repeatCount="0"
        android:fillAfter="true"/>
        
</set>
```

**translate_right.xml 오른쪽으로 넣을 때**
```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:Interpolator="@android:anim/accelerate_decelerate_interpolator">
    <translate
        android:fromXDelta="0%p"
        android:toXDelta="100%p"
        android:duration="500"
        android:repeatCount="0"
        android:fillAfter="true"/>
        
</set>
```

<br><br>

---

### 교재 455p : 애니메이션 실행 버튼 설정.

```java
public class MainActivity extends AppCompatActivity {

    // 버튼이 현재 열려있는 건지 아닌건지를 확인하기 위한 전역 변수(플래그)를 선언함.
    // false 닫혀있음, true 열려있음. 토글 버튼. 토글 상태.
    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        button = findViewById(R.id.button);

        translateLeftAnim = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // isPageOpen 플래그가 true면 닫히는 애니메이션
                if(isPageOpen){
                    page.startAnimation(translateRightAnim);
                } else {
                // isPageOpen 플래그가 false면 열리면서 보이는 애니메이션
                // View는 page이다. `gone`상태인 뷰를 `VISIBLE`하게 되어 보이게끔 해주고 왼쪽으로 열리게 해준다.
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeftAnim);
                }
            }
        });
    }
}
```

**결과 화면**

<img src="https://user-images.githubusercontent.com/84966961/124237032-14575100-db52-11eb-8afe-69595f005271.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/124237034-14efe780-db52-11eb-9da7-635409d6e9a8.png" width="40%">



<br><br>

---


### 애니메이션을 인식하는 리스너 : setAnimationListener

 버튼의 `onClick`을 감지하는 리스너는 `setOnClickListener`이며 우리는 매개변수로 `new View.OnClickListener()` 를 익명 객체로 만들어 사용했었다. 하지만 우리가 페이지 슬라이딩 애니메이션 만들 때는 넣고 뺄때 같은 리스너를 두 번 사용하여 코드 중복이 생기므로 따로 Listener 인터페이스를 구현하고 클래스에 상속하여 사용하는 것이 더 효율적인 코드의 작성이 될 것이다.    
    
 `setAnimationListener(new Animation.AnimationListener() ... ` 특히나 애니메이션 리스너는 버튼과 다르게 오버라이드 할 부분이 `Start`, `End`, `Repeat` 이렇게 세 부분이나 해야 하기 때문에 코드 중복을 줄이기 위해 따로 클래스를 작성한다.   

 ```java
        translateRightAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
```

 따라서 긴 코드의 중복을 없애기 위해서 중첩 클래스로 Listener를 만들어준다.
 
 ```java
     private class SlidingPageAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            } else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
```

코드 작성 후 확인해보면 클릭할 때마다 버튼의 이름이 잘 바뀌는 것을 확인할 수 있다.
  
**결과 화면**

<img src="https://user-images.githubusercontent.com/84966961/124242921-3bb11c80-db58-11eb-8401-51312b44e379.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/124242929-3ce24980-db58-11eb-92cd-bdbbd72bfdd5.png" width="40%">



<br><br>

---




