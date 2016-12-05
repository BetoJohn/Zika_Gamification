package com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.FragmentCustomPagerAdapter;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P1M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P2M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P3M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P4M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P5M1E1;

import java.util.ArrayList;
import java.util.List;

public class M1E1 extends AppCompatActivity {
    private Toolbar tb;
    private ViewPager mViewPager;
    private String TagFragmentP2;
    List<Fragment> fragments;

    public void setTagFragmentP2(String t) {
        TagFragmentP2 = t;
    }

    public String getTagFragmentP2() {
        return TagFragmentP2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_e1);

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new P1M1E1());
        fragments.add(new P2M1E1());
        fragments.add(new P3M1E1());
        fragments.add(new P4M1E1());
        fragments.add(new P5M1E1());
        mViewPager.setAdapter(new FragmentCustomPagerAdapter(fragmentManager, fragments));
    }

    public void trocarPagina(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_c1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
