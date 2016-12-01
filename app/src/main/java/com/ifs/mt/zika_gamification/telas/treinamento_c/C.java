package com.ifs.mt.zika_gamification.telas.treinamento_c;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.CustomPagerAdapter;

public class C extends AppCompatActivity {
    private Toolbar tb;
    PagerTitleStrip pagerTitleStrip;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        tb = (Toolbar)findViewById(R.id.tb);
        setSupportActionBar(tb);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        // mCustomPagerAdapter = new CustomPagerAdapter(getFragmentManager(), this);

        //the connection between the ViewPager and the adapter is below//
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new CustomPagerAdapter(fragmentManager));
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
