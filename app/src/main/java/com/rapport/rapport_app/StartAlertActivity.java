package com.rapport.rapport_app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.rapport.rapport_app.Utils.SharedPreferencesService;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.media.AudioManager.STREAM_ALARM;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ALERT_HOUR;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ALERT_MINUTE;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_ALERT_VOLUME;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_RINGTONE;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_VIBRATOR;
import static com.rapport.rapport_app.Utils.SharedPreferencesService.SHARED_VIBRATOR_SLEEP;

public class StartAlertActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btStartAlertGo, btStartAlertStop;
    private Vibrator vrStartAlert;//진동
    private AlarmManager amStartAlert;//내일 알람 설정을 위한 알람 매니저

    private Ringtone rtStartAlert;//알람
    private long[] lAlertWave;//알람 웨이브(진동했다가 쉬었다가 반복)

    private AudioManager amSetVolum=null;
    private int volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_alert);
        init();
        setNextAlarm();

        SharedPreferencesService.getInstance().setPrefLongData(SHARED_VIBRATOR, 2000);//여기서 설정해도 되지만 아무대나서 해도됨 (밀리초단위)
        SharedPreferencesService.getInstance().setPrefLongData(SHARED_VIBRATOR_SLEEP, 1000);//여기서 설정해도 되지만 아무대나서 해도됨 (밀리초단위)

        lAlertWave = new long[]{SharedPreferencesService.getInstance().getPrefLongData(SHARED_VIBRATOR_SLEEP)
                , SharedPreferencesService.getInstance().getPrefLongData(SHARED_VIBRATOR)};//진동 쉼 / 울림 의 값 (밀리초단위)
        vrStartAlert = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);  // <- or Context.VIBRATE_SERVICE)

        rtStartAlert = RingtoneManager.getRingtone(getApplicationContext(), Uri.parse(SharedPreferencesService.getInstance().getPrefStringData(SHARED_RINGTONE)));//저장되어 있는 알람음 불러오기 및 설정

        volume=SharedPreferencesService.getInstance().getPrefIntData(SHARED_ALERT_VOLUME);
        amSetVolum.setStreamVolume(AudioManager.STREAM_ALARM,
                volume, 0);

        //26버전 이상에는 예외처리
        if (Build.VERSION.SDK_INT >= 26) {
            vrStartAlert.vibrate(VibrationEffect.createWaveform(lAlertWave, -1)); // 뒤에 상수 0은 진동 무한 / -1 은 1회
        } else {
            vrStartAlert.vibrate(lAlertWave, 0);
        }
        //화면이 잠겨있을 때 열기기
        PowerManager mPowerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = mPowerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();


        //롤리팝 이상에는 예외 처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            rtStartAlert.setAudioAttributes(audioAttributes);
        } else {
            rtStartAlert.setStreamType(STREAM_ALARM);//무음에서도 알람이 울릴 수 있도록.
        }

        rtStartAlert.play();
    }

    private void init() {
        SharedPreferencesService.getInstance().load(getApplicationContext());//ShredPreferencesService를 쓰기 위해서 선언

        btStartAlertGo = (Button) findViewById(R.id.bt_start_alert_go);
        btStartAlertStop = (Button) findViewById(R.id.bt_start_alert_stop);
        btStartAlertGo.setOnClickListener(this);
        btStartAlertStop.setOnClickListener(this);
        amStartAlert = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        amSetVolum = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


    }

    private void setNextAlarm() {
        //정상 코드
        Calendar mCalendar = new GregorianCalendar();
//        mCalendar.set(Calendar.DAY_OF_MONTH,mCalendar.get(Calendar.DAY_OF_MONTH)+1);
//        mCalendar.set(Calendar.HOUR_OF_DAY,SharedPreferencesService.getInstance().getPrefIntData(SHARED_ALERT_HOUR));
//        mCalendar.set(Calendar.MINUTE,SharedPreferencesService.getInstance().getPrefIntData(SHARED_ALERT_MINUTE));
//        amStartAlert.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis() + 5000, getPendingIntent());//테스트를 위해 5초 후에 울리도록 알람

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start_alert_go:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                rtStartAlert.stop();
                vrStartAlert.cancel();
                finish();
                break;
            case R.id.bt_start_alert_stop:
                rtStartAlert.stop();
                vrStartAlert.cancel();
                finish();
                break;
            default:
                break;

        }
    }

    //알람의 설정 시각에 발생하는 인텐트 작성
    private PendingIntent getPendingIntent() {
        Intent i = new Intent(getApplicationContext(), StartAlertActivity.class);//일정 시간시 불러오는 액티비티 설정
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        return pi;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rtStartAlert.stop();
        vrStartAlert.cancel();

    }
}
