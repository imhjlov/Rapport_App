package com.rapport.rapport_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.v7.app.ActionBar;

import com.rapport.rapport_app.Bluetooth.BlunoLibrary;
import com.rapport.rapport_app.Utils.Timerecord;
import com.tsengvn.typekit.TypekitContextWrapper;


import java.util.Timer;
import java.util.TimerTask;

public class m_Activity extends BlunoLibrary implements Animation.AnimationListener{

    //레이아웃
    private Button buttonScan;
    private Button buttonSerialSend;
    private EditText serialSendText;
    private TextView serialReceivedText;
    private TextView tv_m__name;
    private ImageView iv_m_img;
    private TextView tv_m_order;
    private ProgressBar progressBar;


    //타이머
    private TextView timer_text;
    private TimerTask second;
    private final Handler handler = new Handler();
    static Timerecord bathrecord = new Timerecord();
    static Timerecord bookrecord = new Timerecord();
    static Timerecord massagerecord = new Timerecord();
    static Timerecord lullabyrecord = new Timerecord();


    int timer_sec;
    int count;

    boolean bRunning;


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
        setContentView(R.layout.activity_m_);
        onCreateProcess();														//onCreate Process by BlunoLibrary


        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200

        //받은데이터
        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data

        tv_m__name = (TextView)findViewById(R.id.tv_m__name);
        iv_m_img = (ImageView)findViewById(R.id.iv_m_img);
        tv_m_order=(TextView)findViewById(R.id.tv_m_order);

        m_showSettingMenu=(RelativeLayout)findViewById(R.id.showSettingMenu);
        m_s_1=(LinearLayout)findViewById(R.id.s_ll_rapoInfo);
        m_s_2_1=(LinearLayout)findViewById(R.id.s_ll_rapoOrderSet);
        m_s_3=(LinearLayout)findViewById(R.id.s_ll_rapoAlret);

        timer_text=(TextView)findViewById(R.id.tv_rapo_run_time);

//
//        //보내는데이터
//        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data
//
//        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
//        buttonSerialSend.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//
//                serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
//            }
//        });
//
//

        buttonScanOnClickProcess();

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

    protected void onResume(){
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        onResumeProcess();														//onResume Process by BlunoLibrary
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();
        if (showMenu) {
            m_showSettingMenu.setVisibility(View.GONE);
            m_showSettingMenu.startAnimation(fade_out);
            showMenu = false;//onPause Process by BlunoLibrary
        }
    }

    protected void onStop() {
        super.onStop();
        onStopProcess();														//onStop Process by BlunoLibrary
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called

    }

    @Override
    public void onSerialReceived(String theString) {                            //Once connection data received, this function will be called
        // TODO Auto-generated method stub
        serialReceivedText.append(theString);                            //append the text into the EditText
        //The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
        //((ScrollView) serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
        switch (theString) {                                            //Four connection state
            case "3\r\n":
                tv_m__name.setText("목욕");
                iv_m_img.setBackgroundResource(R.drawable.m__bath_icon);
                tv_m_order.setText("1");
                testStart();

                break;
            case "4\r\n":
                stopTask(bathrecord);
                Intent i1 = new Intent(this, EActivity.class);
                startActivity(i1);
                finish();
                break;
            case "5\r\n":
                tv_m__name.setText("동화책");
                iv_m_img.setBackgroundResource(R.drawable.m__book_icon);
                tv_m_order.setText("3");
                testStart();
                break;
            case "6\r\n":
                stopTask(bookrecord);
                Intent i2 = new Intent(this, EActivity.class);
                startActivity(i2);
                finish();
                break;
            case "1\r\n":
                tv_m__name.setText("마사지");
                iv_m_img.setBackgroundResource(R.drawable.m__massage_icon);
                tv_m_order.setText("2");
                testStart();
                break;
            case "2\r\n":
                stopTask(massagerecord);
                Intent i3 = new Intent(this, EActivity.class);
                startActivity(i3);
                finish();
                break;
            case "7\r\n":
                tv_m__name.setText("자장가");
                iv_m_img.setBackgroundResource(R.drawable.m__lullaby_icon);
                tv_m_order.setText("4");
                testStart();
                break;
            case "8\r\n":
                stopTask(lullabyrecord);
                Intent i4 = new Intent(this, EActivity.class);
                startActivity(i4);
                finish();
                break;
            default:
                break;
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


    //백버튼 터치시
    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }//end onBackPressed


    public void testStart() {
        timer_sec = 0;
        count = 0;
        bRunning=true;
        second = new TimerTask() {

            @Override
            public void run() {
                if(!bRunning) return;

                Log.i("Test", "Timer start");
                Update();
                timer_sec++;
            }
        };
        Timer timer = new Timer();
            timer.schedule(second, 0, 1000);

    }

    protected void Update() {
        Runnable updater = new Runnable() {
            public void run() {

                timer_text.setText(timer_sec + "초");
            }
        };
        handler.post(updater);
    }

    public void stopTask(Timerecord timerecord) {

        if (second != null) {
            timerecord.calMinSec(timer_sec);
            count = 0;
            bRunning = false;
            Log.d("TIMER", "timer canceled");
            second.cancel();
            second = null;
        }
    }

    //폰트적용
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
