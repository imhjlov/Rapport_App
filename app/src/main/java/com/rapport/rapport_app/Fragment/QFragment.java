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

import com.rapport.rapport_app.I_1Activity;
import com.rapport.rapport_app.I_2Activity;
import com.rapport.rapport_app.I_3Activity;
import com.rapport.rapport_app.I_4Activity;
import com.rapport.rapport_app.P_1Activity;
import com.rapport.rapport_app.P_2Activity;
import com.rapport.rapport_app.P_3Activity;
import com.rapport.rapport_app.P_4Activity;
import com.rapport.rapport_app.R;
import com.rapport.rapport_app.S_1Activity;
import com.rapport.rapport_app.S_2_1Activity;
import com.rapport.rapport_app.S_3Activity;


/**
 * A simple {@link Fragment} subclass.
 */
public class QFragment extends Fragment {

    private View view;

    public QFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_q, container, false);

        view.findViewById(R.id.q_i1_btn).setOnClickListener(show_i_1);
        view.findViewById(R.id.q_i2_btn).setOnClickListener(show_i_2);
        view.findViewById(R.id.q_i3_btn).setOnClickListener(show_i_3);
        view.findViewById(R.id.q_i4_btn).setOnClickListener(show_i_4);

        return view;
    }

    private View.OnClickListener show_i_1 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), I_1Activity.class);
            startActivity(i1);

        }
    };

    private View.OnClickListener show_i_2 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i2=new Intent(view.getContext(), I_2Activity.class);
            startActivity(i2);

        }
    };

    private View.OnClickListener show_i_3 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i3=new Intent(view.getContext(), I_3Activity.class);
            startActivity(i3);

        }
    };

    private View.OnClickListener show_i_4 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i4=new Intent(view.getContext(), I_4Activity.class);
            startActivity(i4);

        }
    };


}
