package com.ifs.mt.zika_gamification.telas;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.StatusDao;
import com.ifs.mt.zika_gamification.model.StatusM;

public class Status extends AppCompatActivity {
    private TextView tv_experiencia, tv_nivel, tv_pontos, tv_tb_status;
    private Toolbar tb_status;
    private ImageView iv_m1, iv_m2, iv_m3, iv_m4;
    private Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");

        tb_status = (Toolbar) findViewById(R.id.tb_status);
        tb_status.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Status.this, MenuPrincipal.class));
            }
        });
        tv_tb_status = (TextView)findViewById(R.id.tv_tb_status);
        tv_tb_status.setTypeface(font);


        tv_experiencia = (TextView) findViewById(R.id.tv_experiencia);
        tv_experiencia.setTypeface(font);

        tv_nivel = (TextView) findViewById(R.id.tv_nivel);
        tv_nivel.setTypeface(font);

        tv_pontos = (TextView) findViewById(R.id.tv_pontos);
        tv_pontos.setTypeface(font);
        iv_m1 = (ImageView) findViewById(R.id.iv_m1);
        iv_m2 = (ImageView) findViewById(R.id.iv_m2);
        iv_m3 = (ImageView) findViewById(R.id.iv_m3);
        iv_m4 = (ImageView) findViewById(R.id.iv_m4);

        banco = new Banco(getApplicationContext());
        StatusDao statusDao = new StatusDao(banco);
        StatusM statusM = statusDao.getStatusByUsuario(Login.getUsuarioLogado().getUsuario_id());

        tv_experiencia.setText("Experiência: "+statusM.getExperiencia());
        tv_nivel.setText("Nível: "+statusM.getNivel());
        tv_pontos.setText("Pontos: "+statusM.getPontuacao());

        if (statusM.isModulo_01_status()) {
            iv_m1.setImageResource(R.drawable.modulo_01_c);
        }
        if (statusM.isModulo_02_status()) {
            iv_m2.setImageResource(R.drawable.modulo_02_c);
        }
        if (statusM.isModulo_03_status()) {
            iv_m3.setImageResource(R.drawable.modulo_03_c);
        }
        if (statusM.isModulo_04_status()) {
            iv_m4.setImageResource(R.drawable.modulo_04_c);
        }
    }
}
