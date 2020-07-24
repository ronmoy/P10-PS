package com.myapplicationdev.android.p10ps;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag1 extends Fragment
{
    TextView tv1;
    Button btnChangeColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);

        tv1 = view.findViewById(R.id.tv1);
        btnChangeColor =  view.findViewById(R.id.button2);

        btnChangeColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Random random = new Random();
                int color = Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
                .setBackgroundColor(color);
            }
        });

        return view;
    }
