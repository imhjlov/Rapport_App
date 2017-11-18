package com.rapport.rapport_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rapport.rapport_app.Fragment.MFragment;
import com.rapport.rapport_app.Utils.Time;
import com.rapport.rapport_app.Utils.Timerecord;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.w3c.dom.Text;


public class F_1_Activity extends AppCompatActivity implements Animation.AnimationListener {


    //액션바
    private RelativeLayout m_showSettingMenu;
    private LinearLayout m_s_1, m_s_2_1, m_s_3;
    private View actionbar;
    private boolean showMenu = false;

    //애니메이션
    Animation slide_down;
    Animation fade_out;

    //layout
    private TextView bathTime, bookTime, massageTime, lullabyTime, totalTime,
    startTime, date;

    //Timerecord
    Timerecord bathrecord = m_Activity.bathrecord;
    Timerecord bookrecord = m_Activity.bookrecord;
    Timerecord massagerecord = m_Activity.massagerecord;
    Timerecord lullabyrecord = m_Activity.lullabyrecord;

    //Time
    Time time= MFragment.time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_1_);
        m_showSettingMenu=(RelativeLayout)findViewById(R.id.showSettingMenu);
        m_s_1=(LinearLayout)findViewById(R.id.s_ll_rapoInfo);
        m_s_2_1=(LinearLayout)findViewById(R.id.s_ll_rapoOrderSet);
        m_s_3=(LinearLayout)findViewById(R.id.s_ll_rapoAlret);

        //layout
        bathTime=(TextView)findViewById(R.id.f_1__bath_tv);
        bookTime=(TextView)findViewById(R.id.f_1__book_tv);
        massageTime=(TextView)findViewById(R.id.f_1__massage_tv);
        lullabyTime=(TextView)findViewById(R.id.f_1__lullaby_tv);
        totalTime=(TextView)findViewById(R.id.f_1__total_time);
        startTime=(TextView)findViewById(R.id.f_1__start_time);
        date=(TextView)findViewById(R.id.f_1__date);

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
        findViewById(R.id.f_1__btn).setOnClickListener(end);

        todayTime();


    }


    private void todayTime(){
        //Timerecord
        Timerecord record[]={bathrecord,bookrecord,massagerecord,lullabyrecord};

        TextView Time_tv[]={bathTime,bookTime,massageTime,lullabyTime};


        int mTotalTime=bathrecord.getTotalSec()+bookrecord.getTotalSec()+massagerecord.getTotalSec();
        if(mTotalTime==0){
            totalTime.setText("-");
        }else if(mTotalTime>=60) {
            Timerecord totalTime_min = new Timerecord();
            totalTime_min.calMinSec(mTotalTime);
            totalTime.setText(totalTime_min.getMin() + "분");
        }else{
            totalTime.setText(mTotalTime+"초");
        }

        for(int i=0; i<record.length;i++){
            if(record[i].getTotalSec()==0){
                Time_tv[i].setText("-");
            }else if(record[i].getTotalSec()>=60){
                Time_tv[i].setText(record[i].getMin()+"분");
            }else{
                Time_tv[i].setText(record[i].getSec()+"초");
            }
        }

        startTime.setText(time.getFormatTime());
        date.setText(time.getFormatDate());





    }
    private View.OnClickListener end = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //finish();
            Intent i=new Intent(F_1_Activity.this, F_1Activity.class);
            startActivity(i);

        }
    };
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

    //폰트적용
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
