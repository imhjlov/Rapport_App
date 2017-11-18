package com.rapport.rapport_app.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.rapport.rapport_app.R;

public class MPDialog extends Dialog {

    //레이아웃
    private Button m_CloseBtn;

    private View.OnClickListener m_CloseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        setContentView(R.layout.activity_mp_dialog);

        m_CloseBtn=(Button)findViewById(R.id.bt_mp_dialog_close);

        //클릭이벤트 셋팅
        if(m_CloseListener!=null){
            m_CloseBtn.setOnClickListener(m_CloseListener);
        }


    }

    public MPDialog(Context context, View.OnClickListener closeListener){
        super(context,android.R.style.Theme_NoTitleBar);
        this.m_CloseListener = closeListener;
    }


}
