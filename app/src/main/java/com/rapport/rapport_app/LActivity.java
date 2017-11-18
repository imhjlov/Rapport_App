package com.rapport.rapport_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * 로고 표시 및 기타 작업을 수행하는 액티비티
 */
public class LActivity extends AppCompatActivity {
    // 인트로 화면 대기시간
    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);

        // 인트로 화면 대기시간 이후에, 메인 액티비티로 이동한다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
