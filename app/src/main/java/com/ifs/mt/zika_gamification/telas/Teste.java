package com.ifs.mt.zika_gamification.telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.ModuloRecyclerViewAdapter;

public class Teste extends AppCompatActivity {

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        /*MediaController mc= new MediaController(this);
        VideoView view = (VideoView)findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/raw/m1e1p5";// + R.raw.m1e1p5;
        System.out.println("Path: "+path);
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);
        //view.start();*/


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        gaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        ModuloRecyclerViewAdapter rcAdapter = new ModuloRecyclerViewAdapter(Teste.this);
        recyclerView.setAdapter(rcAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teste, menu);
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
