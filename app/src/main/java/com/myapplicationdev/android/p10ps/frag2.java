package com.myapplicationdev.android.p10ps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag2 extends Fragment {

    ImageView iv;

    public frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);

        iv = view.findViewById(R.id.imageView);

        String imageUrl = "https://www.adweek.com/wp-content/uploads/2020/04/AI-Meme-PAGE-2020.jpg";
        Picasso.with(this.getContext()).load(imageUrl).into(iv);

        return view;
    }
}
