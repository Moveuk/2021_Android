# 레이아웃 정의하고 카드뷰 넣기
Key Word : 카드뷰(CardView), 리사이클러뷰(RecyclerView)

<hr/>
  
### 교재 406p : 07-3 레이아웃 정의하고 카드뷰 넣기

1. `SampleLayout` 프로젝트 생성 후 레이아웃 설정   
    
![image](https://user-images.githubusercontent.com/84966961/123748102-b7a82c00-d8ee-11eb-9793-abd727c6721e.png)
    
    
<br/><br/>
<hr/>

2. Layout1.java 코드 작성    
   
 레이어 리니아웃을 상속받는 코드를 작성하여 기존의 코드를 활용하여 만들음.   
   
**Layout1.java**
```java
 package org.techtown.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView textView;
    TextView textView2;

    public Layout1(Context context) {
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1, this, true);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }

    public void setName(String name) {
        textView.setText(name);
    }

    public void setMobile(String mobile) {
        textView2.setText(mobile);
    }
}
```
    
    
<br/><br/>
<hr/>


3. MainActivity.java에서 layout1 view의 이미지를 바꾸는 기능 구현하기.

**MainActivity.java**
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Layout1 layout1 = findViewById(R.id.layout1);

        layout1.setImage(R.drawable.ic_launcher_foreground);
        layout1.setName("김민수");
        layout1.setMobile("010-0000-0000");

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setImage(R.drawable.profile01);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setImage(R.drawable.profile02);
            }
        });
    }
}
```

    
<br/><br/>
<hr/>

4. 카드뷰 코드 작성

 카드뷰는 외부 라이브러리로 xml 파일에서 태그로 작성이 가능하다. 카드뷰는 평면적인 화면에서 특정 뷰를 카드처럼 솟아(elevation을 주어서) 보이게 하는 용도로 쓰이게된다. 카드뷰 모양으로 바뀌게 되면 더 깔끔하게 보이는 효과가 있다. 레이아웃을 상속하는 뷰를 직접 만들고 그 모양도 카드뷰 모양으로 보일 수 있도록 했다.

**layout1.xml 카드뷰를 넣은 xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardBackgroundColor="#FFFFFFFF"
        app:cardCornerRadius="10dp"
        app:cardElevation="5dp"
        app:cardUseCompatPadding="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:padding="5dp"
                app:srcCompat="@mipmap/ic_launcher" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="5dp"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="match_parent"
                    android:layout_height="30sp"
                    android:text="이름" />

                <TextView
                    android:id="@+id/textView2"
                    android:layout_width="match_parent"
                    android:layout_height="25sp"
                    android:text="전화번호"
                    android:textColor="#0000FF" />
            </LinearLayout>
        </LinearLayout>
    </androidx.cardview.widget.CardView>
</LinearLayout>
```

**실행 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/123753906-4b7cf680-d8f5-11eb-9c69-e200e1a91ad0.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/123753911-4cae2380-d8f5-11eb-914d-b9ab0f4353a9.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/123753919-4d46ba00-d8f5-11eb-8e5d-7b9f15b323f8.png" width="30%">
   
   

**참고 : **외부에서 끌어다 쓰는 라이브러리 등은 xml에서 주소 그대로 태그를 사용한다.

    
<br/><br/>
<hr/>

### 교재 416p : 07-4 리싸이클러뷰 만들기
 
 모바일 단말에서 가장 많이 사용되는 UI 모양 중의 하나가 바로 리스트이다. 리스트는 일반적으로 여러 개의 아이템 중 하나를 선택할 수 있는 세로모양으로 된 화면 컨트롤(Control)을 말하는데 이런 UI모양은 다른 언어에서도 많이 사용된다. 특히 아이폰이나 안드로이드처럼 손가락으로 터치하는 방식을 사용하는 단말에서는 리스트가 쉽고 직관적이기 때문에 여러 개의 아이템 중에 선택하는 기능을 넣을 때 더 자주 사용된다.   
   
**선택 위젯(Selection Widget)**
 안드로이드 에서는 여러 개의 아이템 중에 하나를 선택할 수 있는 리스트 모양의 위젯을 특별히 `선택 위젯(Selection widget)`이라고 부른다. 선택 기능을 가진 위젯을 특별히 구별하는 이유는 사용되는 방식이 다른 위젯과 약간 다르기 때문이다. 일반 위젯과는 달리 선택 위젯은 어댑터에서 따로 관리하며 `어댑터(Adaptor) 패턴`을 사용한다.















