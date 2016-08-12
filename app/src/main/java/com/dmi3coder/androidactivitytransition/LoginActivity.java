package com.dmi3coder.androidactivitytransition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    private Button view;
    private View circle;
    private ImageView image;
    boolean color = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view = (Button) findViewById(R.id.button);
        view.setTransitionName("view");
        image = (ImageView) findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.animated_vector);
        circle = findViewById(R.id.circle);
        circle.setVisibility(View.INVISIBLE);
        Animatable animation = (Animatable)image.getBackground();
        animation.start();
        new SomeTask().execute();


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        circle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Animatable animation = (Animatable)image.getBackground();
                animation.start();
                return true;
            }
        });
    }

    public static Animator createRevealWithDelay(View view, int centerX, int centerY, float startRadius, float endRadius) {

        // get the center for the clipping circle
        int cx = view.getMeasuredWidth() / 2;
        int cy = view.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0, endRadius);

        // make the view visible and start the animation
        anim.setDuration(1000);
        view.setVisibility(View.VISIBLE);
        anim.start();
        return anim;
    }

    class SomeTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            float finalRadius = (float) Math.hypot(view.getRootView().getWidth(), view.getRootView().getHeight());
            createRevealWithDelay(circle, ((int) (image.getX()+image.getWidth()/2)), ((int) (image.getY() + image.getHeight()/2)) ,0,finalRadius);
        }

    }
}
