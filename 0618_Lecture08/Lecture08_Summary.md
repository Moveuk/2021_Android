# Lecture07 : 프래그먼트 화면 전환, 상하단 탭.
Key Word : 프래그먼트(Fragment), 상하단 탭    
   
<hr/>
   
## 교재 305p : 05-2 프래그먼트로 화면 만들기   
   
 다음과 같은 구조로 예제를 만들어 프래그먼트의 화면 전환을 배워볼 것이다.   
    
> MainActivity.java
> > ListFragment
> > > 버튼1   
> > > 버튼2   
> > > 버튼3   

> > ViewerFragment
> > > 이미지뷰   
   
 버튼 1,2,3을 클릭하게 되면 이미지뷰의 이미지가 바뀌게 된다.   
     
 1. 이미지를 바뀌게 하기위해서 ViewerFragment에 이미지뷰 객체를 만들어 이미지 리소스를 바뀌게 하는 기능(메소드)를 만든다.
 2. ListFragment 에서 액티비티에 접근하기 위해서 
   
   
   
**MainActivity.java**
   
```java
public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelection{
    ListFragment listFragment;          // 리스트 프래그먼트 선언
    ViewerFragment viewerFragment;      // 뷰어 프래그먼트 선언

    int[] images = {R.drawable.dream01,R.drawable.dream02,R.drawable.dream03};  //드림01 이 정수 타입임? 이미지?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.ListFragment);
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.ViewerFragment);
    }


    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}
```
   
**ListFragment.java**
   

```java
public class ListFragment extends Fragment {

    public static interface ImageSelection{
        public  void onImageSelected(int position);
    }

    public ImageSelection callback;

    @Override
    public void onAttach(Context context) {     // 프래그먼트가 액티비티에 붙는 순간에 대해서 오버라이드함.
        super.onAttach(context);

        if (context instanceof ImageSelection) {
            // context에 메인액티비티 넣을거고 이미지 섹션의 자식으로 메인엑티비티를 넣을거임. 그러면 true가 나옴
            callback = (ImageSelection)context; // callback은 메인액티비티임.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        Button button = rootView.findViewById(R.id.button);
        Button button2 = rootView.findViewById(R.id.button2);
        Button button3 = rootView.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(2);
            }
        });


        return rootView;

    }
}
```
   
**ViewerFragment.java**
   
```java
public class ViewerFragment extends Fragment {
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);  // 이미지 바꿔주는 메소드
    }
}
```

 viewerFragment 에는 이미지 뷰만 존재

**실행 결과**   
   
<img src="https://user-images.githubusercontent.com/84966961/122517207-b6534580-d04a-11eb-96e7-c92b0f35576c.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/122517241-bf441700-d04a-11eb-9e7b-efedf62e2dc2.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/122517259-c2d79e00-d04a-11eb-8ce3-1b5be110f261.png" width="30%">

   
<br/><br/>
<hr/>
   
## 교재 323p : 05-4 상단 탭과 하단 탭 만들기   
   

 다음 build.gradle 에서 다음 내용이 없다면 상하단바를 외부 라이브러리를 이용해 만들 수 없다.   
    
![image](https://user-images.githubusercontent.com/84966961/122520376-7db56b00-d04e-11eb-9189-a4bc2acb7a78.png)

**activity_main.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@color/colorPrimaryDark"
                android:elevation="1dp"
                android:theme="@style/ThemeOverlay.AppCompat.Dark">

                <TextView
                    android:id="@+id/titleText"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="타이틀"
                    android:textAppearance="@style/Base.TextAppearance.Widget.AppCompat.Toolbar.Title"/>

            </androidx.appcompat.widget.Toolbar>

<!--    요부분에 탭메뉴를 배치할 것임.       -->
            <com.google.android.material.tabs.TabLayout 
                android:id="@+id/tabs"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@android:color/background_light"
                android:elevation="1dp"
                app:tabGravity="fill"
                app:tabMode="fixed"
                app:tabSelectedTextColor="@color/colorAccent"
                app:tabTextColor="@color/colorPrimary"/>
        </com.google.android.material.appbar.AppBarLayout>

        <FrameLayout
            android:id="@+id/container"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

        </FrameLayout>

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

</RelativeLayout>
```
   
**MainActivity.java**
   
```java
package org.techtown.tab;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);   // 탭메뉴를 툴바에 만들겠다.
        setSupportActionBar(toolbar);           // 툴바 주소에 액션바 만들겠다.

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);    //디스플레이 타이틀 안보이게

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
        // 탭 프래그 먼트를 바꾸어라.

        // 코드 상에서 xml 레이아웃을 추가하는 방법.
        TabLayout tabs = findViewById(R.id.tabs);           // xml에 tab 레이아웃 찾아라
        tabs.addTab(tabs.newTab().setText("통화기록"));      // 탭 레이아웃에 하나씩 추가해라
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭을 누르면 아래처럼 반응해라
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);
                Fragment selected = null;   // 띄울 프래그먼트를 넣을 변수값
                if (position == 0) {        // 종류마다 입력
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else  if (position == 2) {
                    selected = fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
```

**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/122530521-82cbe780-d059-11eb-9c8f-1b2685d37455.png)


<br/><br/>
<hr/>

**로그 코드 확인**    
   
 다음 코드에서 Log.d 라는 코드 때문에 디바이스에서 탭 클릭시 logcat에 찍히는 것을 볼 수 있다.
   
   
```
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭을 누르면 아래처럼 반응해라
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);
                Fragment selected = null;   // 띄울 프래그먼트를 넣을 변수값
                if (position == 0) {        // 종류마다 입력
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else  if (position == 2) {
                    selected = fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }
```

**Logcat 화면**  
![image](https://user-images.githubusercontent.com/84966961/122531080-1c939480-d05a-11eb-990e-e03090c95b6c.png)






