package com.ifs.mt.zika_gamification.telas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.ModulosRecyclerViewAdapter;

public class Treinamento extends AppCompatActivity {
    private Toolbar tb_treinamento;
    private TextView tv_treinamento;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private boolean m1 = true, m2 = false, m3 = false, m4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinamento);


        tv_treinamento = (TextView) findViewById(R.id.tv_treinamento);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_treinamento.setTypeface(font);

        tb_treinamento = (Toolbar) findViewById(R.id.tb_treinamento);
        tb_treinamento.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Treinamento.this, MenuPrincipal.class));
            }
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_treinamento);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        gaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //ÍCONES DOS MÓDULOS
        ModulosRecyclerViewAdapter rcAdapter = new ModulosRecyclerViewAdapter(Treinamento.this);
        recyclerView.setAdapter(rcAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Treinamento.this, MenuPrincipal.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_treinamento, menu);
        return true;
    }
/*
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
    }*/
}
