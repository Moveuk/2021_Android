package org.techtown.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        // 웹뷰의 설정 수정하기
        WebSettings webSettings = webView.getSettings();
        // 웹페이지에서 스크립트 기능들이 정상적으로 작동하게 해주는 설정
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new ViewClient());
        // 기능없이 사용할 때는 이것을 사용함.
//        webView.setWebViewClient(new WebViewClient());

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 웹사이트 로딩
                webView.loadUrl(editText.getText().toString());
            }
        });
    }

    // 기능을 넣고 싶을 때 이렇게 함.
    private class ViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            view.loadUrl(url);

            return true;
        }
    }
}