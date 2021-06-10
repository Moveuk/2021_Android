 # Layout2
 Key Word:   
<hr/>
   
### 153p : 02-4 테이블 레이아웃

 테이블 레이아웃은 HTML에서 테이블 태그와 비슷하다. 예제를 통해 알아보자.
    
 먼저, SampleTableLayout 프로젝트를 생성한다.
![image](https://user-images.githubusercontent.com/84966961/121468268-7a3e3600-c9f5-11eb-92c4-169a827123cf.png)

 그 후 다음과 같은 계층 구조로 뷰들을 설정한다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121468799-5af3d880-c9f6-11eb-987a-12efca62f9d5.png)

  비대칭으로 들어가 있는 버튼들을 stretch 시켜서 화면을 모두 채워준다.
  
  attribute 패널에서 stretchColumns 를 찾아 0,1,2 로 값을 주는 방법과 다음과 같이 코드를 작성하면 된다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121469373-567bef80-c9f7-11eb-94f9-a54c08d4ac08.png)

 ![image](https://user-images.githubusercontent.com/84966961/121469399-5f6cc100-c9f7-11eb-9ede-d4817077071f.png)
   
  다음은 새로운 TableRow 에 Plain text를 추가하고 버튼을 추가해보면 다음과 같이 된다.
  
  ![image](https://user-images.githubusercontent.com/84966961/121469609-c2f6ee80-c9f7-11eb-8d50-a33f530fa9b4.png)

  그 후에 Plain Text 뷰에 Layout_span 속성값을 찾아 2를 주게되면 테이블 태그처럼 2개의 칸을 잡아서 합쳐주게 된다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121469805-1c5f1d80-c9f8-11eb-903d-1ea3d68b4c91.png)

  블루 프린트 화면을 보게 되면 테이블의 크기와 모양을 쉽게 확인할 수 있으며 span 유무를 보고 싶은 경우 활용하면 된다.
  
  세로 span을 원할 경우 리니어 레이아웃을 잡고 layout_rowSpan 속성을 찾아주면된다.
 
<hr/>
   
### 159p : 02-5 프레임 레이아웃과 뷰의 전환   
 
 프레임 레이아웃은 가장 기본적이고 단순한 레이아웃이다. 프레임 레이아웃에 뷰를 넣으면 그중에서 하나의 뷰만 화면에 표시한다. 프레임 레이아웃은 중첩(Overlay)를 사용하여 뷰를 변경하는 데 사용한다.
    
 다음과 같이 버튼과 프레임 레이아웃을 넣어주고, 버튼의 레이아웃 정렬(layout_gravity)을 중앙으로 해보자.
 
![image](https://user-images.githubusercontent.com/84966961/121470731-9643d680-c9f9-11eb-9c26-58c722e63c06.png)

 다음은 프레임 레이아웃에 이미지를 넣어줄 차례이다.   
 Common 탭의 Imageview를 드래그앤드롭을 통해 Component Tree에 옴겨 넣고 이미지 선택창이 열리면 FrameLayout 프로젝트에 있는 이미지를 넣으면 된다.
 
 ![image](https://user-images.githubusercontent.com/84966961/121471075-110cf180-c9fa-11eb-918a-71d40942aaed.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_weight="1"
        android:text="버튼바꾸기" />

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:srcCompat="@drawable/sky" />
    </FrameLayout>
</LinearLayout>
```   

 항상 디자인 화면으로 디자인을 끝마친 이후 코드로 확인하는 습관을 가지는게 좋다.
 
  다음은 버튼에 onclick 속성에 원하는 (java에서 사용할)메소드 이름을 넣어주어 java 파일 작성을 시작하면 된다.
 
  **'findViewById'** 라는 기능을 이용하여 우리가 원하는 이미지 뷰를 찾아줄 것이다. **'findViewById'**는 HTML에서 getElementById와 같은 기능을 한다.
    
 ```java
  imageView = findViewById(R.id.imageView);
 ```
  위의 코드에서 R.id. 부분에서 R은 왼쪽 폴더 패널에서 res 폴더 전부를 말하며 내부의 id중에 imageView라는 id를 말하는 것이다.
 
 
 **frameLayout onButton1Clicked 기능 코드**    
```java
public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onButton1Clicked(View v) {
        onButton1Clicked();
    }

    private void onButton1Clicked() {
        if (imageIndex == 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex = 1;
        } else if (imageIndex == 1) {
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex = 0;
        }
    }
}
```
 
 기능 구현이 다 끝나면 다음과 같이 버튼 클릭시 이미지가 바뀌게 된다.
    
 ![image](https://user-images.githubusercontent.com/84966961/121473812-284dde00-c9fe-11eb-86f3-633ea2df2bb0.png) -> ![image](https://user-images.githubusercontent.com/84966961/121473832-300d8280-c9fe-11eb-9026-6f4ee53ba73c.png)
   

 <hr/>
   
### 163p : 02-6 스크롤뷰 사용하기   
    
 스크롤뷰는 추가된 뷰의 영역이 한눈에 다 보이지 않을 때 사용된다. 이때 단순히 스크롤뷰를 추가하고 그 안에 뷰를 넣으면 스크롤이 생기게 된다..
    
 스크롤 뷰 안에 이미지를 넣고 스크롤이 나타나는지 보기 위해 SampleScrollView를 생성해주고, 다음과 같은 레이아웃 계층을 만들어 주도록 하자.
 
![image](https://user-images.githubusercontent.com/84966961/121474794-8d560380-c9ff-11eb-8943-eb8d0856c9a8.png)

 그 후 이미지 2장을 drawble 폴더에 준비한 후 imageView에 java 코드를 통해 이미지를 넣어보도록 하겠다.   
    
 **java 스크롤뷰 이미지 삽입 및 이미지 변경 기능 코드**   
 ```java
public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        scrollView.setHorizontalScrollBarEnabled(true); // 스크롤시 가로 스크롤 보이게(true)
        imageView = findViewById(R.id.imageView);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onButton1Clicked(View v) {
        changeImage();
    }

    private void changeImage() {
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }
}
```

 xml 파일에서 이미지를 불러와 삽입할 수 도 있지만 우리는 java를 통해 이미지 삽입을 해보려고 한다.
 ```java
...
      Resources res = getResources();
      bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
      int bitmapWidth = bitmap.getIntrinsicWidth();
      int bitmapHeight = bitmap.getIntrinsicHeight();

      imageView.setImageDrawable(bitmap);
      imageView.getLayoutParams().width = bitmapWidth;
      imageView.getLayoutParams().height = bitmapHeight;
...
```
 위 코드 중 일부분의 코드이다.   
 첫째줄 : res에 application의 resources에 접근할 수 있는 클래스를 인스턴스화시켜준다.
 둘째줄 : BitmapDrawable 타입으로 미리 선언한 bitmap에 이미지01파일을 불러와서 타입 변환을 시켜준다.
 셋째줄 : getIntrinsicWidth()메소드를 사용하여 본래 비트맵파일의 너비값를 기억해둔다.
 넷째줄 : getIntrinsicWidth()메소드를 사용하여 본래 비트맵파일의 높이값를 기억해둔다.
 
 다섯째줄 : 이미지뷰에 비트맵 그림 파일을 세팅해준다.
 여섯째줄 : 너비를 설정해준다.
 일곱째줄 : 높이를 설정해준다.
 
 와 같으며 다양한 클래스들을 import시켜야 한다.
 
  또한, FrameLayout 에서 이미지를 변화시키는 코드를 활용해 스크롤 뷰에서도 이미지 변환을 줄 수 있다.
 
 **이미지가 다시 돌아오게 하는것.**
 ```java
    private void changeImage() {
        if (imageIndex == 0) {
            Resources res = getResources();
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            imageIndex = 1;
        } else if (imageIndex == 1) {
            Resources res = getResources();
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            imageIndex = 0;
        }
```
 
  <hr/>
   
### 168p : 도전!03 두 개의 이미지 뷰에 이미지 번갈아 보여주기.  
 
```java
package org.techtown.mission03;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_master extends AppCompatActivity {
    ScrollView scrollView;
    ScrollView scrollView2;
    ImageView imageView;
    ImageView imageView2;
    Button button;
    Button button2;
    BitmapDrawable bitmap;
    BitmapDrawable bitmap2;
    int imageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById((R.id.imageView));
        imageView2 = findViewById((R.id.imageView2));

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            // OnClickListener()인터페이스임.
            @Override
            public void onClick(View v) {
                moveImageUp();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            // OnClickListener()인터페이스임.
            @Override
            public void onClick(View v) {
                moveImageDown();
            }
        });

    }

    private void moveImageDown() {
        imageView.setImageResource(R.drawable.image01);
        imageView2.setImageResource(R.drawable.image02);

        imageView.invalidate(); //이미지 화면에 그려주는 것.
        imageView2.invalidate(); //이미지 화면에 그려주는 것.
    }

    private void moveImageUp() {
        imageView.setImageResource(R.drawable.image02);
        imageView2.setImageResource(R.drawable.image01);

        imageView.invalidate(); //이미지 화면에 그려주는 것.
        imageView2.invalidate(); //이미지 화면에 그려주는 것.
    }
}
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity" >

    <HorizontalScrollView
        android:id="@+id/scrollView"
        android:layout_above="@id/buttonLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_alignParentTop="true">

        <ScrollView
            android:layout_width="wrap_content"
            android:layout_height="match_parent">

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:scaleType="matrix"/>
        </ScrollView>
    </HorizontalScrollView>

    <LinearLayout
        android:id="@+id/buttonLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layout_centerInParent="true">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:layout_gravity="center_horizontal"
            >

            <Button
                android:id="@+id/button"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:text="    ▲    "
                android:textSize="18sp"
                />

            <Button
                android:id="@+id/button2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:text="    ▼    "
                android:textSize="18sp"
                />
        </LinearLayout>
    </LinearLayout>

    <HorizontalScrollView
        android:id="@+id/scrollView2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/buttonLayout"
        android:layout_alignParentBottom="true"
        >
        <ScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ImageView
                android:id="@+id/imageView2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:scaleType="matrix"
                />

        </ScrollView>
    </HorizontalScrollView>
</RelativeLayout>
```
 
