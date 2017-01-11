package com.ifs.mt.zika_gamification.telas.treinamento_modulo1;

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
import com.ifs.mt.zika_gamification.telas.Treinamento;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

public class M1 extends AppCompatActivity {
    private Toolbar tb_etapa;
    private TextView tv_treinamento;
    private boolean A = false, B = false;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private MySharedPreferencesController mySharedPreferencesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1);


        tv_treinamento = (TextView) findViewById(R.id.tv_treinamento);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_treinamento.setTypeface(font);


        tb_etapa = (Toolbar) findViewById(R.id.tb_etapa);
        tb_etapa.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M1.this, Treinamento.class));
            }
        });

       /* GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapterEtapasM1(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                *//*Toast.makeText(M1.this, "" + position,
                        Toast.LENGTH_SHORT).show();*//*

                mySharedPreferencesController = MySharedPreferencesController.getInstance(getApplicationContext());
               // mySharedPreferencesController.clearData();

                A = true;
                B = mySharedPreferencesController.getData(MySharedPreferencesController.M1_E2);

                switch(position){
                    case 0:
                        if (A)
                            startActivity(new Intent(M1.this, M1E1.class));
                        break;
                    case 1:
                        if (B)
                            startActivity(new Intent(M1.this, M1E2.class));
                        break;


                }


            }
        });*/

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_etapa);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        gaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //Apresenta os icones das etapas
        EtapasM1RecyclerViewAdapter rcAdapter = new EtapasM1RecyclerViewAdapter(M1.this);
        recyclerView.setAdapter(rcAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M1.this, Treinamento.class));
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
