package com.android.animequo.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.android.animequo.Logs.Sign_in;
import com.android.animequo.R;

public class MainActivity extends AppCompatActivity {

    //LOGO
    private static final String LOG="Splash Screen ";
    private final int Splash_Screen_length=6000;

    TextView logo_app,text_description;

    Animation animte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo_app=(TextView)findViewById(R.id.logo_app);
        text_description=(TextView)findViewById(R.id.text_description);


        //TODO:declare the Animation :
        animte= AnimationUtils.loadAnimation(this,R.anim.fade_animation);
        //TODO: set the Animation :
        logo_app.startAnimation(animte);
        text_description.startAnimation(animte);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    Intent MainIntent = new Intent(MainActivity.this, Sign_in.class);
                    // the MainActivity is the Main page of the appliaction
                    MainActivity.this.startActivity(MainIntent);
                    MainActivity.this.finish();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        },Splash_Screen_length);


    }
}
