package com.example.list_animation_flow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DetailsActivity extends AppCompatActivity {

    public static final String COLOR = "COLOR";
    public static final String CURVE = "CURVE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Circle match the launch activity
        View circle = findViewById(R.id.Circle);
        circle.setBackgroundTintList(ColorStateList.valueOf(getIntent().getIntExtra(COLOR, 0xffff00ff)));

        //Check the motion method
        boolean curve = getIntent().getBooleanExtra(CURVE, false);
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(curve ?
                R.transition.curve : R.transition.move));
    }
}
