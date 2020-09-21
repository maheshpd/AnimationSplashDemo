package com.createsapp.animationsplashdemo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashActivity extends AppCompatActivity {
    //Initialize variable
    ImageView ivTop, ivHear, ivBeat, ivBottom;
    TextView textView;

    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //When runnable run
            //Set text
            textView.setText(charSequence.subSequence(0, index++));
            //Check condition
            if (index <= charSequence.length()) {
                //When index is equal to text length
                //Run handler
                handler.postDelayed(runnable, delay);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Assign variable
        ivTop = findViewById(R.id.iv_top);
        ivHear = findViewById(R.id.iv_hear);
        ivBeat = findViewById(R.id.iv_beat);
        ivBottom = findViewById(R.id.iv_bottom);
        textView = findViewById(R.id.text_view);

        //Set full screen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Initialize top animation
        Animation animation1 = AnimationUtils.loadAnimation(this,
                R.anim.top_wave);

        //Start top animation
        ivTop.setAnimation(animation1);

        //Initialize object animator
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                ivHear,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        //Set duration
        objectAnimator.setDuration(500);
        //Set repeat count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //Set repeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //Start animator
        objectAnimator.start();

        //Set animate text
        animatText("Hear Beat");

        //Load Gif
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/demoapp-ae96a.appspot.com/o/heart_beat.gif?alt=media&token=b21dddd8-782c-457c-babd-f2e922ba172b")
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivBeat);

        //Initialize bottom animation
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_wave);

        //Start bottom animation
        ivBottom.setAnimation(animation2);

        //Initialoze handler
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Redirect to main activity
                startActivity(new Intent(SplashActivity.this,MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                //Finish Activity
                finish();
            }
        },10000);*/
    }

    //Create animated text metjod
    public void animatText(CharSequence cs) {
        //Set text
        charSequence = cs;
        //Ckear index
        index = 0;
        //Clear text
        textView.setText("");
        //Remove call back
        handler.removeCallbacks(runnable);
        //Run handler
        handler.postDelayed(runnable, delay);
    }

}