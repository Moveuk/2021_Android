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
