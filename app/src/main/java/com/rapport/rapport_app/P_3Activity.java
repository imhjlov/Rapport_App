package com.rapport.rapport_app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class P_3Activity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {
    //액션바
    private RelativeLayout m_showSettingMenu;
    private LinearLayout m_s_1, m_s_2_1, m_s_3;
    private View actionbar;
    private boolean showMenu = false;

    //애니메이션
    Animation slide_down;
    Animation fade_out;


    //mediaPlayer
    private ImageView mPlayBtn;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_3);

        //mediaPlayer
        mPlayBtn=(ImageView)findViewById(R.id.p_play_btn);
        mPlayBtn.setOnClickListener(this);

        mMediaPlayer = MediaPlayer.create(this, R.raw.shushing_sound_30s);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();//액티비티 실행시 자동재생

        //설정버튼
        findViewById(R.id.p_btn).setOnClickListener(setBtn);

        //setting메뉴 set
        m_showSettingMenu=(RelativeLayout)findViewById(R.id.showSettingMenu);
        m_s_1=(LinearLayout)findViewById(R.id.s_ll_rapoInfo);
        m_s_2_1=(LinearLayout)findViewById(R.id.s_ll_rapoOrderSet);
        m_s_3=(LinearLayout)findViewById(R.id.s_ll_rapoAlret);


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
            finish();

        }
    };

    private View.OnClickListener showS_2_1 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), S_2_1Activity.class);
            startActivity(i1);
            finish();

        }
    };

    private View.OnClickListener showS_3 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), S_3Activity.class);
            startActivity(i1);
            finish();
        }
    };
    private View.OnClickListener setBtn = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            finish();
            //서버에 값올리고, finish
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
        actionbar = inflater.inflate(R.layout.layout_custom_actionbar3, null);

        actionBar.setCustomView(actionbar);



        actionbar.findViewById(R.id.s_setting).setOnClickListener(showSettingMenu);
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

    //음악재생
    @Override
    public void onClick(View view) {

        if(mMediaPlayer!=null){
            IsPlaying();
        }else{
            mMediaPlayer=MediaPlayer.create(P_3Activity.this, R.raw.shushing_sound_30s);
            IsPlaying();

        }
    }

    private void IsPlaying(){
        if(!mMediaPlayer.isPlaying()){
            mMediaPlayer.start();
            mPlayBtn.setImageResource(R.drawable.p_shi_pause_btn);


        }else {
            mPlayBtn.setImageResource(R.drawable.p_shi_play_btn);
            mMediaPlayer.pause();
            mMediaPlayer.release();
            mMediaPlayer=null;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mediaplayer객체가 리소스 잡아먹는거 방지
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer=null;
        }


    }
}
