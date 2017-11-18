package com.rapport.rapport_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

import static com.rapport.rapport_app.Fragment.MFragment.btnCount;

public class S_2_1Activity extends AppCompatActivity {

    //액션바
    private View actionbar;
    private TextView actionbar_label;

    public static Activity S_2_1Activity;

    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_2_1);

        //액티비티객체에 현재클래스 담기
        S_2_1Activity=S_2_1Activity.this;

        findViewById(R.id.btn_s_2_1).setOnClickListener(show_s_2_2);
    }

    private View.OnClickListener show_s_2_2 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i=new Intent(view.getContext(), S_2_2Activity.class);
            startActivity(i);

        }
    };

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

    @Override
    public void onBackPressed() {
        if(btnCount!=0){
            super.onBackPressed();
        }else{
            Intent i = new Intent(getApplicationContext(), S_1Activity.class);
            startActivity(i);
            finish();
        }

    }


    //이전 아이콘 클릭시
    private View.OnClickListener clickBackBtn=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (btnCount == 0) {
                Intent i = new Intent(getApplicationContext(), S_1Activity.class);
                startActivity(i);
            } else {
                backBtn.setOnClickListener(clickBackBtn);
                finish();
            }
        }
    };

    //폰트적용
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
