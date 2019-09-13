package com.android.animequo.Activities;


import android.animation.Animator;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.android.animequo.Fragments.About_fragment;
import com.android.animequo.Fragments.Bookmark_fragment;
import com.android.animequo.Fragments.Category_fragment;
import com.android.animequo.Fragments.Trending_fragment;
import com.android.animequo.Logs.Sign_in;
import com.android.animequo.R;
import com.android.animequo.Utils.SectionsPagerAdapter;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Feed extends AppCompatActivity implements
        Category_fragment.OnFragmentInteractionListener,Trending_fragment.OnFragmentInteractionListener,
        Bookmark_fragment.OnFragmentInteractionListener,About_fragment.OnFragmentInteractionListener , Category_fragment.OnSendDataHost {


    private static final String TAG = "Feed Activity";
    com.airbnb.lottie.LottieAnimationView menu_animate, search_animate,check_icon_animation,copied_animation , checkicon_search_animation;
    private TextView mini_logo;
    public RelativeLayout bottom_Nav_bar,layer_category_tag,layer_search,coiped_layer;
    boolean visible,visible1;
    public TextView text_selcted;
    private EditText search_input;
    android.support.v7.widget.Toolbar toolsbar;
    private int select_nbr;
    private boolean pressed=false;
     RadioGroup category_radio_group1,category_radio_group2;
    private RadioButton love_tag,motivition_tag,funny_tag,regrets_tag,philo_tag,dark_tag,death_tag;
    private ArrayList<String> category_list ;



    Animation animte,animte1,animte_down;

    private TabLayout mTabLayout;
    public ViewPager mViewPager;
    public SectionsPagerAdapter mSectionsPageAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        menu_animate = (com.airbnb.lottie.LottieAnimationView) findViewById(R.id.menu_animate);
        search_animate = (com.airbnb.lottie.LottieAnimationView) findViewById(R.id.search_animate);
        check_icon_animation=(com.airbnb.lottie.LottieAnimationView)findViewById(R.id.check_icon_animation);
        copied_animation=(com.airbnb.lottie.LottieAnimationView)findViewById(R.id.copied_animation);
        checkicon_search_animation=(com.airbnb.lottie.LottieAnimationView)findViewById(R.id.checkicon_search_animation);


        //add the animaton for the slide_up animation with manging the visviblete (Visible):
        animte= AnimationUtils.loadAnimation(this,R.anim.fade_animation);
        animte1=AnimationUtils.loadAnimation(this,R.anim.bottom_fade_animation);
        //add the animaton for the slide_down animation with manging the visviblete (Invisible):
        animte_down=AnimationUtils.loadAnimation(this,R.anim.slide_down);





        search_input=(EditText)findViewById(R.id.search_input);
        mini_logo=(TextView)findViewById(R.id.mini_logo);

        category_radio_group1 = (RadioGroup) findViewById(R.id.category_radio_group1);
        category_radio_group2=(RadioGroup)findViewById(R.id.category_radio_group2);

        //Selected Textview RadioGroup:
        text_selcted=(TextView)findViewById(R.id.text_selcted);
        text_selcted.setText("Select");

        //checkbox list of the catgegory tag;
        dark_tag=(RadioButton)findViewById(R.id.dark_tag);
        love_tag=(RadioButton) findViewById(R.id.love_tag);
        motivition_tag=(RadioButton)findViewById(R.id.motivition_tag);
        death_tag=(RadioButton)findViewById(R.id.death_tag);
        regrets_tag=(RadioButton)findViewById(R.id.regrets_tag);
        philo_tag=(RadioButton)findViewById(R.id.philo_tag);
        funny_tag=(RadioButton)findViewById(R.id.funny_tag);


        //relative layout for the Tablayout:
        bottom_Nav_bar=(RelativeLayout)findViewById(R.id.bottom_Nav_bar);
        layer_search=(RelativeLayout)findViewById(R.id.layer_search);

        //relative layout for the category bottom  pop:
        layer_category_tag = (RelativeLayout) findViewById(R.id.layer_category_tag);
        coiped_layer=(RelativeLayout)findViewById(R.id.coiped_layer);
        toolsbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);


        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);


        //bottom_Nav_bar.startAnimation(animte);
        //layer_category_tag.startAnimation(animte);



        init_tablayout();
        set_animation_toolbar();
        init_category_list();



    }



    @Override
    public void set_animation_copied_layer(boolean clicked) {
        if (clicked == true) {
            if (coiped_layer.getVisibility() == View.INVISIBLE) {
                if ((bottom_Nav_bar.getVisibility() == View.VISIBLE) || (layer_category_tag.getVisibility() == View.VISIBLE) && (layer_search.getVisibility() == View.VISIBLE)) {

                    //TODO: Apply the down animation :
                    bottom_Nav_bar.setVisibility(View.INVISIBLE);
                    layer_search.setVisibility(View.INVISIBLE);
                    layer_category_tag.setVisibility(View.INVISIBLE);
                    //TODO : Add the animation for the Relative layer :
                    coiped_layer.setAnimation(animte1);
                    coiped_layer.setVisibility(View.VISIBLE);
                    copied_animation.playAnimation();
                    TimerCloseLayer_copied();


                }
                else{
                    //TODO : Add the animation for the Relative layer :
                    coiped_layer.setAnimation(animte1);
                    coiped_layer.setVisibility(View.VISIBLE);
                    copied_animation.playAnimation();
                    TimerCloseLayer_copied();

                }
            }
            else {
                //TODO : Add the animation for the Relative layer :
                //coiped_layer.startAnimation(animte_down);
                coiped_layer.setVisibility(View.INVISIBLE);
            }

        }
        //else Maybe missing there :



    }



    private void TimerCloseLayer_copied() {
        //close the layer pop after 5 Seconds:
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    //TODO: add the fade out animation :
                    coiped_layer.setVisibility(View.INVISIBLE);
                    //copied_animation.startAnimation(animte_down);
                    //bottom_Nav_bar.startAnimation(animte);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        },5000);



    }



    private void set_animation_toolbar() {

        menu_animate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                menu_animate.playAnimation();
                //Animate View Group with fade animtion:
                //TransitionManager.beginDelayedTransition(bottom_Nav_bar);
            menu_animate.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                        //Nothing
                }

                @Override
                public void onAnimationEnd(Animator animator) {


                if  (bottom_Nav_bar.getVisibility()==View.INVISIBLE){
                    //bottom_Nav_bar.setVisibility(View.VISIBLE);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);
                    //bottom_Nav_bar.startAnimation(animte);


                    layer_category_tag.setVisibility(View.INVISIBLE);
                    layer_search.setVisibility(View.INVISIBLE);
                }else
                {
                    bottom_Nav_bar.setVisibility(View.INVISIBLE);
                    //bottom_Nav_bar.startAnimation(animte_down);
                }



                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });



            }
        });



        search_animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play the Animation
                search_animate.playAnimation();
                //TODO: Animate the saerch layer :
                init_search_input();

                checkicon_search_animation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //init the parms of the animation :
                        checkicon_search_animation.setBackground(null);
                        checkicon_search_animation.playAnimation();
                        checkicon_search_animation.addAnimatorListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                //TODO: Set The layer of the category invisble :

                                layer_search.setVisibility(View.INVISIBLE);
                                //layer_search.startAnimation(animte1);

                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });

                        init_layer_category();


                    }
                });



            }
        });



    }

    private void init_layer_category() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    //TODO: add the fade bootom animation :
                    //TODO : change the Visiblete animation into xml animation :
                    layer_category_tag.setVisibility(View.INVISIBLE);
                    //layer_category_tag.startAnimation(animte_down);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);
                    //bottom_Nav_bar.startAnimation(animte);
                    //TODO: send the Filter params into the API :


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        },4000);

    }



    private void init_search_input() {
        //TODO add fade animation :*
        if (layer_search.getVisibility() == View.INVISIBLE)
        {
            if ((bottom_Nav_bar.getVisibility() == View.VISIBLE) || (layer_category_tag.getVisibility() == View.VISIBLE)) {
                bottom_Nav_bar.setVisibility(View.INVISIBLE);
                layer_category_tag.setVisibility(View.INVISIBLE);
                //layer_search.startAnimation(animte1);
                layer_search.setVisibility(View.VISIBLE);

            } else {
                //layer_search.startAnimation(animte1);
                layer_search.setVisibility(View.VISIBLE);

            }
        }
        else {
            layer_search.setVisibility(View.INVISIBLE);
            //layer_search.startAnimation(animte_down);
            bottom_Nav_bar.setVisibility(View.VISIBLE);
            //bottom_Nav_bar.startAnimation(animte);
        }


        checkicon_search_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //init the parms of the animation :
                checkicon_search_animation.setBackground(null);
                checkicon_search_animation.playAnimation();
                checkicon_search_animation.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        //TODO: Set The layer of the category invisble :
                        //layer_search.startAnimation(animte_down);
                        layer_search.setVisibility(View.INVISIBLE);
                        layer_category_tag.setVisibility(View.INVISIBLE);
                        bottom_Nav_bar.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                init_layer_category();


            }
        });


    }

    private void init_tablayout() {

        mSectionsPageAdapter=new SectionsPagerAdapter(getSupportFragmentManager());


        ImageView imgView= new ImageView(Feed.this);
        imgView.setImageResource(R.drawable.anim_tab_trending);
        imgView.setPadding(5,5,5,5);
        mTabLayout.getTabAt(0).setCustomView(imgView);

        ImageView imgView1= new ImageView(Feed.this);
        imgView1.setImageResource(R.drawable.anim_tab_category);
        imgView1.setPadding(5,5,5,5);
        mTabLayout.getTabAt(1).setCustomView(imgView1);

        ImageView imgView2= new ImageView(Feed.this);
        imgView2.setImageResource(R.drawable.anim_tab_bookmark);
        imgView2.setPadding(5,5,5,5);
        mTabLayout.getTabAt(2).setCustomView(imgView2);

        ImageView imgView3= new ImageView(Feed.this);
        imgView3.setImageResource(R.drawable.anim_tab_about);
        imgView3.setPadding(5,5,5,5);
        mTabLayout.getTabAt(3).setCustomView(imgView3);


        //open the tab layout with Feed Tab in defoult  fragment in opening :
        //mTabLayout.getTabAt(1).select();






        LinearLayout tabStrip = (LinearLayout) mTabLayout.getChildAt(0);
        tabStrip.getChildAt(1).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {




                visible1 = !visible1;
                if (visible1){

                    //bottom_Nav_bar.startAnimation(animte_down);
                    bottom_Nav_bar.setVisibility(View.INVISIBLE);

                    //layer_category_tag.startAnimation(animte1);
                    layer_category_tag.setVisibility(View.VISIBLE);


                } else {
                    //bottom_Nav_bar.startAnimation(animte);
                    bottom_Nav_bar.setVisibility(View.VISIBLE);

                    //layer_category_tag.startAnimation(animte_down);
                    layer_category_tag.setVisibility(View.INVISIBLE);

                }

                check_icon_animation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        check_icon_animation.setBackground(null);
                        check_icon_animation.playAnimation();
                        check_icon_animation.addAnimatorListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                //set the catgeorylayer invisible and set the tabyout visble :
                                layer_category_tag.setVisibility(View.INVISIBLE);
                                //layer_category_tag.startAnimation(animte_down);
                                bottom_Nav_bar.setVisibility(View.VISIBLE);
                                //bottom_Nav_bar.startAnimation(animte);
                                //set the Text into the defoult ;
                                text_selcted.setText("Select");
                                //TODO: set the 2 RadioGroup into the nocheck items:
                                category_radio_group1.clearCheck();
                                category_radio_group2.clearCheck();


                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }
                });

                return false;
            }
        });





    }





    private void setupViewPager(ViewPager mViewPager) {

        SectionsPagerAdapter Adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //Adding Thes Fragments to the TabLayout:
        //there is an extra fragment it is Feed_fragment from the tutoriels:
        Adapter.addFragment(new Trending_fragment(),"");
        Adapter.addFragment(new Category_fragment(),"");
        Adapter.addFragment(new Bookmark_fragment(),"");
        Adapter.addFragment(new About_fragment(),"");
        mViewPager.setAdapter(Adapter);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void init_category_list() {
        text_selcted.setText("Select");
        //declare the radio Group:
        category_radio_group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                setuncheck("category_radio_group2");
                text_selcted.setText("Select 1");
                String s="";
                switch (checkedId) {
                    case R.id.death_tag:
                        //showToast("personnel");
                        getCategory("death");
                        break;
                    case R.id.love_tag:
                        //showToast("work");
                        getCategory("love");
                        break;
                    case R.id.motivition_tag:
                        //showToast("study");
                        getCategory("motivition");
                        break;
                    case R.id.funny_tag:
                        //showToast("meeting");
                        getCategory("funny");
                        break;

                }


            }
        });


        category_radio_group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                setuncheck("category_radio_group1");
                text_selcted.setText("Select 1");
                switch (i) {
                    case R.id.regrets_tag:
                        //catergoy shopping selected:
                        //showToast("regrets");
                        getCategory("regrets");


                        break;

                    case R.id.philo_tag:
                        //showToast("philo");
                        getCategory("philo");


                        break;
                    case R.id.dark_tag:
                        //showToast("dark");
                        getCategory("dark");


                        break;
                }




            }
        });

    }

    private void setuncheck(String rd){
        if (rd.equals("category_radio_group1")){


            death_tag.setChecked(false);
            love_tag.setChecked(false);
            motivition_tag.setChecked(false);
            funny_tag.setChecked(false);


        }
       else {

            regrets_tag.setChecked(false);
            philo_tag.setChecked(false);
            dark_tag.setChecked(false);


        }
    }

    private void showToast(String ms_toast){
        //TODO: show toast here:
        MDToast mdToast = MDToast.makeText(getApplicationContext(), "You Select Category " + ms_toast, Toast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
        mdToast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
        mdToast.show();
    }

    private void getCategory(String msg){
        //Save The Selected category:
        ArrayList<String> catgeory_flag=new ArrayList<>();
        catgeory_flag.add(msg);
        //get the value of arraylist : category_flag.get(0) :
        //showToast(catgeory_flag.get(0));

    }



}