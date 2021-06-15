package org.techtown.samplecallintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent receiveIntent = getIntent();
        String username = receiveIntent.getStringExtra("username");
        String password = receiveIntent.getStringExtra("password");

        Toast.makeText(this,"username : "+username+", password : "+password,Toast.LENGTH_LONG).show();

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName name = new ComponentName("org.techtown.samplecallintent",
                        "org.techtown.samplecallintent.MainActivity");
                intent.setComponent(name);  //인텐트 객체에 컴포넌트 지정
                startActivityForResult(intent, 101);
            }
        });
    }

}