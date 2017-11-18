package com.rapport.rapport_app;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rapport.rapport_app.Adapter.S_1_PagerAdapter;
import com.tsengvn.typekit.TypekitContextWrapper;

import static com.rapport.rapport_app.Fragment.MFragment.btnCount;

public class S_1Activity extends AppCompatActivity {
    //액션바
    private View actionbar;
    private TextView actionbar_label;

    //레이아웃
    public static ViewPager m_ViewPager;
    private TabLayout m_TabLayout;
    private View m_View;
    private ImageView backBtn;

    //탭아이콘
    int selectedIcons []= {R.drawable.s_1_sellected_btn,R.drawable.s_1_sellected_btn};// 아이콘
    int icons[]={R.drawable.s_1_sel_btn, R.drawable.s_1_sel_btn};//탭 아이콘


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_1);

        //viewPager
        m_TabLayout=(TabLayout)findViewById(R.id.s_1tabLayout);
        m_ViewPager=(ViewPager)findViewById(R.id.s_1viewPager);


        //4개의 탭생성
        for(int i=0; i<icons.length;i++){
            m_TabLayout.addTab(m_TabLayout.newTab().setIcon(icons[i]));
        }

        //첫화면 탭아이콘 초기화
        m_TabLayout.getTabAt(0).setIcon(selectedIcons[0]);

        //탭 아이콘 크기 조정
        for(int i=0; i<m_TabLayout.getTabCount(); i++){
            TabLayout.Tab tab = m_TabLayout.getTabAt(i);
            if(tab!=null) tab.setCustomView(R.layout.layout_custom_s_1_navi);
        }

        //페이지 연결
        S_1_PagerAdapter adapter=new S_1_PagerAdapter(getSupportFragmentManager(),m_TabLayout.getTabCount());
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
        if(btnCount==0){
            actionbar = inflater.inflate(R.layout.layout_custom_actionbar4, null);
            actionbar.findViewById(R.id.btn_next).setOnClickListener(clickNextBtn);
        }else {
            actionbar = inflater.inflate(R.layout.layout_custom_actionbar2, null);
        }

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        backBtn=(ImageView)actionbar.findViewById(R.id.backBtn);
        if(btnCount==0) {
            backBtn.setVisibility(View.INVISIBLE);
        }else{
            backBtn.setOnClickListener(clickBackBtn);
        }



        actionbar_label=(TextView)actionbar.findViewById(R.id.actionbar2_label);
        actionbar_label.setText(R.string.s_rapoInfo);

        return true;
    }


    //이전 아이콘 클릭시
    private View.OnClickListener clickBackBtn=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener clickNextBtn=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(v.getContext(), S_2_1Activity.class);
            startActivity(i);
            finish();

        }
    };

    @Override
    public void onBackPressed() {
        if(btnCount!=0){
            super.onBackPressed();
        }

    }

    //폰트적용
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}
