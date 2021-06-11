# 03 기본 위젯과 드로어블 사용하기
 
## 171p : 03-1 기본 위젯 다시 한 번 자세히 공부하기

1. SampleWidjet 프로젝트를 생성하여 수업 진행! 다음과 같은 계층 구조를 만들어 준다.
![image](https://user-images.githubusercontent.com/84966961/121638774-7f1eea80-cac6-11eb-816c-ffe1cc164c0c.png)
   
2. 실제 앱을 실행해보면 다음과 같은 화면이 뜨게 되는 데 상단의 SampleWidget이라는 문자열은 R.values.strings.xml 파일에 들어있는 정보이며, R.manifests.AndroidManifest.xml 내부의 label 에 지정되어있다.    
**R.values.strings.xml**
```
<resources>
    <string name="app_name">SampleWidget</string>
</resources>
```
**R.manifests.AndroidManifest.xml**
```
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"  // 이 값. at string의 app_name 값을 label로 지정하라.
 ...
```   
 **실행화면**    
 ![image](https://user-images.githubusercontent.com/84966961/121640233-a1b20300-cac8-11eb-87f1-6b47fbd73337.png)
    
3. 첫번째 텍스트뷰에 들어올 값을 R.values.strings.xml 파일에 추가하여 사람 이름을 참조 시켜보자.   
**R.values.strings.xml**
```
<resources>
    <string name="app_name">SampleWidget</string>
    <string name="person_name">이동욱</string>
</resources>
```
**R.layout.activity_main.xml**
```
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/person_name"/>
 ...
``` 
 **실행화면**
![image](https://user-images.githubusercontent.com/84966961/121640345-bf7f6800-cac8-11eb-90ef-46ef01144530.png)
   
4. 173p 부터 각종 텍스트 코드를 넣었다.   
**R.layout.activity_main.xml**    
```
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="#FFFF0000"
        android:textSize="40sp"
        android:textStyle="bold"
        android:typeface="serif"
        android:text="@string/person_name"/>

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="40sp"
        android:textStyle="bold"
        android:typeface="serif"
        android:maxLines="1"
        android:text="여기에 이름을 입력하세요. 이름은 한 줄로 표시됩니다."/>
  ```
 **실행화면**    
![image](https://user-images.githubusercontent.com/84966961/121640502-f8b7d800-cac8-11eb-8b85-9f689035dc8c.png)
   
5. 176p 코드에 maxline = 1을 넣게 되면 한줄만 나오게됨.
```
android:maxLines="1"
```   
**실행화면**    
![image](https://user-images.githubusercontent.com/84966961/121640719-3b79b000-cac9-11eb-9b22-3bd576b166b1.png)
   
6. 177p 버튼은 사용자가 클릭하면 클릭에 대한 반응을 하는 위젯입니다.    
  radio : 동그란 버튼   
  checkbox : 체크 박스 버튼   
  과 같이 다양한 버튼이 존재함.   
 **팔레트 패널에 보이는 다양한 버튼들**
![image](https://user-images.githubusercontent.com/84966961/121640858-6d8b1200-cac9-11eb-8eb3-c92bba729774.png)

7. 버튼들을 골라 다음과 같이 정렬하였다.    
**실행화면**    
 ![image](https://user-images.githubusercontent.com/84966961/121641231-e1c5b580-cac9-11eb-800d-46f238dbcb79.png)
   
8. 안드로이드 스튜디오 xml 파일의 코드는 HTML 방식과 비슷하고 자동 완성 기능과 직관적인 문법 명령어를 사용하기 때문에 코딩을 통한 디자인도 쉬운 편이다.    
**R.layout.activity_main.xml**
```
 ...
    <RadioGroup
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"
        android:paddingLeft="10dp"
        android:paddingRight="10dp">

        <RadioButton
            android:id="@+id/radioButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:textColor="#FFFF0000"
            android:textSize="40sp"
            android:text="남성"/>

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:textColor="#FFFF0000"
            android:textSize="40sp"
            android:text="여성" />
 ...
```
9. 177p ~ 179p : 예제 완성
    
![image](https://user-images.githubusercontent.com/84966961/121643100-6580a180-cacc-11eb-9596-c29f4fdf9fd5.png)![image](https://user-images.githubusercontent.com/84966961/121643502-d922ae80-cacc-11eb-8671-bedd755d3fd8.png)

10. 180p : 에디트 텍스트
```
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:paddingTop="28dp"
        android:gravity="center_horizontal">

        <EditText
            android:id="@+id/deitTextTextPersonName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ems="10"                         // ems : 글자수 제한?
            android:inputType="numberSigned"         // inputType : 입력되는 글자의 유형 정의
            android:text="이름을 입력하세요."         // 기본 안내문의 hint 표시
    </LinearLayout>
```
![image](https://user-images.githubusercontent.com/84966961/121644819-7af6cb00-cace-11eb-8731-2c28ba72dede.png)
   
  <hr/>
    
## 187p : 드로어블 만들기

1. SampleDrawable 프로젝트 작성.
2. 189p : 드로어블에 대한 설명






3. 버튼에 기본 이미지를 삽입한 상태에서 버튼을 눌렀을 때 버튼의 이미지가 변화하는 기능을 추가하려고 한다. 이를 위해 R.drawable 폴더에 finger_drawable.xml 파일을 생성후 다음과 같은 코드를 작성해준다.   
**R.drawable.finger_drawable.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true"
        android:drawable="@drawable/finger_pressed" />

    <item android:drawable="@drawable/finger"/>
</selector>
```   
4. 이 코드를 이제 버튼의 백그라운드 속성에 xml 파일을 직접 넣어주면 버튼이 작동한다.(HTML 구성과 비슷.)   
**핸드폰 이미지 변환 전 후**    
![image](https://user-images.githubusercontent.com/84966961/121647178-11c48700-cad1-11eb-9d60-301d1324c689.png)![image](https://user-images.githubusercontent.com/84966961/121647132-040f0180-cad1-11eb-8c83-1b669345ca9e.png)


5. 
6. ㅁ
7. ㅁ
8. ㅁ
9. ㅁㅁ
10. ㅁ
11. ㅁ
12. ㅁㅁ
13. ㅁ
14. ㅁ
15. ㅁㅁ
16. ㅁ
17. ㅁ
18. ㅁ
19. ㅁㅁ
20. 































