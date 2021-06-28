package org.techtown.test6;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_master extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragment1 = new Fragment1();
        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragmentview);
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);

    }
    public void onGotoMainFragment() {
        b1.setTypeface(Typeface.create("",Typeface.NORMAL));
        b1.setTextColor(Color.BLACK);
        b2.setTypeface(Typeface.create("",Typeface.BOLD));
        b2.setTextColor(Color.BLACK);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
    }

    public void onButton1(View v) {
        b1.setTypeface(Typeface.create("",Typeface.BOLD));
        b1.setTextColor(Color.RED);
        b2.setTypeface(Typeface.create("",Typeface.NORMAL));
        b2.setTextColor(Color.BLACK);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
    }

    public void onButton2(View v) {
        b1.setTypeface(Typeface.create("",Typeface.NORMAL));
        b1.setTextColor(Color.BLACK);
        b2.setTypeface(Typeface.create("",Typeface.BOLD));
        b2.setTextColor(Color.RED);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
    }


}