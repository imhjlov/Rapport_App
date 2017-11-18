package com.rapport.rapport_app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rapport.rapport_app.F_1Activity;
import com.rapport.rapport_app.F_1_Activity;
import com.rapport.rapport_app.F_7Activity;
import com.rapport.rapport_app.F_7_Activity;
import com.rapport.rapport_app.R;
import com.rapport.rapport_app.S_1Activity;
import com.rapport.rapport_app.S_2_1Activity;
import com.rapport.rapport_app.S_3Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FFragment extends Fragment {

    private View view;

    public FFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_f, container, false);

        view.findViewById(R.id.btn_feedback_sel_7day_standard_feedback).setOnClickListener(F_7_Click);
        view.findViewById(R.id.btn_feedback_sel_today_feedback).setOnClickListener(F_1_Click);

        return view;
    }//endCreateView


    //7일 기준점 피드백 버튼 처리
    private View.OnClickListener F_7_Click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //기준점 유무 확인

            //페이지이동
            Intent i=new Intent(v.getContext(), F_7_Activity.class);
            //Intent i=new Intent(v.getContext(), F_7Activity.class);
            startActivity(i);
        }
    };//btn7dayStandardFeedbackClick


    //오늘 피드백 버튼 처리
    private View.OnClickListener F_1_Click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //오늘 했는지 여부확인

            //페이지이동
            Intent i=new Intent(v.getContext(), F_1_Activity.class);
            //Intent i=new Intent(v.getContext(), F_1Activity.class);
            startActivity(i);

        }
    };//btnTodayFeedbackClick


}
