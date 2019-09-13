package com.android.animequo.Fragments;

import android.animation.Animator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.animequo.Activities.Feed;
import com.android.animequo.R;

public class Category_fragment extends Fragment {
    public static final String TAG="Category_Fragment";
  public interface OnSendDataHost{
        void set_animation_copied_layer(boolean clicked);
    }
    public OnSendDataHost mOnSendDataHost;



  //TODO: Init the Lottie Animation :
    com.varunest.sparkbutton.SparkButton spark_button;
    public boolean clicked=false;
    TextView text_item;
    CardView item;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_category_fragment , container,false);

        //decalre the var :

        spark_button=(com.varunest.sparkbutton.SparkButton)view.findViewById(R.id.spark_button);

        item=(CardView) view.findViewById(R.id.card_view);
        text_item=(TextView)view.findViewById(R.id.text_item_card);



        //init Listener:
        init_listener();
        //init animation:
        init_like_animation();
        return  view ;

    }

    private void init_listener() {



    }

    private void init_like_animation() {

        item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                clicked=!clicked;
                mOnSendDataHost.set_animation_copied_layer(clicked);

                //TODO: copy the Text :
                CopyToClipboard(text_item.getText().toString());
                return false;
            }
        });
        text_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                clicked=!clicked;
                mOnSendDataHost.set_animation_copied_layer(clicked);

                //TODO: copy the Text :
                CopyToClipboard(text_item.getText().toString());
                return false;

            }
        });
    }


    public void CopyToClipboard(String copyText) {

        ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("url", copyText);
        clipboard.setPrimaryClip(clip);
        //Toast toast = Toast.makeText(getContext(), "Link is copied", Toast.LENGTH_SHORT);
        //toast.show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnSendDataHost=(OnSendDataHost)getActivity();
        }catch (ClassCastException e){
            Log.d(TAG, "onAttach: ");
        }
    }
}
