# 하단 Tab   
Key Word :    

<hr/>
   
## 교재 331p : 하단 탭 보여주기
   
1. 먼저 상단 탭처럼 하단 탭에 들어갈 아이템과 속성을 정의할 수 있도록 새로운 폴더와 `menu_bottom.xml`이라는 파일을 만든다. 그 후 코드를 작성하도록 한다.      
    
![image](https://user-images.githubusercontent.com/84966961/122872009-ca5cb700-d36a-11eb-8ade-e057b62f3609.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/tab1"
        app:showAsAction="ifRoom"
        android:enabled="true"
        android:icon="@android:drawable/ic_dialog_email"
        android:title="이메일" />

    <item
        android:id="@+id/tab2"
        app:showAsAction="ifRoom"
        android:icon="@android:drawable/ic_dialog_info"
        android:title="정보"/>

    <item
        android:id="@+id/tab3"
        app:showAsAction="ifRoom"
        android:enabled="true"
        android:icon="@android:drawable/ic_dialog_map"
        android:title="위치"/>
    
</menu>
```
   
 해당 xml 코드는 하단 탭의 아이콘과 이름을 넣어놓은 것이다. 첫 번째 탭은 이메일과 이메일 아이콘을 두 번째 탭은 정보와 info 아이콘을 세 번째 탭은 위치와 맵 아이콘을 넣도록 한것을 알 수 있다.   
   
<br/>
<hr/>
   
2. 그 다음 `activity_main.xml`파일에 하단 바의 위치를 잡아주고, menu 태그에 1번에서 정의했던 메뉴 탭의 내용을 넣어주면 된다.     

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <FrameLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginEnd="0dp"
        android:layout_marginStart="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:itemBackground="@color/color_primary"
        app:itemIconTint="@drawable/item_color"
        app:itemTextColor="@drawable/item_color"
        app:menu="@menu/menu_bottom"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```
   
 위치를 잡아주기 위해서 constraint 레이아웃에서 `BottomNavigationView` 속성에 `app:layout_constraint`에 관련된 값을 주어 하단에 고정해준 것을 알 수 있다.   
 
 ```
         app:itemBackground="@color/color_primary"
        app:itemIconTint="@drawable/item_color"
        app:itemTextColor="@drawable/item_color"
 ```
   
 또한, 상단의 코드를 적용시키기 위해서 color.xml 파일에 다음과 같이 코드를 추가시켜줘야 하고, 글자 색깔과 아이콘이 선택되어졌을때의 색상을 정하기 위해서 drawable 폴더에 `itam_color.xml` 파일을 만들어 정의해줘야 한다.

**R.values.color.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>

    <color name="color_primary">#FFFFFFFF</color>
</resources>
```
    
![image](https://user-images.githubusercontent.com/84966961/122873283-88cd0b80-d36c-11eb-8095-2d33b36bee30.png)   
	
**R.drawable.item_color.xml**
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:color="#51032d"></item>
    <item android:color="#cfd8dc"></item>
</selector>
```
   
<br/>
<hr/>
   
3. 다음 `MainActivity.java` 파일에 기능 구현을 하도록 한다.   

 하단 탭은 상단 탭과는 달리 bottomNavigation 을 사용한다.

```java
public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();

                        return true;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment3).commit();

                        return true;
                }
                return false;
            }
        });
    }
}
```
   
<br/>
<hr/>
   
4. 새로운 프로젝트이라서 만약 fragment1,2,3이 존재하지 않는다면 다음과 같이 추가하도록 한다. 주소만 바꿔주면 된다.    
![image](https://user-images.githubusercontent.com/84966961/122873668-042ebd00-d36d-11eb-8f2d-38ff0b401a36.png)   
   

**Fragment1.java**
```java
public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment1, container, false);
    }
}
```

**fragment1.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".Fragment1" >

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="첫 번째 하단 탭"
        android:textSize="20sp" />
</LinearLayout>
```

 디바이스에 적용시켜 실행해보면 다음과 같은 화면이 실행될 것이다.   
   
**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/122874268-c54d3700-d36d-11eb-8c5e-634b48b3a125.gif" width="40%">



   
<br/><br/>
<hr/>
   












