package org.techtown.drawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback{

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

//    @Override
//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.END)) {
//            drawer.closeDrawer(GravityCompat.END);
//        } else {
//            super.onBackPressed();
//        }
//    }

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
}