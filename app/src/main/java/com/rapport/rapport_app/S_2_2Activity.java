package com.rapport.rapport_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rapport.rapport_app.DB.DB_Manager;
import com.rapport.rapport_app.Utils.SharedPreferencesService;
import com.tsengvn.typekit.TypekitContextWrapper;

import static com.rapport.rapport_app.Fragment.MFragment.btnCount;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ORDER_BATH;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ORDER_BOOK;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ORDER_LULLABY;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ORDER_MASSAGE;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ORDER_SET_OK;

import java.util.ArrayList;

import static com.rapport.rapport_app.MainActivity.m_ViewPager;

public class S_2_2Activity extends AppCompatActivity {
    //전화면 스텍틱변수 넣어두기
    S_2_1Activity s_2_1Activity = (S_2_1Activity)S_2_1Activity.S_2_1Activity;

    //액션바
    //액션바
    private View actionbar;
    private TextView actionbar_label;

    //DB
    private DB_Manager db_manager;
    //private DB_Define db_define;
    private ArrayList<Integer> results;

    int numButton=0;
    private int bathNum, massageNum, bookNum, lullabyNum,ok;
    boolean bathClick,massageClick, lullabyClick, bookClick=false;
    private int orders[];
    final ImageView[] imageViews = new ImageView[4];
    final ImageView[] nexts=new ImageView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_2_2);
        init();

        db_manager=new DB_Manager();
        results=new ArrayList<Integer>();


        bathNum= SharedPreferencesService.getInstance().getPrefIntData(SHARED_ORDER_BATH);
        massageNum=SharedPreferencesService.getInstance().getPrefIntData(SHARED_ORDER_MASSAGE);
        lullabyNum=SharedPreferencesService.getInstance().getPrefIntData(SHARED_ORDER_LULLABY);
        bookNum=SharedPreferencesService.getInstance().getPrefIntData(SHARED_ORDER_BOOK);
        ok=SharedPreferencesService.getInstance().getPrefIntData(SHARED_ORDER_SET_OK);
        orders = new int[]{bathNum, massageNum, lullabyNum, bookNum};


        if(ok!=1) {
            imageViews[0].setVisibility(View.INVISIBLE);
            imageViews[1].setVisibility(View.INVISIBLE);
            imageViews[2].setVisibility(View.INVISIBLE);
            imageViews[3].setVisibility(View.INVISIBLE);
        }else{
            //저장된 이미지도 불러오기
            imageViews[0].setVisibility(View.INVISIBLE);
            imageViews[1].setVisibility(View.INVISIBLE);
            imageViews[2].setVisibility(View.INVISIBLE);
            imageViews[3].setVisibility(View.INVISIBLE);
        }


        Button pattern_end_btn = (Button)findViewById(R.id.pattern_end_btn);
        pattern_end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numButton==0){
                    Toast.makeText(S_2_2Activity.this, "수면의식을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else {

                        //순서 저장
                        OrderSave();
                        if(btnCount==0){
                            Toast.makeText(S_2_2Activity.this, "순서가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(v.getContext(), S_3Activity.class);
                            startActivity(i);
                            s_2_1Activity.finish();//s_2_1Activity액티비티 종료
                            finish();

                        }else {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            Toast.makeText(S_2_2Activity.this, "순서가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                            m_ViewPager.setCurrentItem(1);

                            startActivity(intent);//다음 액티비티 이동
                            s_2_1Activity.finish();//s_2_1Activity액티비티 종료
                            finish();

                        }



                }

            }
        });//end pattern_end_btn onClickListener()

    }

    private void init() {
        SharedPreferencesService.getInstance().load(getApplicationContext());//ShredPreferencesService를 쓰기 위해서 선언

        imageViews[0] = (ImageView) findViewById(R.id.order1);
        imageViews[1] = (ImageView) findViewById(R.id.order2);
        imageViews[2] = (ImageView) findViewById(R.id.order3);
        imageViews[3] = (ImageView) findViewById(R.id.order4);

        nexts[0] = (ImageView) findViewById(R.id.next1);
        nexts[1] = (ImageView) findViewById(R.id.next2);
        nexts[2] = (ImageView) findViewById(R.id.next3);
        nexts[3] = (ImageView) findViewById(R.id.next4);

        findViewById(R.id.order_bath).setOnClickListener(ivBathClick);
        findViewById(R.id.order_massage).setOnClickListener(ivMassageClick);
        findViewById(R.id.order_lullaby).setOnClickListener(ivLullabyClick);
        findViewById(R.id.order_book).setOnClickListener(ivBookClick);



    }


    private View.OnClickListener ivBathClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (numButton!=4) {
                //선택되지 않은 상태일때 클릭시
                if (!bathClick) {
                    bathClick = true;//클릭됨
                    bathNum = numButton;//순서저장
                    imageViews[bathNum].setBackgroundResource(R.drawable.s_2_2_bath_icon);
                    imageViews[bathNum].setVisibility(View.VISIBLE);//순서에 맞는 위치에 이미지 보여주기
                    nexts[bathNum].setVisibility(View.VISIBLE);//화살표보여주기
                    //order_bath.setBackgroundResource(R.drawable.bath_clicked);
                    numButton++;
                    //선택된 상태일때 클릭시
                } else {
                    if (bathNum == (numButton - 1)) {
                        bathClick = false;
                        imageViews[bathNum].setVisibility(View.INVISIBLE);
                        nexts[bathNum].setVisibility(View.INVISIBLE);
                        //order_bath.setBackgroundResource(R.drawable.bath_unclicked);
                        for (int i = bathNum; i < numButton; i++) {
                            imageViews[i + 1].setVisibility(View.INVISIBLE);
                            nexts[i + 1].setVisibility(View.INVISIBLE);
                            Drawable background = imageViews[i + 1].getBackground();
                            imageViews[i + 1].setBackground(null);
                            imageViews[i].setBackground(background);
                            imageViews[i].setVisibility(View.VISIBLE);

                        }
                        numButton--;
                        bathNum = 10;
                    } else {
                        Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();
                    }
                }
            } else{
                if(bathNum!=3) {
                    Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                }else {
                    bathClick = false;
                    imageViews[bathNum].setVisibility(View.INVISIBLE);
                    nexts[bathNum].setVisibility(View.INVISIBLE);
                    imageViews[bathNum].setBackground(null);
                    numButton--;
                    bathNum = 10;
                }

            }


        }
    };//end ivBathClick


    private View.OnClickListener ivMassageClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (numButton!=4) {
                //선택되지 않은 상태일때 클릭시
                if (!massageClick) {
                    massageClick = true;
                    massageNum = numButton;
                    imageViews[massageNum].setBackgroundResource(R.drawable.s_2_2_massage_icon);
                    imageViews[massageNum].setVisibility(View.VISIBLE);
                    nexts[massageNum].setVisibility(View.VISIBLE);
                    //order_massage.setBackgroundResource(R.drawable.massage_clicked);
                    numButton++;
                    //선택된 상태일때 클릭시
                } else {
                    if (massageNum == (numButton - 1)) {
                        massageClick = false;
                        imageViews[massageNum].setVisibility(View.INVISIBLE);
                        nexts[massageNum].setVisibility(View.INVISIBLE);
                        for (int i = massageNum; i < numButton; i++) {
                            imageViews[i + 1].setVisibility(View.INVISIBLE);
                            nexts[i + 1].setVisibility(View.INVISIBLE);
                            Drawable background = imageViews[i + 1].getBackground();
                            imageViews[i + 1].setBackground(null);
                            imageViews[i].setBackground(background);
                            imageViews[i].setVisibility(View.VISIBLE);

                        }
                        numButton--;
                        massageNum = 10;
                    }else{
                        Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                    }
                }
            } else{
                if(massageNum!=3) {
                    Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                }else {

                    massageClick = false;
                    imageViews[massageNum].setVisibility(View.INVISIBLE);
                    nexts[massageNum].setVisibility(View.INVISIBLE);
                    imageViews[massageNum].setBackground(null);
                    numButton--;
                    massageNum = 10;
                }

            }

        }
    }; //end ivMassageClick


    private View.OnClickListener ivLullabyClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (numButton != 4) {
                //선택되지 않은 상태일때 클릭시
                if (!lullabyClick) {
                    lullabyClick = true;
                    lullabyNum = numButton;
                    imageViews[lullabyNum].setBackgroundResource(R.drawable.s_2_2_lullaby_icon);
                    imageViews[lullabyNum].setVisibility(View.VISIBLE);
                    nexts[lullabyNum].setVisibility(View.VISIBLE);
                    //order_lullaby.setBackgroundResource(R.drawable.lullaby_clicked);
                    numButton++;
                    //선택된 상태일때 클릭시
                } else {
                    if (lullabyNum == (numButton - 1)) {
                        lullabyClick = false;
                        imageViews[lullabyNum].setVisibility(View.INVISIBLE);
                        nexts[lullabyNum].setVisibility(View.INVISIBLE);
                        //order_lullaby.setBackgroundResource(R.drawable.lullaby_unclicked);
                        for (int i = lullabyNum; i < numButton; i++) {
                            imageViews[i + 1].setVisibility(View.INVISIBLE);
                            nexts[i + 1].setVisibility(View.INVISIBLE);
                            Drawable background = imageViews[i + 1].getBackground();

                            imageViews[i].setBackground(background);
                            imageViews[i + 1].setBackground(null);
                            imageViews[i].setVisibility(View.VISIBLE);


                        }
                        numButton--;
                        lullabyNum = 10;
                    } else {
                        Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                    }
                }
            } else {
                if (lullabyNum != 3) {
                    Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                } else{
                    lullabyClick = false;
                    imageViews[lullabyNum].setVisibility(View.INVISIBLE);
                    nexts[lullabyNum].setVisibility(View.INVISIBLE);
                    imageViews[lullabyNum].setBackground(null);
                    //order_book.setBackgroundResource(R.drawable.book_unclicked);
                    numButton--;
                    lullabyNum = 10;

                }


            }

        }
    };//end ivLullabyClick

    private View.OnClickListener ivBookClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (numButton!=4) {
                //선택되지 않은 상태일때 클릭시
                if (!bookClick) {
                    bookClick = true;
                    bookNum = numButton;
                    imageViews[bookNum].setBackgroundResource(R.drawable.s_2_2_book_icon);
                    imageViews[bookNum].setVisibility(View.VISIBLE);
                    nexts[bookNum].setVisibility(View.VISIBLE);
                    //order_book.setBackgroundResource(R.drawable.book_clicked);
                    numButton++;
                    //선택된 상태일때 클릭시
                } else {
                    if (bookNum == (numButton - 1)) {
                        bookClick = false;
                        imageViews[bookNum].setVisibility(View.INVISIBLE);
                        nexts[bookNum].setVisibility(View.INVISIBLE);
                        //order_book.setBackgroundResource(R.drawable.book_unclicked);
                        for (int i = bookNum; i < numButton; i++) {
                            imageViews[i + 1].setVisibility(View.INVISIBLE);
                            nexts[i + 1].setVisibility(View.INVISIBLE);
                            Drawable background = imageViews[i + 1].getBackground();
                            imageViews[i + 1].setBackground(null);
                            imageViews[i].setBackground(background);
                            imageViews[i].setVisibility(View.VISIBLE);

                        }
                        numButton--;
                        bookNum = 10;
                    }else {
                        Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                    }
                }
            } else{
                if(bookNum!=3) {
                    Toast.makeText(S_2_2Activity.this, "순서대로 지우셔야 합니다", Toast.LENGTH_SHORT).show();

                }else {
                    bookClick = false;
                    imageViews[bookNum].setVisibility(View.INVISIBLE);
                    nexts[bookNum].setVisibility(View.INVISIBLE);
                    imageViews[bookNum].setBackground(null);
                    //order_book.setBackgroundResource(R.drawable.book_unclicked);
                    numButton--;
                    bookNum = 10;

                }

            }

        }
    };//ivBookClick

    private void OrderSave(){
        SharedPreferencesService.getInstance().setPrefIntData(SHARED_ORDER_BATH, bathNum);
        SharedPreferencesService.getInstance().setPrefIntData(SHARED_ORDER_MASSAGE, massageNum);
        SharedPreferencesService.getInstance().setPrefIntData(SHARED_ORDER_LULLABY, lullabyNum);
        SharedPreferencesService.getInstance().setPrefIntData(SHARED_ORDER_BOOK, bookNum);
        SharedPreferencesService.getInstance().setPrefIntData(SHARED_ORDER_SET_OK, 1);
    }
    private void OkDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("수면의식 순서 설정");
        builder.setMessage("이미 등록되어있는 순서가 있습니다. 재등록 하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        OrderSave();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(S_2_2Activity.this, "순서가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        m_ViewPager.setCurrentItem(1);

                        startActivity(intent);//다음 액티비티 이동
                        finish();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(S_2_2Activity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                        m_ViewPager.setCurrentItem(1);

                        startActivity(intent);//다음 액티비티 이동
                        finish();
                    }
                });
        builder.show();
    }



    //커스텀 액션바
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        actionbar = inflater.inflate(R.layout.layout_custom_actionbar2, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        actionbar_label=(TextView)actionbar.findViewById(R.id.actionbar2_label);
        actionbar_label.setText(R.string.s_rapoOrderSet);

        actionbar.findViewById(R.id.backBtn).setOnClickListener(clickBackBtn);


        return true;
    }


    //이전 아이콘 클릭시
    private View.OnClickListener clickBackBtn=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                finish();
        }
    };

    //폰트적용
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
