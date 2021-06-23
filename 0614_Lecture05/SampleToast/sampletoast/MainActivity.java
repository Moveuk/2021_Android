package org.techtown.sampletoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
    }

    public void onButton1Clicked(View v) {
        try {
            int xOffset = Integer.parseInt(editText.getText().toString());
            int yOffset = Integer.parseInt(editText2.getText().toString());
            Toast toastView = Toast.makeText(this, "위치값 = X : "+xOffset+", Y : "+yOffset, Toast.LENGTH_LONG);

                toastView.setGravity(Gravity.TOP|Gravity.LEFT,xOffset,yOffset);
                toastView.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void onButton3Clicked(View v) {
        Snackbar.make(v,"스낵바입니다.", Snackbar.LENGTH_LONG).show();
    }
}