package com.ifs.mt.zika_gamification.telas.treinamento_modulo3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.EtapasM1RecyclerViewAdapter;
import com.ifs.mt.zika_gamification.adapter.EtapasM3RecyclerViewAdapter;
import com.ifs.mt.zika_gamification.telas.Treinamento;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

import static com.ifs.mt.zika_gamification.R.id.tb_etapa;
import static com.ifs.mt.zika_gamification.R.id.tv_treinamento;


public class M3 extends AppCompatActivity {
    private Toolbar tb_etapa;
    private TextView tv_treinamento;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos);


        tv_treinamento = (TextView) findViewById(R.id.tv_treinamento);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_treinamento.setTypeface(font);


        tb_etapa = (Toolbar) findViewById(R.id.tb_etapa);
        tb_etapa.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M3.this, Treinamento.class));
            }
        });


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_etapa);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        gaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //Apresenta os icones das etapas
        EtapasM3RecyclerViewAdapter rcAdapter = new EtapasM3RecyclerViewAdapter(M3.this);
        recyclerView.setAdapter(rcAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M3.this, Treinamento.class));
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
