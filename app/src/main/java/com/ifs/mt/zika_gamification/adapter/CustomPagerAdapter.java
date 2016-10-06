package com.ifs.mt.zika_gamification.adapter;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ifs.mt.zika_gamification.fragmentos.Pergunta1_C;
import com.ifs.mt.zika_gamification.fragmentos.Pergunta2_C;
import com.ifs.mt.zika_gamification.fragmentos.Pergunta3_C;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {


    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //as you see from the below this is how we tell the Viewpager which fragment (page) to be shown//
        Fragment fragment=null;
        if (position==0)
        {
            fragment = new Pergunta1_C();
        }
        if (position==1)
        {
            fragment = new Pergunta2_C();
        }
        if (position==2)
        {
            fragment = new Pergunta3_C();
        }
        return fragment;
    }

    @Override
    public int getCount() {

        return 3;//this is the number of pages
    }
//the below is for the titles//

    @Override
    public CharSequence getPageTitle(int position) {

        if (position==0)
        {
            return "Pergunta 01";
        }
        if (position==1)
        {
            return "Pergunta 02";
        }
        if (position==2)
        {
            return "Pergunta 03";
        }
        return null;
    }
}
