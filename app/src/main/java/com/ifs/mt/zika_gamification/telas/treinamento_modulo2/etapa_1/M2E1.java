package com.ifs.mt.zika_gamification.telas.treinamento_modulo2.etapa_1;

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
import com.ifs.mt.zika_gamification.adapter.ZoomOutPageTransformer;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P1M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P2M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P3M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P4M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P5M1E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_1.P1M2E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_1.P2M2E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_1.P3M2E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_1.P4M2E1;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_1.P5M2E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo2.M2;

import java.util.ArrayList;
import java.util.List;

public class M2E1 extends AppCompatActivity {
    private Toolbar tb_perguntas;
    private ViewPager mViewPager;
    private String TagFragmentP2;
    private TextView tv_treinamento;
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
        setContentView(R.layout.activity_m2_e1);


        tv_treinamento = (TextView) findViewById(R.id.tv_treinamento);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_treinamento.setTypeface(font);

        tb_perguntas = (Toolbar) findViewById(R.id.tb_perguntas);
        tb_perguntas.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(M2E1.this);
                alertDialog.setTitle("Tem certeza de que quer sair?");
                alertDialog.setMessage("Todo o progresso dessa etapa será perdido.");

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       startActivity(new Intent(M2E1.this, M2.class));
                    }
                });
                alertDialog.show();

            }
        });

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setOffscreenPageLimit(1);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        fragments.add(new P1M2E1());
        fragments.add(new P2M2E1());
        fragments.add(new P3M2E1());
        fragments.add(new P4M2E1());
        fragments.add(new P5M2E1());
        mViewPager.setAdapter(new FragmentCustomPagerAdapter(fragmentManager, fragments));
    }

    public void trocarPagina(int index) {
        mViewPager.setCurrentItem(index);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(M2E1.this);
        alertDialog.setTitle("Tem certeza de que quer sair?");
        alertDialog.setMessage("Todo o progresso dessa etapa será perdido.");

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(M2E1.this, M2.class));
            }
        });
        // alertDialog.setIcon(R.drawable.dengue_10dp);
        alertDialog.show();
    }



    public int getCurrentPage(){
        return mViewPager.getCurrentItem();
    }
}
