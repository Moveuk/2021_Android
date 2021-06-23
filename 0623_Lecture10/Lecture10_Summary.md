# 바로가기 메뉴바 구성
Key Word : 바로가기 메뉴, 햄버거 메뉴, NavigationDrawer, FragmentManager

<hr/>

## 교재 342p : 05-6 바로가기 메뉴 만들기   
   
 바로가기 메뉴는 화면의 좌측 상단에 위치한 햄버거 모양 아이콘을 눌렀을 때 나타나는 화면을 말한다. 웹이나 앱에서 자주 사용되는 기능이며 안드로이드에서는 NavigationDrawer라는 이름으로 불린다. 바로가기 메뉴는 몇 개의 화면에서 공통으로 보여줄 수 있기 때문에 빠르게 메뉴 기능에 접근하고자 할 때 사용한다. 또한, 로그인한 사용자의 프로필 정보나 설정 메뉴를 보여줄 때도 사용할 수 있다.   
   
**바로가기 모양**   
![image](https://user-images.githubusercontent.com/84966961/123049421-7d481600-d43a-11eb-9169-6f7e4030d644.png)
   
 바로가기 메뉴를 추가하는 가장 쉬운 방법은 안드로이드 스튜디오에서 제공하는 템플릿인 Navigation Drawer Activity를 선택하여 프로젝트를 실행하는 것이다.   
   
**새로운 프로젝트 만들기의 템플릿 화면**   
![image](https://user-images.githubusercontent.com/84966961/123049567-a7013d00-d43a-11eb-9b12-ea4230727f77.png)
   
 이제 바로가기 메뉴 예제를 만들어보자.

<br/><br/>
<hr/>

## 교재 343p : 05-6 바로가기 메뉴 예제   
   
1. 템플릿의 xml 파일을 수정하고 main.java 파일에서 툴바 객체를 만들어 액션바로 툴바를 쓸 수 있도록 만들고, Drawer를 토글로 불러올 수 있도록 액션바 토글 기능을 만든다.    
2. 이 후 네비게이션 뷰 객체를 만들어 네비게이션바가 선택되어졌을때 감지할 수 있도록 리스너를 만든다.  
3. 다음 맨처음 컨테이너에 보일 fragment1을 프래그먼트매니저를 통해 `commit()` 시켜준다.
   
   
 **MainActivity.java**
```
package org.techtown.drawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    toolbar = findViewById(R.id.toolbar);   // 툴바 객체 생셩
    setSupportActionBar(toolbar);           // 툴바를 액션바로 사용할 것이다.

    drawer = findViewById(R.id.drawer_layout);  // 레이아웃 객체 생성
    // 바로가기 메뉴 토글 액션바 만들기
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();     // 바로가기 메뉴 보여주게 하는 코드

        NavigationView navigationView = findViewById(R.id.nav_view);    // 네이게이션 뷰(실제 바로가기 메뉴의 내용) 객체화
        navigationView.setNavigationItemSelectedListener(this); //

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

    getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    // R.id.container activity_main.xml의 화면이 보이는 곳에 fragment1이 보이도록 한다.


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        return false;
    }
}
```
   
 **activity_main.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">

    <androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/Theme.SampleDrawer.AppBarOverlay">

            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                app:popupTheme="@style/Theme.SampleDrawer.PopupOverlay" />

        </com.google.android.material.appbar.AppBarLayout>

        <FrameLayout
            android:id="@+id/container"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

        <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:id="@+id/fab"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|end"
            android:layout_margin="@dimen/fab_margin"
            app:srcCompat="@android:drawable/ic_dialog_email" />

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" />
</androidx.drawerlayout.widget.DrawerLayout>
```
   
**결과 화면**   
<img src="https://user-images.githubusercontent.com/84966961/123049902-08c1a700-d43b-11eb-9e72-d87a06cb3d1f.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/123049915-0bbc9780-d43b-11eb-994a-1afab4b9e064.png" width="40%">   
   
   
   
<br/><br/>
<hr/>

4. `onFragmentSelected()` 메소드를 위한 `FragmentCallback` 인터페이스를 만든다.   
   
![image](https://user-images.githubusercontent.com/84966961/123051248-8c2fc800-d43c-11eb-9aaa-d1a6bea1e2d0.png)
   
```java
package org.techtown.drawer;

import android.os.Bundle;

public interface FragmentCallback {

    public void onFragmentSelected(int position, Bundle bundle);
}

```




   
<br/><br/>
<hr/>

5. `onNavigationItemSelected(@NonNull @NotNull MenuItem item)` 메소드를 마저 작성해준다. 이 메소드의 기능은 메뉴가 선택되었을 때 인식하고 뷰의 화면을 변경해주는 메소드를 호출해주는 기능이다.

```java
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu1) {
            Toast.makeText(getApplicationContext(),"첫 번째 메뉴 선택됨",Toast.LENGTH_LONG).show();
            onFragmentSelected(0, null);    // 프래그먼트 셀렉트 신호를 보낸다. 리스너가 받아서 열릴것이다.
        } else if (id == R.id.menu2) {
            Toast.makeText(getApplicationContext(),"두 번째 메뉴 선택됨",Toast.LENGTH_LONG).show();
            onFragmentSelected(1, null);
        } else if (id == R.id.menu3) {
            Toast.makeText(getApplicationContext(),"세 번째 메뉴 선택됨",Toast.LENGTH_LONG).show();
            onFragmentSelected(2, null);
        }

        drawer.closeDrawer(GravityCompat.START);    // 메뉴 서랍(drawer)를 닫아준다. start : 왼쪽으로 닫, End: 오른쪽으로 닫

            return true;
    }
```

6. 화면 보여주는 기능 구현.

```java
    @Override
    public void onFragmentSelected(int position, Bundle bundle) {   // 화면 보여주는 기능.
        Fragment curFragment = null;
        
        if (position == 0) {
            curFragment = fragment1;
            toolbar.setTitle("첫 번째 화면");
        } else if (position == 1) {
            curFragment = fragment2;
            toolbar.setTitle("첫 번째 화면");
        } else if (position == 2) {
            curFragment = fragment3;
            toolbar.setTitle("첫 번째 화면");
        }
        
        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }
```
**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/123053658-1842ef00-d43f-11eb-8e4f-2adab97b850e.gif" width="40%">   
   
<br/><br/>
<hr/>

## 바로가기 바 반대로 나오게 하기   
   
 xml의 네비게이션 위치를 바꾸어주면 나온다. 다만 햄버거 메뉴 버튼을 이용해 꺼내는 것은 상수 값이라 바꿀 수 없어서 왼쪽이나 오른쪽 슬라이드로 꺼내야한다.   
   
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="end">

    <androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/Theme.SampleDrawer.AppBarOverlay">

            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                app:popupTheme="@style/Theme.SampleDrawer.PopupOverlay" />

        </com.google.android.material.appbar.AppBarLayout>

        <FrameLayout
            android:id="@+id/container"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior"/>

        <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:id="@+id/fab"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|start"
            android:layout_margin="@dimen/fab_margin"
            app:srcCompat="@android:drawable/ic_dialog_email" />

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="end"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" />
</androidx.drawerlayout.widget.DrawerLayout>
```

맨처음 시작 고정부분과 `NavigationView의 gravity` 값을 end로 바꿔주면 다음과 같이 이동한다. 이 때 창을 눌렀을 때 꺼지는 기능인 `closeDrawer()`값을 해당 방향에 맞게 변경해줘야 한다.   
   
```java
...


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu1) {
            Toast.makeText(getApplicationContext(),"첫 번째 메뉴 선택됨",Toast.LENGTH_LONG).show();
            onFragmentSelected(0, null);    // 프래그먼트 셀렉트 신호를 보낸다. 리스너가 받아서 열릴것이다.
        } else if (id == R.id.menu2) {
            Toast.makeText(getApplicationContext(),"두 번째 메뉴 선택됨",Toast.LENGTH_LONG).show();
            onFragmentSelected(1, null);
        } else if (id == R.id.menu3) {
            Toast.makeText(getApplicationContext(),"세 번째 메뉴 선택됨",Toast.LENGTH_LONG).show();
            onFragmentSelected(2, null);
        }

        drawer.closeDrawer(GravityCompat.END);    // 클릭했을 때 메뉴 서랍(drawer)를 닫아준다. start : 왼쪽으로 닫, End: 오른쪽으로 닫

            return true;
    }


...
```


   
**결과화면**
![image](https://user-images.githubusercontent.com/84966961/123059452-c1d8af00-d444-11eb-8f2b-15f57d56868b.png)

 바로가기 버튼으로는 구현 불가능함. 왼쪽으로 막아놨음.








