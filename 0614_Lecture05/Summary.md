# 이벤트 처리하기!
Key Word : 
  <hr/>
    
## 196p : 03-3 이벤트 처리 이해하기
   
 **이전 수업시간 내용**   
   
1. 이벤트 처리 방식   
   
 지난 수업에서 이벤트 핸들러를 이용한 다양한 이벤트들을 이미 접하였다. 하지만 다른 디바이스와는 다른 스마트폰에서만의 이벤트 특징은 터치 이벤트가 가능하다는 점이다. 그 외에도 키 이벤트, 제스처 이벤트, 포커스, 화면 방향 변경 같은 이벤트들이 있다.   
   
 또한, 이벤트에 대한 각각의 View.OnTouchListener, View.OnkeyListner 등 다양한 리스너가 존재하며 일어난 이벤트들을 감지한다.    
    
 감지한 이벤트들을 198p의 테이블에 존재하는 다양한 메소드들을 오버라이딩(Override)하여 기능을 추가하는 것이다.   
   
2. SampleEvent 를 작성하여 Event 기능의 예제를 만들어보자.      
 먼저 디자인을 완성시켰다.   
    
3. 첫번째 화면에 대한 기능 구현을 다음과 같이하였다.   
   
<br/><br/><br/>
<hr/>
   
### 교재 202p : 제스처 이벤트 처리하기
   
4. 제스처 이벤트는 터치 이벤트 중에서 스크롤 등을 구별한 후 알려주는 이벤트 이다. 제스처 이벤트를 처리해주는 클래스는 Gesture Detector이며, 이 객체를 만들고 터치 이벤트를 전달하면 GestureDetector 객체에서 각 상황에 맞는 메서드를 호출한다. 화면에 추가했던 두 번째 뷰(황색)를 터치했을 때 제스처 이벤트로 처리하도록 onCreate() 메서드 안에 다음 코드를 추가한다.   
   
```java
public class MainActivity extends AppCompatActivity {

    TextView textView;

    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("손가락 뗌 : " + curX + ", " + curY);
                }

                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                println("onShowPress() 호출됨.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                println("onSingleTapUp() 호출됨.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll() 호출됨 : " + v + ", " + v1);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling() 호출됨 : " + v + ", " + v1);
                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {   // 뷰를 터치했을 떄 발생하는 터치 이벤트를 제스처 디텍터로 전달.
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    public void println(String data) {
        textView.append(data + "\n");
    }

}
```
   
**실행 화면**
   
<img src="https://user-images.githubusercontent.com/84966961/121845706-ce605780-cd20-11eb-89be-a00488e6cb24.png" width="40%">
   
 **GestureDetector의 메소드들** : 교재 198p      
    
|메서드|이벤트 유형|
|---|---|
| 1. onDown | 화면이 눌렸을 경우 |
| 2. onShowPress | 화면이 눌렸다 떼어지는 경우 |
| 3. onSingleTapUp | 화면이 한 손가락으로 눌렸다 떼어지는 경우 |
| 4. onSingleTapComfirmed | 화면이 한 손가락으로 눌려지는 경우 |
| 5. onDoubleTap | 화면이 두 손가락으로 눌려지는 경우 |
| 6. onDoubleTapEvent | 화면이 두손가락으로 눌려진 상태에서 떼거나 이동하는 등 세부적인 액션을 취하는 경우 |
| 7. onScroll | 화면이 눌린채 일저안 속도와 방향으로 움직였다 떼는 경우 |
| 8. onFling | 화면이 눌린 채 가속도를 붙여 손가락을 움직였다 떼는 경우 |
| 9. onLongPress | 화면을 손가락으로 오래 누르는 경우 |





<br/><br/><br/>
<hr/>
   
### 교재 207p : 단말 방향을 전환했을 때 이벤트 처리하기   
   
 1. 스마트폰의 세로와 가로 방향에 따른 이벤트 처리를 예제로 해보기 위해서 SampleOrientation 프로젝트를 만들었다.   
 2. 이 후 res 폴더에 오른쪽 클릭을 하여 새로운 폴더 `layout-land`를 만들어준다.   
   
![image](https://user-images.githubusercontent.com/84966961/121847737-03ba7480-cd24-11eb-8368-2ef8f1fd7543.png)   
![image](https://user-images.githubusercontent.com/84966961/121847781-1339bd80-cd24-11eb-9cbb-afcb15e865e1.png)    

<br/><br/>
<hr/>
   
 3. 스마트폰은 폴더 이름을 통해 layout의 방향에 맞는 폴더 내부의 파일을 가져온다. 또한, 방향이 바뀔 때마다 메모리에 있는 액티비티들을 모두 없앴다가 새롭게 로딩을 시킨다.    
 만든 폴더에 `activity_main.xml`을 복사하여 주고 그림과 같이 `hello world!` 텍스트를 가로와 세로 방향에 맞게 정리한다.   
    
![image](https://user-images.githubusercontent.com/84966961/121848129-8e02d880-cd24-11eb-9096-17388496793d.png)

<br/><br/>
<hr/>
   
 4. 스마트폰의 방향이 바뀔 때 메모리에 있던 액티비티가 사라지고 다시 로딩이 된다는 개념을 잘 기억한 상태로 디바이스를 실행시켜 확인해보도록 하자. 다음 그림처럼 방향을 돌릴 때마다 메모리가 리셋이 되고 수명주기, 생명주기 메서드들이 자동으로 호출되며 showToast 기능이 작동되는 것을 알 수 있다.   
   
 <img src="https://user-images.githubusercontent.com/84966961/121849046-d373d580-cd25-11eb-8c21-4e472f41d54f.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/121849058-d66ec600-cd25-11eb-8b9c-1005365cd45b.png" width="65%">

 **%토스트 메시지**   
 
 토스트 메세지란(Toast Message)?   
    
토스트 메세지란 짧은 메세지를 유저에게 노출 시킨 후 일정 시간이 지나면 사라지는 팝업이다. 우리가 앱에서 흔히 볼수 있는 뒤로가기를 눌렀을때 "뒤로가기를 한 번 더 누르면 앱이 종료됩니다."라고 뜨는 팝업이 토스트 메세지 이다.

```java
    public void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
```
   
 매개변수로 첫번째 인자는 컨텍스트이며 두번째 인자인 data는 우리가 띄우고 싶은 문구를 의미하고, 세번째 인자인 Toast.LENGTH_LONG은 얼마만큼의 시간동안 보여줄지 즉 노출 시간(short_4초(4000ms)와 long_7초(7000ms) 가량)을 뜻한다. 마지막 메소드 .show()를 통하여 세팅된 토스트 메세지를 출력하게 된다.    
     
 전체 메서드를 관장하는 것 : 컨텍스트 ( 메인에서는 전체가 컨텍스트이다.)
 
<br/><br/>
<hr/>
   
 ### 교재 217p : 03-4 토스트, 스낵바 그리고 대화상자 사용하기.   
 
 토스트 메세지 이외에도 스낵바나 대화상자라는 메시지 상자들이 있다.    
 
  1. 토스트 만들어보기
    
 SampleToast 프로젝트를 만든후 다음의(교재 218p) 구성을 만들어 보자.
 
 > LinearLayout
 >> EditText
 >> EditText
 >> Button   
    
**화면 구성 코드**
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <EditText
            android:id="@+id/editTextTextPersonName"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="X 위치"
            android:inputType="numberSigned"
            android:text="Name"
            android:textSize="20sp" />

        <EditText
            android:id="@+id/editTextTextPersonName2"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="Y 위치"
            android:inputType="numberSigned"
            android:text="Name"
            android:textSize="20sp" />

        <Button
            android:id="@+id/button"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="띄우기"
            android:textSize="20sp"
            android:onClick="onButton1Clicked"/>

    </LinearLayout>
</LinearLayout>
```
   
**위치값 지정에 따른 Toast 메세지 표출 기능 코드**

```java
public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);		// editText의 주소값을 불러와 저장해둠.
        editText2 = findViewById(R.id.editText2);	// editText2의 주소값을 불러와 저장해둠.
    }

    public void onButton1Clicked(View v) {
        try {
            Toast toastView = Toast.makeText(this, "위치가 바뀐 토스트 메시지 입니다.", Toast.LENGTH_LONG);
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());

                toastView.setGravity(Gravity.TOP|Gravity.TOP,xOffset,yOffset);
                toastView.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
```

 EditText View에 각각 x값 y값을 넣어보면 다음과 같이 변한다.   
    
<img src="https://user-images.githubusercontent.com/84966961/121853692-310b2080-cd2c-11eb-82b4-3990fdd33377.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/121853377-d07be380-cd2b-11eb-8565-918ec33d517c.png" width="40%">
    
 기존 코드를 다음과 같이 위치값을 알기 쉽도록 바꾸어 보았다.   
    
```java
    public void onButton1Clicked(View v) {
        try {
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());
            Toast toastView = Toast.makeText(this, "위치값 = X : "+xOffset+",Y : "+yOffset, Toast.LENGTH_LONG);

                toastView.setGravity(Gravity.TOP|Gravity.TOP,xOffset,yOffset);
                toastView.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
```
   
**화면**

<img src="https://user-images.githubusercontent.com/84966961/121854036-995a0200-cd2c-11eb-965e-becdb51dfd3e.png" width="25%">
<img src="https://user-images.githubusercontent.com/84966961/121854510-39179000-cd2d-11eb-8ab9-b35d28ba7f9e.png" width="25%">
<img src="https://user-images.githubusercontent.com/84966961/121854527-3d43ad80-cd2d-11eb-9a51-869aa5ce082e.png" width="25%">



<br/><br/>
<hr/>
   
 ### 스낵바(Snackbar) 보여주기.   
    
  2. 스낵바 만들어보기
   
 스낵바를 띄워보기 위해서 버튼만 하나 추가 후 기능을 위하여 onClick 속성에 `onButton3Clicked`를 넣어주었다. 이 후 자바코드에 다음과 같은 한줄을 넣게 되면 기능이 작동한다.    
   
```java
    public void onButton3Clicked(View v) {
        Snackbar.make(v,"스낵바입니다.", Snackbar.LENGTH_LONG).show();
    }
```
   
**실행 화면**   
![image](https://user-images.githubusercontent.com/84966961/121856225-39b12600-cd2f-11eb-838b-319668b375fe.png)    
   

<br/><br/>
<hr/>




   <br/><br/>
<hr/>



   <br/><br/>
<hr/>



   <br/><br/>
<hr/>



   <br/><br/>
<hr/>
   
 5. 진행사항을 저장시켜주는 onSaveInstanceState 콜백 메서드
   
 단말의 방향이 바뀌는 기능을 해보았다. 하지만 문제점은 예를들어 사용자가 결제를 하던 도중 방향이 돌아가게 되면 기존까지 모든 액티비티들이 리셋되고 다시 처음부터 실행되어 다시 결제를 시도해야하는 불편함이 생길 수 있다는 점이다. 이런 문제를 해결하기 위하여 액티비티 안에 선언해 두었던 변수 값이 사라지므로 변수의 값을 저장했다가 다시 복원하는 방법이 있어야 한다. 이런 문제를 해결할 수 있도록 onSaveInstanceState 콜백 메서드가 제공된다. 이 메서드는 액티비티가 종료되기 전의 상태를 저장하고, 이 때 저장된 상태를 onCreate() 메서드가 호출 될 때 다시 복원시킬 수 있는 기능을 한다.   
 





 
<br/><br/>
<hr/>
   

