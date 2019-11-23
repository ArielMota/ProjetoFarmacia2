package com.example.projetofarmacia2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    ImageView sub;
    Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity( new Intent(getBaseContext(), MainActivity.class));
                finish();

            }
        }, 3500);



        /*sub = (ImageView) findViewById(R.id.bemvindo);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombotton);
        sub.setAnimation(frombottom);*/
    }
}
