package com.android.animequo.Logs;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.animequo.Activities.Feed;
import com.android.animequo.R;

public class Sign_in extends AppCompatActivity {

    private TextView Sign_in_btn;
    Animation animte_fade,animte_slide_fade;
    EditText password,username;
    TextView illustration,new_account,password_forgot;
    RelativeLayout layout2,layout3;
    ConstraintLayout bg_pager;
    private int pager=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Sign_in_btn=(TextView)findViewById(R.id.Sign_in_btn);
        password=(EditText)findViewById(R.id.password);
        username=(EditText)findViewById(R.id.username);
        password_forgot=(TextView)findViewById(R.id.password_forgot);
        illustration=(TextView)findViewById(R.id.illustration);
        new_account=(TextView)findViewById(R.id.new_account);
        layout2=(RelativeLayout)findViewById(R.id.layout2);
        layout3=(RelativeLayout)findViewById(R.id.layout3);
        bg_pager=(ConstraintLayout)findViewById(R.id.bg_pager);






        //TODO:declare the Animation :
        animte_fade= AnimationUtils.loadAnimation(this,R.anim.fade_animation);
        animte_slide_fade=AnimationUtils.loadAnimation(this,R.anim.slide_fade_animation);
        //TODO: set the Animation :
        password.startAnimation(animte_slide_fade);
        username.startAnimation(animte_slide_fade);
        illustration.startAnimation(animte_fade);


        Event();






    }
    private void Event()
    {
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i =new Intent(getApplicationContext(),Sign_up.class);
                 //   startActivity(i);
                //TODO: manage the same layout but with changing the content :
                illustration.setBackgroundResource(R.drawable.ic_illustartion_sign_up);
                illustration.startAnimation(animte_fade);
                layout2.setVisibility(View.INVISIBLE);
                password.setVisibility(View.INVISIBLE);
                password_forgot.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.VISIBLE);
                username.setHint(R.string.Email);
                layout2.startAnimation(animte_slide_fade);
                username.setText("");
                password.setText("");
                Sign_in_btn.setBackgroundResource(R.drawable.ic_next_btn);
                pager=1;

            }
        });

        Sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((pager==0)||(pager==3)) {
                    //TODO: Create new Intent :
                    Intent i = new Intent(Sign_in.this, Feed.class);
                    //TODO: open the Intent :
                    startActivity(i);
                    //TODO: Animate the Intent :
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_down);
                }
                else if ((username.getText().toString().isEmpty()==false)&& (pager==1))
                {
                    illustration.setBackgroundResource(R.drawable.ic_illustartion_sign_up_1);
                    illustration.startAnimation(animte_fade);
                    layout2.setVisibility(View.INVISIBLE);
                    //password.setVisibility(View.INVISIBLE);
                    //password_forgot.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    username.setHint(R.string.username);
                    layout2.startAnimation(animte_slide_fade);
                    username.setText("");
                    pager=2;

                }
                else if ((username.getText().toString().isEmpty()==false)&& (pager==2))
                {
                    //bg_pager.setBackgroundResource(R.drawable.ic_bg_sign_up_pager_3);
                    illustration.setBackgroundResource(R.drawable.ic_illustartion_sign_up_2);
                    illustration.startAnimation(animte_fade);
                    layout2.setVisibility(View.INVISIBLE);
                    //password.setVisibility(View.INVISIBLE);
                    //password_forgot.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    username.setHint(R.string.passwrod);
                    username.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    layout2.startAnimation(animte_slide_fade);
                    Sign_in_btn.setBackgroundResource(R.drawable.ic_welcome_btn);

                    //TODO: update the margin of the ViewGroup of the illustartion :

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    // Set TextView layout margin 25 pixels to all side
                    // Left Top Right Bottom Margin
                    params.setMargins(0,0,0,25);

                    // Apply the updated layout parameters to TextView
                    illustration.setLayoutParams(params);
                    //TODO: update the margin of the ViewGroup of the illustartion :


                    username.setText("");
                    pager=3;
                }
            }
        });


    }
}
