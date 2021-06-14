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
   
 3. 스마트폰은 폴더 이름을 통해 layout의 방향에 맞는 폴더 내부의 파일을 가져온다. 또한, 방향이 바뀔 때마다 메모리에 있는 액티비티들을 모두 없앴다가 새롭게 로딩을 시킨다.    
 만든 폴더에 `activity_main.xml`을 복사하여 주고 그림과 같이 `hello world!` 텍스트를 가로와 세로 방향에 맞게 정리한다.   
    
![image](https://user-images.githubusercontent.com/84966961/121848129-8e02d880-cd24-11eb-9096-17388496793d.png)

 4. 스마트폰의 방향이 바뀔 때 메모리에 있던 액티비티가 사라지고 다시 로딩이 된다는 개념을 잘 기억한 상태로 디바이스를 실행시켜 확인해보도록 하자. 다음 그림처럼 방향을 돌릴 때마다 showToast 기능을 통해 수명주기, 생명주기 메서드가 호출되는 것을 알 수 있다.   
   
 <img src="https://user-images.githubusercontent.com/84966961/121849046-d373d580-cd25-11eb-8c21-4e472f41d54f.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/121849058-d66ec600-cd25-11eb-8b9c-1005365cd45b.png" width="65%">

 5. 
 6.  
 7.   
 8. ㅁ
 9. ㅁㅁ
 10. ㅁ
 11. ㅁ
 12. ㅁㅁ
 13. ㅁ
 14. ㅁ
 15. ㅁㅁ
 16. ㅁ
 17. 
