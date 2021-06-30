package org.techtown.recylerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);   // 2열 종대로
        recyclerView.setLayoutManager(layoutManager);

        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수", "010-0000-0000"));
        adapter.addItem(new Person("김하늘", "010-0000-0000"));
        adapter.addItem(new Person("홍길동", "010-0000-0000"));
        adapter.addItem(new Person("이동욱", "010-0000-0000"));
        adapter.addItem(new Person("조영민", "010-0000-0000"));
        adapter.addItem(new Person("음동원", "010-0000-0000"));
        adapter.addItem(new Person("여가은", "010-0000-0000"));
        adapter.addItem(new Person("피효정", "010-0000-0000"));
        adapter.addItem(new Person("조규원", "010-0000-0000"));
        adapter.addItem(new Person("etc", "010-0000-0000"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"아이템선택됨 : "+item.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}