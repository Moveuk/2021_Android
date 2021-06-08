Hello Android

<img src="https://user-images.githubusercontent.com/84966961/121152404-2231f300-c880-11eb-83d5-ee97cb1c9132.JPG">

### 52p :버튼 활성화




### 86p :

뷰와 뷰의 크기 속성 이해하기.

<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
 --> 제약조건 레이아웃
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

Component Tree
 눈에 보이지는 않지만 이 화면에 어떤 뷰들이 어떤 계층 구조로 만들어져있는지 모여주는것.


    android:layout_height="match_parent"

match_parent : 창을 부모창에 맞춰서 보여줘라.

wrap_content : 컨텐츠의 크기에 맞게 씌워라.

94p

1. 버튼의 제약 조건을 성립하도록 기준점을 잡아준다.

2. 뷰의 위치 설정 magnet 표시 on/off
	-> 절대 위치, 상대 위치 설정 가능.

3. 뷰 미리보기 화면에서 <<, 지그재그, fixed 표시로 크기를 설정할 수 있다.

4. 디자인 미리보기 화면 위의 탭에서 가이드라인을 통해
레이아웃 이외에도 기준점을 잡을 수 있도록 가이드라인을 설치할 수 있다. 

  --   <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
	--> 가이드 라인의 방향 코드 추가됨.
        app:layout_constraintGuide_begin="161dp" />

5. 기준점을 어디로 잡았는지 코드에 나와있음.

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Button"
        app:layout_constraintStart_toStartOf="@+id/guideline4"
	--> 버튼 3은 Start를 잡아서 스타트에 붙였고 주소값은 가이드라인 4임.
        app:layout_constraintTop_toTopOf="parent" />
	--> 버튼 3의 탑 기준점을 잡아서 부모 레이아웃의 탑에 붙였다.

