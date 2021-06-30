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
