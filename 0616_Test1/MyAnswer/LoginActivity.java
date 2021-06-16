package org.techtown.test5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText button_id = findViewById(R.id.editTextTextPersonName);
        EditText button_pass = findViewById(R.id.editTextTextPassword);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button_id.getText().toString().length() == 0 || button_pass.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "아이디 패스워드를 입력하세요",Toast.LENGTH_LONG).show();

                } else if (button_id.getText().toString().length() != 0 && button_pass.getText().toString().length() != 0) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                    startActivity(intent);
                }
            }
        });
    }

}