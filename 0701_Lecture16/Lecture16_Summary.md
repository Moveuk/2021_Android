# Lecture16 : Mission 13
Key Word : 리사이클러뷰(RecyclerView), 어답터, 토스트 메세지(스윙), 

<hr/>

도전 13 만들어보기

**Customer.java**
```java
package org.techtown.mission13;

public class Customer {

    String name;
    String birth;
    String mobile;
    int resId;

    public Customer(String name, String birth, String mobile) {
        this.name = name;
        this.birth = birth;
        this.mobile = mobile;
    }

    public Customer(String name, String birth, String mobile, int resId) {
        this.name = name;
        this.birth = birth;
        this.mobile = mobile;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

```

**customer_item.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardBackgroundColor="#FFFFFFFF"
        app:cardCornerRadius="10dp"
        app:cardElevation="5dp"
        app:cardUseCompatPadding="true" >

        <LinearLayout
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="8dp">

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:src="@drawable/customer" />

            <LinearLayout
                android:orientation="vertical"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="10dp">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Name"
                    android:textSize="40sp"
                    android:textStyle="bold"
                    android:textColor="#ff5805" />

                <RelativeLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                    <TextView
                        android:id="@+id/textView2"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_alignParentLeft="true"
                        android:text="Birth"
                        android:textSize="20sp"
                        android:textColor="#0223e0"
                        android:layout_marginTop="6dp" />

                    <TextView
                        android:id="@+id/textView3"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_alignParentRight="true"
                        android:text="Mobile"
                        android:textSize="20sp"
                        android:textColor="#4902d6" />

                </RelativeLayout>

            </LinearLayout>

        </LinearLayout>

    </androidx.cardview.widget.CardView>

</LinearLayout>
```

**카드 뷰로 만든 리싸이클러 뷰 객체**   
   
![image](https://user-images.githubusercontent.com/84966961/124078849-549bce00-da83-11eb-9a76-e241faf66aeb.png)
   
**activity_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <LinearLayout
        android:id="@+id/layout1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <EditText
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:id="@+id/editText"
            android:hint="이름" />

        <EditText
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:id="@+id/editText3"
            android:hint="전화번호" />

    </LinearLayout>

    <LinearLayout
        android:id="@+id/layout2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/editText2"
            android:hint="생년월일" />

    </LinearLayout>

    <RelativeLayout
        android:id="@+id/layout3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:text="추가"
            android:id="@+id/button" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_alignBaseline="@+id/button"
            android:layout_marginRight="14dp"
            android:text="0 명"
            android:textColor="#000000"
            android:textSize="14dp"
            android:id="@+id/textView" />

    </RelativeLayout>

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

**CustomerAdapter.java**
```java
package org.techtown.mission13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerAdaptor.ViewHolder> {

    //    static TextView textView;  // 중첩클래스의 메소드 내부에서 쓰고싶으면 스태틱이어야함
    // customer 고객 객체 생성.
    ArrayList<Customer> items = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃 xml 파일을 객체화 시킴. 뷰그룹의 컨텍스트로부터 객체 생성
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // xml 인플레이터 메소드를 이용하여 customer_item을 itemView로 객체화함
        View itemView = inflater.inflate(R.layout.customer_item,parent,false);
        // 뷰홀더가 만들어질 때 ViewHolder(itemView)를 실행함.
        // 텍스트 정보를 itemView에 넣어주는것.
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdaptor.ViewHolder holder, int position) {
        // onBindViewHolder 객체가 새로 만들어질때 채워 넣는 메소드
        // 지금 만들어지려는 position을 가져와서 배열정보에서 맞는 값을 item에 저장하고
        Customer item = items.get(position);
        // 저장된 item을 이용하여 holder(ViewHolder 기능)에 세팅해줌.
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        // 아이템의 갯수를 리턴함.
        return items.size();
    }

    // 아이템 배열에 원하는 매개변수(item)을 추가하는 메소드
    public void addItem(Customer item) {
        items.add(item);
    }

    // 아이템 배열에서 원하는 position에 위치한 item을 받아오는 메소드
    public Customer getItem(int position) {
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;
        TextView textView3;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);

        }

        public void setItem(Customer item) {
            textView.setText(item.getName());
            textView2.setText(item.getBirth());
            textView3.setText(item.getMobile());
            imageView.setImageResource(item.getResId());
        }
    }

}
```

**MainActivity.java**
```java
package org.techtown.mission13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        /
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        customerAdaptor = new CustomerAdaptor();

        customerAdaptor.addItem(new Customer("김준수","1995-10-20","010-0000-0000",R.drawable.customer));
        customerAdaptor.addItem(new Customer("홍길동","1454-02-05","010-0000-0000",R.drawable.customer));

        recyclerView.setAdapter(customerAdaptor);
    }
}
```

**결과 화면**   
<img src="https://user-images.githubusercontent.com/84966961/124080968-ec9ab700-da85-11eb-94e0-b373e94873a7.png" width="40%">
   
    
<br/><br/>
---
   
### 토스트 메세지 띄우기

**CustomerAdaptor.java**
```java
package org.techtown.mission13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerAdaptor.ViewHolder>
                    implements OnCustomerItemClickListener{

    //    static TextView textView;  // 중첩클래스의 메소드 내부에서 쓰고싶으면 스태틱이어야함
    // customer 고객 객체 생성.
    ArrayList<Customer> items = new ArrayList<>();

    OnCustomerItemClickListener listener;

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃 xml 파일을 객체화 시킴. 뷰그룹의 컨텍스트로부터 객체 생성
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // xml 인플레이터 메소드를 이용하여 customer_item을 itemView로 객체화함
        View itemView = inflater.inflate(R.layout.customer_item,parent,false);
        // 뷰홀더가 만들어질 때 ViewHolder(itemView)를 실행함.
        // 텍스트 정보를 itemView에 넣어주는것.
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdaptor.ViewHolder holder, int position) {
        // onBindViewHolder 객체가 새로 만들어질때 채워 넣는 메소드
        // 지금 만들어지려는 position을 가져와서 배열정보에서 맞는 값을 item에 저장하고
        Customer item = items.get(position);
        // 저장된 item을 이용하여 holder(ViewHolder 기능)에 세팅해줌.
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        // 아이템의 갯수를 리턴함.
        return items.size();
    }

    // 아이템 배열에 원하는 매개변수(item)을 추가하는 메소드
    public void addItem(Customer item) {
        items.add(item);
    }

    // 아이템 배열에서 원하는 position에 위치한 item을 받아오는 메소드
    public Customer getItem(int position) {
        return items.get(position);
    }

    // 아이템 클릭시 토스트메시지 리턴 기능을 추가하기 위한 메소드 오버라이딩
    // 아래(setOnItemClickListener)에서 저장된 this.listener의 즉, 토스트 메세지를 호출하는 onItemClick을 호출함.
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        // 단순히 형식이 아니라 메인의 내용을 호출하는 기능.
        listener.onItemClick(holder,view,position);
    }

    // 메인에 정의한 listener(토스트가 들어있는 정보)를 가져옴. 이걸 어답터의 전역 변수(this.listener)에 저장함.
    public void setOnItemClickListener(OnCustomerItemClickListener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;
        TextView textView3;
        ImageView imageView;

        // 생성자를 선언하는 것임. 따라서 listener는 아무런 기능없고 단순히 인터페이스를 의미한다.
        // onclick에서 쓰이는 listen
        public ViewHolder(View itemView,OnCustomerItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);

            // 뷰홀더가 실행되면서 한개의 뷰 객체의 내용을 채워서 만든 후에 클릭리스너 기능도 추가함.
            // 모든 뷰는 setOnClickListener라는 리스너 기능을 가지고 있나봄.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 온클릭이 발생하면 position에 어답터로부터 현재 위치 인덱스값을 받아옴.
                    int position = getAdapterPosition();
                    // 뷰홀더가 실행될 때 받아온 listener의 onItemClick의 타입을 실행함.
                    listener.onItemClick(ViewHolder.this,v,position);
                }
            });
        }

        public void setItem(Customer item) {
            textView.setText(item.getName());
            textView2.setText(item.getBirth());
            textView3.setText(item.getMobile());
            imageView.setImageResource(item.getResId());
        }
    }
}
```
   

**MainActivity.java**
```java
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
```


**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124089585-fbd23280-da8e-11eb-8409-cf1ad624e7c9.png)     
 확대해서 볼것


    
<br/><br/>
---

### 앱 내부에서 추가하기
   
**MainActivity.java**
```java
...


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
  ...
```


**결과 화면**   
<img src="https://user-images.githubusercontent.com/84966961/124090246-9fbbde00-da8f-11eb-9637-7548736ffd56.png" width="40%"> <img src="https://user-images.githubusercontent.com/84966961/124090365-b9f5bc00-da8f-11eb-8b67-309e596eb0dd.png" width="40%">
   







