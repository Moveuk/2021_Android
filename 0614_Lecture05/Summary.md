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
   
<img src="![image](https://user-images.githubusercontent.com/84966961/121845706-ce605780-cd20-11eb-89be-a00488e6cb24.png)" width="40%">
   
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

5. ㅁ
6. ㅁ
7. ㅁ
8. ㅁ
9. 
