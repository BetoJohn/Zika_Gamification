package com.ifs.mt.zika_gamification.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.Pergunta1_C;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.Pergunta2_C;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.Pergunta3_C;

import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class FragmentCustomPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public FragmentCustomPagerAdapter(FragmentManager fm,  List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        //as you see from the below this is how we tell the Viewpager which fragment (page) to be shown//
        Fragment fragment = fragments.get(position);
        /*if (position == 0) {
            fragment = fragments.get(0);
        }
        if (position == 1) {
            fragment = fragments.get(1);
        }
        if (position == 2) {
            fragment = fragments.get(2);
        }
        if (position == 3) {
            fragment = fragments.get(3);
        }
        if (position == 4) {
            fragment = fragments.get(4);
        }*/
        return fragment;
    }

    @Override
    public int getCount() {

        return fragments.size();//this is the number of pages
    }
//the below is for the titles//

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Pergunta 01";
        }
        if (position == 1) {
            return "Pergunta 02";
        }
        if (position == 2) {
            return "Pergunta 03";
        }
        if (position == 3) {
            return "Pergunta 04";
        }
        if (position == 4) {
            return "Pergunta 05";
        }
        return null;
    }
}
