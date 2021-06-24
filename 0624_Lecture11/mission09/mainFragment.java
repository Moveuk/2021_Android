package org.techtown.mission09;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class mainFragment extends Fragment {

    EditText nameInput;
    EditText ageInput;

    Button birthButton;
    Button saveButton;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");  //

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container
                , false);

        nameInput = rootView.findViewById(R.id.nameInput);
        ageInput = rootView.findViewById(R.id.ageInput);

        birthButton = rootView.findViewById(R.id.birthButton);
        saveButton = rootView.findViewById(R.id.saveButton);

        birthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = nameInput.getText().toString();
                String ageStr = ageInput.getText().toString();
                String birthStr = birthButton.getText().toString();

                Toast.makeText(getContext(), "이름 : " + nameStr + "\n 나이 : " + ageStr
                        + "\n 생년월일 : " + birthStr,Toast.LENGTH_LONG).show();


            }
        });
        Date curDate = new Date();      //java.util 패키지 import.
        setSelectDate(curDate);

        return rootView;
    }

    private void setSelectDate(Date curDate) {
        String selectDateStr = dateFormat.format(curDate);  // dateFormat에 정해놓은 양식대로 curDate 객체 초기화.
        birthButton.setText(selectDateStr);         // 버튼에 현재 날짜 set.
    }

    public void showDateDialog() {

        String birthDateStr = birthButton.getText().toString();

        Calendar calendar = Calendar.getInstance();     // Calendar는 추상 객체라 인스턴스화 못함?
        Date curBirthDate = new Date();

        try {
            curBirthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(curBirthDate);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int cutDay = calendar.get(Calendar.DAY_OF_MONTH);      // 화면이 문자열이라 바꿔줘야함.

        DatePickerDialog dialog = new DatePickerDialog(getContext()
                , birthDateSetListener, curYear, curMonth, cutDay);
        dialog.show();

    }

    public DatePickerDialog.OnDateSetListener birthDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // 데이트 픽커에서 날짜를 선택하면 위의 매개 변수 자리에 자동으로 들어감.
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(Calendar.YEAR,year);
            selectedCalendar.set(Calendar.MONTH,month);
            selectedCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            Date curDate = selectedCalendar.getTime();
            setSelectDate(curDate);

        }
    };
}