package com.rapport.rapport_app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rapport.rapport_app.P_1Activity;
import com.rapport.rapport_app.P_2Activity;
import com.rapport.rapport_app.P_3Activity;
import com.rapport.rapport_app.P_4Activity;
import com.rapport.rapport_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {

    private View view;

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a, container, false);

        view.findViewById(R.id.a_dry_btn).setOnClickListener(show_p_dry);
        view.findViewById(R.id.a_cleaner_btn).setOnClickListener(show_p_cleaner);
        view.findViewById(R.id.a_shi_btn).setOnClickListener(show_p_shi);
        view.findViewById(R.id.a_heart_btn).setOnClickListener(show_p_heart);



        return view;
    }


    private View.OnClickListener show_p_dry = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i1=new Intent(view.getContext(), P_1Activity.class);
            startActivity(i1);

        }
    };

    private View.OnClickListener show_p_cleaner = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i2=new Intent(view.getContext(), P_2Activity.class);
            startActivity(i2);

        }
    };

    private View.OnClickListener show_p_shi = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i3=new Intent(view.getContext(), P_3Activity.class);
            startActivity(i3);

        }
    };

    private View.OnClickListener show_p_heart = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent i4=new Intent(view.getContext(), P_4Activity.class);
            startActivity(i4);

        }
    };


}
