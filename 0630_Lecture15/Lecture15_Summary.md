# Lecture15 : 리사이클러뷰(RecyclerView)
Key Word : 리사이클러뷰(RecyclerView)

<hr/>
  
### 교재 416p : 07-4 리싸이클러 뷰 만들기

 리스트 뷰를 기존에는 썼엇지만 리싸이클러 뷰를 구글에서 제공해주면서 조금 더 가볍고 빠른 선택 위젯을 사용할 수 있게 되었다.   
 
 ---
 
 #### 리싸이클러의 개념
 
 (교재 422p 내용) 리싸이클러뷰에 보이는 여러개 아이템은 내부에서 캐시되기 때문에 아이템 개수만큼 객체로 만들어지지는 않는다. 예를 들어, 아이템이 천 개라고 하더라도 이 아이템을 위해 천 개의 뷰 객체가 만들어 지지 않는다. 메모리를 효율적으로 사용하려면 뷰홀더에 뷰 객체를 넣어두고 사용자가 스크롤하여 보이지 안게 된 뷰 객체를 새로 보일쪽에 재사용하는 것이 효율적이기 때문이다. 이 과정에서 뷰홀더가 재사용된다.   
   
![image](https://user-images.githubusercontent.com/84966961/123906625-7af03980-d9af-11eb-8041-2f84ac6b2631.png)   
 출처 : https://wooooooak.github.io/android/2019/03/28/recycler_view/
   
   
 위 그림처럼 리스트 뷰는 모든 객체를 만들어서 대기시켜야하지만 리싸이클러뷰는 뷰홀더 객체 내부에 보일 객체들을 나두었다가 재활용하면서 화면에 로딩시킨다.   
    
 이 말인 즉슨 단순히 내용물을 포함하는 **주머니만 화면에 맞게 생성해둔 후 내용물만 바꾸어 화면에 재활용**하여 출력한다는 뜻이다.   
    
 ---
 
 #### 뷰홀더(View Holder)의 개념
   
 화면에 출력되는 뷰 객체는 정해져 있고 이 것이 순환하며 재활용되는 것이 리싸이클러 뷰라고 하였다. 그렇다면 이 뷰 객체들을 기억하고 있을 누군가가 필요하다. 즉 이 것이 ViewHolder이다. 다음 코드를 보면 `PersonAdapter`라는 클래스에 `RecyclerView.Adapter<PersonAdapter.ViewHolder>` 아답터를 상속받아 정의하고 있다. 아답터 방식으로 리싸이클러 뷰가 진행된다는 것이며 뷰홀더가 만들어질떄 `onCreateViewHolder`가 자동 호출될 때 우리가 원하는 뷰 객체를 만들어서 보이도록(itemView라는 우리가 원하는 정보를 내포한 상태로) 코드를 작성하였다.   
 
`onBindViewHolder` 메서드는 뷰홀더가 재사용될 때 호출되므로 뷰객체는 기존 것을 그대로 사용하고 데이터만 바꿔준다. 이 메서드도 리싸이클러 뷰 주기에서 자동으로 호출된다. 이 때 자동으로 새로 생기는 뷰의 `position` 값을 가져와서 우리가 넣어주고 싶은 데이터 `item`을 setItem(item) 시켜 넣어주게 된다.
    
 **뷰홀더 예시**
 ```java
 public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<Person>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);   // 인플레이션을 통해 뷰 객체 만들기

        return new ViewHolder(itemView);    // 뷰 홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
...
```
 
 ---
 
#### 뷰 객체의 모양을 설정하는 person_item.xml
 
 `person_item.xml`는 뷰 객체의 주머니를 디자인한 파일이다. 즉 뷰 홀더 객체는 이 모양 그대로 복제하여 내용물만 바꾼채 재활용 하게 된다.
 
 **뷰 객체 디자인**
 
 <img src="https://user-images.githubusercontent.com/84966961/123912420-f7871600-d9b7-11eb-80c5-d6160e42b7ac.png" width="50%">   
    
**person_item.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardBackgroundColor="#FFFFFFFF"
        app:cardCornerRadius="10dp"
        app:cardElevation="5dp"
        app:cardUseCompatPadding="true" >

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <ImageView
                android:id="@+id/imageView"
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:padding="5dp"
                app:srcCompat="@mipmap/ic_launcher" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_margin="5dp"
                android:layout_weight="1"
                android:orientation="vertical">

                <TextView
                    android:id="@+id/textView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="이름"
                    android:textSize="30sp" />

                <TextView
                    android:id="@+id/textView2"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="전화번호"
                    android:textColor="#FF0000FF"
                    android:textSize="25sp" />
            </LinearLayout>

        </LinearLayout>

    </androidx.cardview.widget.CardView>

</LinearLayout>
```
   
<br/><br/>
   
---
    
#### 데이터 양식 클래스 : Person.java
    
 우리가 뷰 객체에 넣고 싶은 양식을 정하는 기능을 하는 클래스이다.
   
**Person.java**
```java
package org.techtown.recylerview;

public class Person {
    String name;
    String mobile;

    public Person(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }
}
```


<br/><br/>
   
---
    
#### 뷰 홀더 기능을 구현한 어답터 클래스
       
 뷰홀더의 개념에 정리한 내용을 담은 클래스 코드이다.   
 
**PersonAdapter.java**
```java
package org.techtown.recylerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<Person>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);   // 인플레이션을 통해 뷰 객체 만들기

        return new ViewHolder(itemView);    // 뷰 홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);  // 다음 차례의 뷰객체에 들어갈 번호를 이용해서 들어갈 정보를 객체화함.
        viewHolder.setItem(item);   // 뷰홀더에 아이템을 set시킴
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView) {  // 기본 생성자 생성.
            super(itemView);

            textView = itemView.findViewById(R.id.textView);   // 값을 세팅하기 위해 객체 주소 설정.
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(Person item) {      // 화면에 보이게끔 하는 메소드
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }

    // 직접 items 배열에 필요한 부분들을 넣거나 수정하고 확인하기 위해서 추가로 넣은 기능임.
    // 우리는 이 예제에서 이 기능을 이용해 MainActivity에서 직접 자료들을 넣어 줄 것임.
    public void addItem(Person item) {
        items.add(item);
    }

    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }

    public Person getItems(int position) {
        return items.get(position);
    }

    public void setItem(int position, Person item) {
        items.set(position,item);
    }
}
```




<br/><br/>
   
---
    
#### 리싸이클러 뷰 출력
       
 마지막으로 리싸이클러 뷰를 출력시키는 코드를 작성해놓은 `MainActivity.java` 클래스이다. 이 클래스에서 우리가 넣고 싶은 자료들을 직접 구현해본다.   
   
**MainActivity.java**
```java
package org.techtown.recylerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
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
    }
}
```

<br/>

---

**결과 화면**

 <img src="https://user-images.githubusercontent.com/84966961/123913776-92342480-d9b9-11eb-858f-46a7bd41fcbf.png" width="40%">  <img src="https://user-images.githubusercontent.com/84966961/123913782-92ccbb00-d9b9-11eb-9cc5-9a44ca642651.png" width="40%">  




<br/><br/>
   
<hr/>
    
### 교재 428p : 그리드 모양 리싸이클러 뷰 출력
          
 쉽게 MainActivity에서 리니어 레이아웃 매니저를 그리드 레이아웃 매니저로 바꾸어주면 된다.   
    
 ```java
 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);		// 2열 종대로
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
    }
}
```

<br/>

---

**결과 화면**

 <img src="https://user-images.githubusercontent.com/84966961/123916140-446ceb80-d9bc-11eb-8038-9d0b60250ec6.png" width="40%">    
   

<br/>

---

### 교재 431p : 뷰 클릭시 토스트 메세지 보이게 만들기
   
 단순히 어답터 내부에서 출력하는 것이 아니라 중간의 인터페이스를 두어 토스트 메세지를 출력할 수 있도록 기능을 구성해보려고 한다.   

 리스너 내부에서 토스트 메세지를 띄우면 클릭했을 때의 기능이 변경될 때마다 어댑터를 수정해야 하는 문제가 생기므로, 어택터 객체 밖에 리스너를 설정하고 설정된 리스너 쪽으로 이벤트를 전달 받도록 하는 것이 좋다.   

 이를 위해 OnPersonItemClickListener 인터페이스를 다음과 같이 정의한다.

**OnPersonItemClickListener.java**
```java
package org.techtown.recylerview;

import android.view.View;

public interface OnPersonItemClickListener {
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);
    
}
```
   
**PersonAdapter.java 수정**
```java
package org.techtown.recylerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
                        implements OnPersonItemClickListener {
    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);   // 인플레이션을 통해 뷰 객체 만들기

        return new ViewHolder(itemView, this);    // 뷰 홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);  // 다음 차례의 뷰객체에 들어갈 번호를 이용해서 들어갈 정보를 객체화함.
        viewHolder.setItem(item);   // 뷰홀더에 아이템을 set시킴
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override   // 여기서도 사용할 수 있도록 강제한다?
    public void onItemClick(ViewHolder holder, View view, int position) {   // 어답터에서 정의
        if (listener != null) { // 아이템 뷰 클릭시 미리 정의한 다른 리스너의 메서드 호출하기
            listener.onItemClick(holder, view, position);       // 이 온아이템클릭은 메인에서 하는 거임.
        }
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {  // 기본 생성자 생성.
            super(itemView);

            textView = itemView.findViewById(R.id.textView);   // 값을 세팅하기 위해 객체 주소 설정.
            textView2 = itemView.findViewById(R.id.textView2);

            // 아이템 뷰는 person_item 의 전체를 말하는거인듯?
            itemView.setOnClickListener(new View.OnClickListener() {    // 아이템 뷰에 OnClickListener 설정하기
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                     // 교재 432p 코드에서 맨위에 listener 선언.
                    if (listener != null) { // 아이템 뷰 클릭시 미리 정의한 다른 리스너의 메서드 호출하기
                        listener.onItemClick(ViewHolder.this, view, position);  // 정보가 필요하면 넣으면 됨. position은 꼭 필요한 정보.
                        // 지금은 position 빼고는 없어도 되는 매개 변수들임.
                    }
                }
            });
        }

        public void setItem(Person item) {      // 화면에 보이게끔 하는 메소드
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }

    // 직접 items 배열에 필요한 부분들을 넣거나 수정하고 확인하기 위해서 추가로 넣은 기능임.
    // 우리는 이 예제에서 이 기능을 이용해 MainActivity에서 직접 자료들을 넣어 줄 것임.
    public void addItem(Person item) {
        items.add(item);
    }

    public void setItem(ArrayList<Person> items) {
        this.items = items;
    }

    public Person getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Person item) {
        items.set(position,item);
    }
}
```
   

**MainActivity.java 수정**
```java
...
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
```


**결과 화면**   
   
<img src="https://user-images.githubusercontent.com/84966961/123924960-6a4abe00-d9c5-11eb-9a39-c393f30204f1.png" width="40%">
   
<br/>

---
