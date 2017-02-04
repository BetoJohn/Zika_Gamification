package com.ifs.mt.zika_gamification.telas.treinamento_modulo4.etapa_3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.FragmentCustomPagerAdapter;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_3.P1M2E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_3.P2M2E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_3.P3M2E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_3.P4M2E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_3.P5M2E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo4.etapa_3.P1M4E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo4.etapa_3.P2M4E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo4.etapa_3.P3M4E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo4.etapa_3.P4M4E3;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo4.etapa_3.P5M4E3;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo4.M4;

import java.util.ArrayList;
import java.util.List;

public class M4E3 extends AppCompatActivity {
    private Toolbar tb_perguntas;
    private ViewPager mViewPager;
    private TextView tv_treinamento;
    List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_e2);


        tv_treinamento = (TextView) findViewById(R.id.tv_treinamento);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_treinamento.setTypeface(font);

        tb_perguntas = (Toolbar) findViewById(R.id.tb_perguntas);
        tb_perguntas.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(M4E3.this);
                alertDialog.setTitle("Tem certeza de que quer sair?");
                alertDialog.setMessage("Todo o progresso dessa etapa será perdido.");

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       startActivity(new Intent(M4E3.this, M4.class));
                    }
                });
                // alertDialog.setIcon(R.drawable.dengue_10dp);
                alertDialog.show();

            }
        });

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(1);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new P1M4E3());
        fragments.add(new P2M4E3());
        fragments.add(new P3M4E3());
        fragments.add(new P4M4E3());
        fragments.add(new P5M4E3());
        mViewPager.setAdapter(new FragmentCustomPagerAdapter(fragmentManager, fragments));
    }

    public void trocarPagina(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(M4E3.this);
        alertDialog.setTitle("Tem certeza de que quer sair?");
        alertDialog.setMessage("Todo o progresso dessa etapa será perdido.");

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(M4E3.this, M4.class));
            }
        });
        // alertDialog.setIcon(R.drawable.dengue_10dp);
        alertDialog.show();
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
