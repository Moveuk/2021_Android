package org.techtown.mission13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;

    TextView textView;
    RecyclerView recyclerView;

    CustomerAdaptor customerAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 앱 내부에서 추가를 위한 editText 객체화.
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        textView = findViewById(R.id.textView);

        // 리사이클러 뷰 객체화 한 후 리사이클러를 리니어 레이아웃으로 실행시킴.
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        customerAdaptor = new CustomerAdaptor();

        customerAdaptor.addItem(new Customer("김준수","1995-10-20","010-0000-0000",R.drawable.customer));
        customerAdaptor.addItem(new Customer("홍길동","1454-02-05","010-0000-0000",R.drawable.customer));

        // xml 리사이클뷰에 어답터 장착.
        recyclerView.setAdapter(customerAdaptor);

        // 여기서 setOnItemClickListener를 호출해서 토스트가 들어간 listener객체를 어답터 전역변수에 저장하기 위해 보냄.
        customerAdaptor.setOnItemClickListener(new OnCustomerItemClickListener() {
            @Override
            public void onItemClick(CustomerAdaptor.ViewHolder holder, View view, int position) {
                Customer item = customerAdaptor.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 정보 : " + item.getName(),Toast.LENGTH_LONG).show();
            }
        });

        // 초기 리스트 갯수 설정.
        textView.setText(customerAdaptor.getItemCount()+"명");

        // 버튼 클릭시 editText 창에 들어있는 내용을 기반으로 ArrayList에 추가.
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String birth = editText2.getText().toString();
                String mobile = editText3.getText().toString();

                customerAdaptor.addItem(new Customer(name,birth,mobile,R.drawable.customer));
                customerAdaptor.notifyDataSetChanged();
                textView.setText(customerAdaptor.getItemCount()+"명");
            }
        });
    }
}