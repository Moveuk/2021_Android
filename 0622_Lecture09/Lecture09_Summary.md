# 하단 Tab & 뷰페이저   
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
   
## 교재 335p : 05-5 뷰페이저 만들기   
    
 뷰페이저는 손가락으로 좌우 스크롤하여 넘겨볼 수 있는 기능을 제공한다. 만약 화면 전체를 뷰페이저로 채운다면 좌우 스크롤을 통해 화면을 넘겨볼 수 있게 된다. 화면 일부분만 차지하고 있어도 그 부분에서만 좌우 스크롤이 동작할 것이다. 뷰페이저는 그 안에 프래그먼트를 넣을 수 있고 좌우 스크롤로 프래그먼트를 전환 하게 되는 기능이다. 뷰 페이저는 내부에서 어댑터라는 것과 상호작용하게 되어 있는데 이것은 뷰페이저가 여러 개의 아이템 중에 하나를 보여주는 방식으로 동작하기 때문이다. 어댑터에 대해서는 나중에 리싸이클러뷰를 다룰 때 설명할 것이다. 일단 여기에서는 어댑터를 사용한다고 이해하고 그 안에 코드가 어떻게 들어가는지 유심히 살펴보도록 하자.

![image](https://user-images.githubusercontent.com/84966961/122874455-f9c0f300-d36d-11eb-8b92-7717866ef849.png)
 
<br/><br/>
<hr/>
   
1. fragment1,2,3은 기존에 사용한 파일들을 복사한다.   
   
![image](https://user-images.githubusercontent.com/84966961/122876554-9a181700-d370-11eb-9460-fd3fdedd22b7.png)   
    
<br/><br/>
<hr/>
   
2. 페이저를 관리하는 어답터 클래스를 만든다.(굳이 내부 클래스로 만드는 이유는 예제이기 때문에 외부에서 쓸 일이 없기 때문이다.)   
    
**MainActivity.java**
```java
public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
	
    class  MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public  MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
```
   
 `MyPagerAdapter` 클래스는 뷰페이저를 관리하는 어답터로 내부에 ArrayList를 만들어 Fragment라는 값들을 받아 관리할 것이다. 또한, addItem, getItem, countItem 등의 메소드를 구현해서 아이템을 추가하고, 아이템을 불러오고, 아이템의 갯수를 셀 수 있는 기능을 넣었다. 아이템은 프래그먼트 뷰를 말한다.
 
<br/><br/>
<hr/>
   
3. 그 다음 이 어답터를 사용할 수 있도록 OnCreate 부분에 코드를 넣어준다.   
   
**MainActivity.java**
```java
public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);
    }
	
    class  MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public  MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
```
   
 `MyPagerAdapter` 클래스를 객체화 시킨다. 그 후 각각의 프래그먼트 객체를 만들어주고 adapter 객체에 아이템을 추가해준다. 이 부분에서 각 아이템 객체(프래그먼트)는 아답터 내부의 `ArrayList`에 추가된다는 점을 이해해야 한다. 그 다음 pager에 아답터를 세팅해준다. `pager.setAdapter(adapter);`   
 
<br/><br/>
<hr/>
   
4. 손가락으로 화면을 전환하지 않고, 코드에서 전환시키고 싶다면 뷰페이지 객체의 `setCurruntItem()` 메서드를 사용하면 된다. `MainActivity.java`의 `OnCreate` 부분에 버튼 클릭 리스너를 넣어서 변환하도록 만들어 주자.   
   
**MainActivity.java**
```java
...

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);                // 탭기능과 뷰페이저 기능을 동시에.
            }
        });
	
...
```
   
**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/122880938-8b802e80-d375-11eb-867a-0ae57990f6e5.gif" width="40%">   

<br/><br/>
<hr/>

### 각 프래그먼트에 사진 넣어보기   
   
**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/122882145-d9496680-d376-11eb-9ce3-e704d97683c7.gif" width="40%">  


<br/><br/>
<hr/>

### 타이틀 스트립 추가하기     
   
 뷰페이저를 사용하다보면 현재 보고 있는 아이템이 어떤 것인지를 보여줄 필요가 있다. 이럴 때 사용하는 것이 타이틀 스트립이다. `activity_main.xml` 파일에 `<ViewPager>` 태그 안에 `<PaferTitleStrip>` 태그를 넣어 타이틀스트립을 구현해주자.   
   
**activity_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity" >

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="원하는 뷰로 바로 이동"
        android:textColor="#000000"
        android:textSize="20sp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="1" />

        <Button
            android:id="@+id/button2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="2" />

        <Button
            android:id="@+id/button3"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="3" />
    </LinearLayout>

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <androidx.viewpager.widget.PagerTitleStrip
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="top"
            android:background="#55cedf"
            android:textColor="#FFFFFF"
            android:paddingTop="5dp"
            android:paddingBottom="5dp">

        </androidx.viewpager.widget.PagerTitleStrip>

    </androidx.viewpager.widget.ViewPager>

</LinearLayout>
```
   
 `activity_main.xml`에서 구현해주었으니 기능을 실행하기 위해서 `getPageTitle()`메소드를 재정의하여 타이틀 스트립을 넣어준다.
   
**MainActivity.java**
```java
...
    class  MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public  MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
	
....

        @Nullable				// 추가해준다.
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지"+(position+1);
        }

...
```

**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/122885397-03505800-d37a-11eb-81d7-ce6e569e0b24.gif" width="40%">  


