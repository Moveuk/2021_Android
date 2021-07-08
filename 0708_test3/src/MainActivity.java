package org.techtown.test7_webviewnstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;
    String CurrentURL;

    public static Stack back = new Stack<>();
    public static Stack forward = new Stack<>();
    public static Stack current = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        // back, forward 만 쓰면 스택 끝으로 갔을 때 두번 반복해서 웹페이지가 실행되는 오류가 있어서
        // current 를 따로 만들어서 했습니다.
        // 기존 방식과 똑같고 현재 페이지 주소만 current에 존재하게끔 만들었습니다.
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentURL = editText.getText().toString();
                if (!current.empty()){
                    back.push(current.pop());
                }
                webView.loadUrl(CurrentURL);
                current.push(CurrentURL);
                if (!forward.empty()) {
                    forward.clear();
                    Log.d("Stack : URL",editText.getText().toString());
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!back.empty()) {
                    forward.push(current.pop());
                    CurrentURL = (String) current.push(back.pop());
                    webView.loadUrl(CurrentURL);
                    editText.setText(CurrentURL);
                    Log.d("Stack : URL",CurrentURL);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!forward.empty()) {
                    back.push(current.pop());
                    CurrentURL = (String) current.push(forward.pop());
                    webView.loadUrl(CurrentURL);
                    editText.setText(CurrentURL);
                    Log.d("Stack : URL",CurrentURL);
                }
            }
        });
    }

}