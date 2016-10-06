package com.ifs.mt.zika_gamification.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ifs.mt.zika_gamification.R;

/**
 * Created by Betto Silva on 09/10/2015.
 * Essa classe foi criada para bloquear o swipe do meu ViewPager
 */
public class MyViewPager extends ViewPager {

    private boolean mSwipable = true;

    public MyViewPager(Context context) {

        super(context);

    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyViewPager);
        try {
            //Aqui recupero o valor que foi passado para meu attr no meu style atraves do meu layout com app:swipeable="false"
            //ou seja ele esta setando false para meu mSwipable
            mSwipable = a.getBoolean(R.styleable.MyViewPager_swipeable, true);

        } finally {
            a.recycle();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mSwipable ? super.onInterceptTouchEvent(event) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mSwipable ? super.onTouchEvent(event) : false;
    }

    public boolean isSwipable() {
        return mSwipable;
    }

    public void setSwipable(boolean swipable) {
        mSwipable = swipable;
    }
}