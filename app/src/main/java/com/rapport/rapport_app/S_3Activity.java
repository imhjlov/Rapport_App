package com.rapport.rapport_app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import com.rapport.rapport_app.Picker.TimePicker;
import com.rapport.rapport_app.Utils.SharedPreferencesService;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.w3c.dom.Text;

import static com.rapport.rapport_app.Fragment.MFragment.btnCount;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ALERT_HOUR;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ALERT_MINUTE;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ORDER_BATH;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_RINGTONE;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ALERT_VOLUME;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class S_3Activity extends AppCompatActivity implements View.OnClickListener, TimePicker.TimeWatcher {

    //액션바
    private View actionbar;
    private TextView actionbar_label;

    private Button btSetRapoAlertGoAlert, btSetRapoAlertSetRingtone, btn_s_3_bell;
    private AlarmManager amSetRapoAlert;
    private TimePicker tpSetRapoAlert;
    private TextView soundPicker;
    private SeekBar seekVolumn=null;
    private AudioManager amSetVolum=null;

    private Ringtone ringtone;

    private static final int GET_ALERT_RINGTONE = 1001;//링톤 가져오기 값
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_RING);
        setContentView(R.layout.activity_s_3);
        init();
        initControls();






    }

    private void init() {
        SharedPreferencesService.getInstance().load(getApplicationContext());//ShredPreferencesService를 쓰기 위해서 선언
        btSetRapoAlertGoAlert = (Button) findViewById(R.id.bt_set_rapo_alert_go_alert);
        btSetRapoAlertSetRingtone = (Button) findViewById(R.id.bt_set_rapo_alert_set_ringtone);
        amSetRapoAlert = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        btSetRapoAlertGoAlert.setOnClickListener(this);
        btSetRapoAlertSetRingtone.setOnClickListener(this);
        tpSetRapoAlert=(TimePicker) findViewById(R.id.tp_set_rapo_alert);
        soundPicker=(TextView)findViewById(R.id.s_3_soundPick);
        btn_s_3_bell=(Button)findViewById(R.id.btn_s_3_bell);
        seekVolumn = (SeekBar) findViewById(R.id.seekBar);

        tpSetRapoAlert.setTimeChangedListener(this);
        tpSetRapoAlert.setCurrentTimeFormate(TimePicker.HOUR_12);
        tpSetRapoAlert.setAMPMVisible(true);

        ringtone = RingtoneManager.getRingtone(getApplicationContext(), Uri.parse(SharedPreferencesService.getInstance().getPrefStringData(SHARED_RINGTONE)));
        soundPicker.setText(ringtone.getTitle(this));
    }

    private void initControls(){
        try
        {
            seekVolumn = (SeekBar)findViewById(R.id.seekBar);
            amSetVolum = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekVolumn.setMax(amSetVolum
                    .getStreamMaxVolume(AudioManager.STREAM_RING));
            seekVolumn.setProgress(amSetVolum
                    .getStreamVolume(AudioManager.STREAM_RING));


            seekVolumn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    amSetVolum.setStreamVolume(AudioManager.STREAM_RING,
                            progress, 0);
                    SharedPreferencesService.getInstance().setPrefIntData(SHARED_ALERT_VOLUME,progress);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onTimeChanged(int h, int m, int am_pm) {
        Log.e("", "" + h + " " + m + " " + am_pm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_set_rapo_alert_go_alert:
                btnCount=1;
                //finish();
                Calendar mCalendar = new GregorianCalendar();
//                //테스트 코드
//                amSetRapoAlert.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis() + 5000, getPendingIntent());//테스트를 위해 5초 후에 울리도록 알람
//                //mManager.cancel(pendingIntent()); //매 일정 시간 울리던 알람이 울리지 않음


                int mHour = 0;
                int mMinute = 0;
//                //타임피커로 설정하는 정상코드
//                //버전에 따라 다르게 가져옴
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    mHour = tpSetRapoAlert.getHour();
//                    mMinute = tpSetRapoAlert.getMinute();
//                } else {
//                    mHour = tpSetRapoAlert.getCurrentHour();
//                    mMinute = tpSetRapoAlert.getCurrentMinute();
//                }
                SharedPreferencesService.getInstance().setPrefIntData(SHARED_ALERT_HOUR,mHour);
                SharedPreferencesService.getInstance().setPrefIntData(SHARED_ALERT_MINUTE,mMinute);
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                mCalendar.set(Calendar.SECOND, 0);
                amSetRapoAlert.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis()+10000, getPendingIntent());


                finish();

                break;
            case R.id.bt_set_rapo_alert_set_ringtone:
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "알람음 설정");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(SharedPreferencesService.getInstance().getPrefStringData(SHARED_RINGTONE)));
                startActivityForResult(intent, GET_ALERT_RINGTONE);

                break;

            case R.id.btn_s_3_bell:

            default:
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == GET_ALERT_RINGTONE) {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null) {
                SharedPreferencesService.getInstance().setPrefStringData(SHARED_RINGTONE, uri.toString());
                ringtone = RingtoneManager.getRingtone(this, uri);
                soundPicker.setText(ringtone.getTitle(this));
            }else
                SharedPreferencesService.getInstance().setPrefStringData(SHARED_RINGTONE, "");
        }
    }

    //알람의 설정 시각에 발생하는 인텐트 작성
    private PendingIntent getPendingIntent() {
        Intent i = new Intent(getApplicationContext(), StartAlertActivity.class);//일정 시간시 불러오는 액티비티 설정
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;
    }


    @Override
    public void onBackPressed() {
        if(btnCount!=0){
            super.onBackPressed();
        }else{
            Intent i = new Intent(getApplicationContext(), S_2_1Activity.class);
            startActivity(i);
            finish();
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
        actionbar = inflater.inflate(R.layout.layout_custom_actionbar2, null);


        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);


        actionbar.findViewById(R.id.backBtn).setOnClickListener(clickBackBtn);

        actionbar_label=(TextView)actionbar.findViewById(R.id.actionbar2_label);
        actionbar_label.setText(R.string.s_rapoAlert);

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
