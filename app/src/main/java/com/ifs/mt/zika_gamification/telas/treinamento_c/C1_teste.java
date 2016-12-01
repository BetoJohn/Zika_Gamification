package com.ifs.mt.zika_gamification.telas.treinamento_c;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.CustomPagerAdapter;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_c.Pergunta1_C;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_c.Pergunta2_C;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_c.Pergunta3_C;

public class C1_teste extends AppCompatActivity {
    ViewPager mViewPager;
    private Toolbar tb;
  //  TabsAdapter mTabsAdapter;

    String TabFragmentB;

    public void setTabFragmentB(String t){
        TabFragmentB = t;
    }

    public String getTabFragmentB(){
        return TabFragmentB;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);
*/
  /*      tb = (Toolbar)findViewById(R.id.tb);
        setSupportActionBar(tb);
*/
 //       final ActionBar bar = getActionBar();
//        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        //mTabsAdapter = new TabsAdapter(this, mViewPager);


        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager);
        setContentView(mViewPager);
      //  mViewPager = (ViewPager) findViewById(R.id.pager);
        // mCustomPagerAdapter = new CustomPagerAdapter(getFragmentManager(), this);

        //the connection between the ViewPager and the adapter is below//
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new CustomPagerAdapter(fragmentManager));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }

   /* public static class TabsAdapter extends FragmentPagerAdapter
            {

        private final Context mContext;
       // private final ActionBar mActionBar;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<>();

        static final class TabInfo {
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(Class<?> _class, Bundle _args) {
                clss = _class;
                args = _args;
            }
        }

        public TabsAdapter(AppCompatActivity activity, ViewPager pager) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
         //   mActionBar = activity.getActionBar();
            mViewPager = pager;
            mViewPager.setAdapter(this);
          //  mViewPager.setOnPageChangeListener(this);
        }

       *//* public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
            TabInfo info = new TabInfo(clss, args);
            tab.setTag(info);
            tab.setTabListener(this);
            mTabs.add(info);
            mActionBar.addTab(tab);
            notifyDataSetChanged();
        }*//*
*//*
        @Override
        public void onPageScrollStateChanged(int state) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
         //   mActionBar.setSelectedNavigationItem(position);
        }*//*
*//*
        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            Object tag = tab.getTag();
            for (int i=0; i<mTabs.size(); i++) {
                if (mTabs.get(i) == tag) {
                    mViewPager.setCurrentItem(i);
                }
            }
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }*//*

        @Override
        public Fragment getItem(int position) {
            TabInfo info = mTabs.get(position);
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
           // return fragment;
            return fragment.instantiate(mContext, info.clss.getName(), info.args);
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

    }*/


}
