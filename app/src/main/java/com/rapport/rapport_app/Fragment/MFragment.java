package com.rapport.rapport_app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rapport.rapport_app.R;
import com.rapport.rapport_app.S_1Activity;
import com.rapport.rapport_app.Utils.Time;
import com.rapport.rapport_app.m_Activity;
import com.tsengvn.typekit.TypekitContextWrapper;


/**
 * A simple {@link Fragment} subclass.
 */
public class MFragment extends Fragment {
    private View view;

    public static int btnCount=0;

    //레이아웃
    private Button setBtn;

    //Time
    public static Time time = new Time();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view=inflater.inflate(R.layout.fragment_m,container,false);

        setBtn=(Button)view.findViewById(R.id.btn_m_1_set);
        setBtn.setOnClickListener(btnStartClick);

        return view;
    }//endCreateView


    //수면의식 시작버튼 처리
    private View.OnClickListener btnStartClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(btnCount==0){
                Intent i=new Intent(v.getContext(), S_1Activity.class);
                startActivity(i);
                setBtn.setBackgroundResource(R.drawable.m_start_btn);

            }else{
                time.setTime();
                Intent i=new Intent(v.getContext(), m_Activity.class);
                startActivity(i);
            }


        }
    };//end btnStartClick

    @Override
    public void onResume() {
        super.onResume();

    }




}
