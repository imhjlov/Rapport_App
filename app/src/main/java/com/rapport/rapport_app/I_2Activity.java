package com.rapport.rapport_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class I_2Activity extends AppCompatActivity implements Animation.AnimationListener {
    //액션바
    private RelativeLayout m_showSettingMenu;
    private LinearLayout m_s_1, m_s_2_1, m_s_3;
    private View actionbar;
    private boolean showMenu = false;

    //애니메이션
    Animation slide_down;
    Animation fade_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_2);

        m_showSettingMenu=(RelativeLayout)findViewById(R.id.showSettingMenu);
        m_s_1=(LinearLayout)findViewById(R.id.s_ll_rapoInfo);
        m_s_2_1=(LinearLayout)findViewById(R.id.s_ll_rapoOrderSet);
        m_s_3=(LinearLayout)findViewById(R.id.s_ll_rapoAlret);


        findViewById(R.id.i_btn).setOnClickListener(i_btn_click);


        // load the animation
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        fade_out =  AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);

        // set animation listener
        slide_down.setAnimationListener(this);
        fade_out.setAnimationListener(this);


        m_s_1.setOnClickListener(showS_1);
        m_s_2_1.setOnClickListener(showS_2_1);
        m_s_3.setOnClickListener(showS_3);
    }

    //설정 메뉴 클릭 이벤트
    private View.OnClickListener showS_1 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), S_1Activity.class);
            startActivity(i1);

        }
    };

    private View.OnClickListener showS_2_1 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), S_2_1Activity.class);
            startActivity(i1);

        }
    };

    private View.OnClickListener showS_3 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), S_3Activity.class);
            startActivity(i1);
        }
    };

    private View.OnClickListener i_btn_click = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            finish();

        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        if (showMenu) {
            m_showSettingMenu.setVisibility(View.GONE);
            m_showSettingMenu.startAnimation(fade_out);
            showMenu = false;//onPause Process by BlunoLibrary
        }
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
        actionbar = inflater.inflate(R.layout.layout_custom_actionbar, null);

        actionBar.setCustomView(actionbar);

        actionbar.findViewById(R.id.s_setting).setOnClickListener(showSettingMenu);


        return true;
    }


    //설정 아이콘 클릭시
    private View.OnClickListener showSettingMenu=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(showMenu){
                m_showSettingMenu.setVisibility(View.GONE);
                m_showSettingMenu.startAnimation(fade_out);
                showMenu=false;
            }else {
                m_showSettingMenu.setVisibility(View.VISIBLE);
                m_showSettingMenu.startAnimation(slide_down);
                showMenu=true;
            }
        }
    };



    //메뉴창 애니메이션
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
