package org.techtown.mission13;

import android.view.View;

public interface OnCustomerItemClickListener {
    // 아이템을 클릭했을때 실행되는 추상 메소드
    // position 값만 가지고 있으면 됨.
    public void onItemClick(CustomerAdaptor.ViewHolder holder, View view, int position);

}
