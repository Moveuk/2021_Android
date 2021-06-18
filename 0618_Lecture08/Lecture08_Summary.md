# Lecture07 : 프래그먼트 화면 전환
Key Word : 프래그먼트(Fragment)   
   
<hr/>
   
## 교재 305p : 05-2 프래그먼트로 화면 만들기   
   
 다음과 같은 구조로 예제를 만들어 프래그먼트의 화면 전환을 배워볼 것이다.   
    
> MainActivity.java
> > ListFragment
> > > 버튼1   
> > > 버튼2   
> > > 버튼3   

> > ViewerFragment
> > > 이미지뷰

**MainActivity.java**
   
```java
public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelection{
    ListFragment listFragment;          // 리스트 프래그먼트 선언
    ViewerFragment viewerFragment;      // 뷰어 프래그먼트 선언

    int[] images = {R.drawable.dream01,R.drawable.dream02,R.drawable.dream03};  //드림01 이 정수 타입임? 이미지?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.ListFragment);
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.ViewerFragment);
    }


    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}
```
   
**ListFragment.java**
   

```java
public class ListFragment extends Fragment {

    public static interface ImageSelection{
        public  void onImageSelected(int position);
    }

    public ImageSelection callback;

    @Override
    public void onAttach(Context context) {     // 프래그먼트가 액티비티에 붙는 순간에 대해서 오버라이드함.
        super.onAttach(context);

        if (context instanceof ImageSelection) {
            // context에 메인액티비티 넣을거고 이미지 섹션의 자식으로 메인엑티비티를 넣을거임. 그러면 true가 나옴
            callback = (ImageSelection)context; // callback은 메인액티비티임.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        Button button = rootView.findViewById(R.id.button);
        Button button2 = rootView.findViewById(R.id.button2);
        Button button3 = rootView.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onImageSelected(2);
            }
        });


        return rootView;

    }
}
```
   
**ViewerFragment.java**
   
```java
public class ViewerFragment extends Fragment {
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);  // 이미지 바꿔주는 메소드
    }
}
```

 viewerFragment 에는 이미지 뷰만 존재

**실행 결과**
<img src="https://user-images.githubusercontent.com/84966961/122517207-b6534580-d04a-11eb-96e7-c92b0f35576c.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/122517241-bf441700-d04a-11eb-9e7b-efedf62e2dc2.png" width="30%"> <img src="https://user-images.githubusercontent.com/84966961/122517259-c2d79e00-d04a-11eb-8ce3-1b5be110f261.png" width="30%">
