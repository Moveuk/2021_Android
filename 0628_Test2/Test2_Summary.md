# Test2 Fragment


<hr/>

### 결과 화면

![MainActivity](https://user-images.githubusercontent.com/84966961/123595628-18bafb80-d82c-11eb-89b6-77f09711a5ad.png)
![fragment2](https://user-images.githubusercontent.com/84966961/123595642-1bb5ec00-d82c-11eb-9d15-24a4ec1c39c5.png)
![fragment3](https://user-images.githubusercontent.com/84966961/123595649-1d7faf80-d82c-11eb-9433-b883d5e44285.png)

```java
package org.techtown.test6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
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
//    public void onGotoMainFragment() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.ll2, fragment1).commit();
//    }
//
//    public void onButton1(View v) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.ll2, fragment2).commit();
//    }
//
//    public void onButton2(View v) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.ll2, fragment3).commit();
//    }
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
```
