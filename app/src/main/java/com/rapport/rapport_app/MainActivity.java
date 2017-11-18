package com.rapport.rapport_app;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rapport.rapport_app.Adapter.PagerAdapter;
import com.rapport.rapport_app.Dialog.MPDialog;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {
    //다이얼로그
    private MPDialog m_MPDialog;

    //레이아웃
    public static ViewPager m_ViewPager;
    private TabLayout m_TabLayout;
    private View m_View;

    //액션바
    private RelativeLayout m_showSettingMenu;
    private LinearLayout m_s_1, m_s_2_1, m_s_3;
    private View actionbar;
    private boolean showMenu = false;

    //애니메이션
    Animation slide_down;
    Animation fade_out;

    //탭아이콘
    int selectedIcons []= {R.drawable.main_m, R.drawable.main_f, R.drawable.main_a,R.drawable.main_q};//탭 아이콘
    int icons[]={R.drawable.main_m_sel,R.drawable.main_f_sel,R.drawable.main_a_sel, R.drawable.main_q_sel};//선택된 탭 아이콘


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


 //        SharedPreferencesService.getInstance().load(getApplicationContext());
//        SharedPreferencesService.getInstance().setPrefStringData(SHARED_ALERT_HOUR,"key");
//        SharedPreferencesService.getInstance().getPrefStringData(SHARED_ALERT_HOUR);


        m_TabLayout=(TabLayout)findViewById(R.id.tabLayout);
        m_ViewPager=(ViewPager)findViewById(R.id.pager);

        m_showSettingMenu=(RelativeLayout)findViewById(R.id.showSettingMenu);
        m_s_1=(LinearLayout)findViewById(R.id.s_ll_rapoInfo);
        m_s_2_1=(LinearLayout)findViewById(R.id.s_ll_rapoOrderSet);
        m_s_3=(LinearLayout)findViewById(R.id.s_ll_rapoAlret);


        //4개의 탭생성
        for(int i=0; i<icons.length;i++){
            m_TabLayout.addTab(m_TabLayout.newTab().setIcon(icons[i]));
        }

        //첫화면 탭아이콘 초기화
        m_TabLayout.getTabAt(0).setIcon(selectedIcons[0]);

        //탭 아이콘 크기 조정
        for(int i=0; i<m_TabLayout.getTabCount(); i++){
            TabLayout.Tab tab = m_TabLayout.getTabAt(i);
            if(tab!=null) tab.setCustomView(R.layout.layout_custom_tab);
        }

        //페이지 연결
        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),m_TabLayout.getTabCount());
        m_ViewPager.setAdapter(adapter);


        //이벤트-페이저 체인지 이벤트와 탭의 이벤트를 동기화 시키는 것
        m_ViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(m_TabLayout));
        m_TabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택한 탭 아이콘 변경
                tab.setIcon(selectedIcons[tab.getPosition()]);
                m_ViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //unselected 탭 아이콘 변경
                tab.setIcon(icons[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        }); //end setOnTabSelectedListener

        // load the animation
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        fade_out =  AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);

        // set animation listener
        slide_down.setAnimationListener(this);
        fade_out.setAnimationListener(this);


        //ShowMPDialog();


        m_s_1.setOnClickListener(showS_1);
        m_s_2_1.setOnClickListener(showS_2_1);
        m_s_3.setOnClickListener(showS_3);


    }   //end onCreate()


    public void ShowMPDialog(){
        m_MPDialog = new MPDialog(this, closeListener);
        m_MPDialog.show();
    }

    private View.OnClickListener closeListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            m_MPDialog.dismiss();
        }
    };

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


    //백버튼 터치시
    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }//end onBackPressed

    @Override
    protected void onPause() {
        super.onPause();
        if(showMenu) {
            m_showSettingMenu.setVisibility(View.GONE);
            m_showSettingMenu.startAnimation(fade_out);
            showMenu = false;
        }
    }
}
