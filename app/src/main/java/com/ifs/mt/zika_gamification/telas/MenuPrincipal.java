package com.ifs.mt.zika_gamification.telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;

public class MenuPrincipal extends AppCompatActivity {

    private Toolbar tb;
    private ImageView  imageViewTreinamento, imageViewStatus, imageViewRanking;
    private TextView tv_usuario_logado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

        tv_usuario_logado = (TextView) findViewById(R.id.tv_usuario_logado);
        tv_usuario_logado.setText("Bem Vindo, " + Login.getUsuarioLogado().getUsuario_nome());

        imageViewTreinamento = (ImageView)findViewById(R.id.imageViewTreinamento);
        imageViewTreinamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, Treinamento.class));
            }
        });

        imageViewStatus = (ImageView)findViewById(R.id.imageViewStatus);
        imageViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuPrincipal.this, "imageViewStatus",Toast.LENGTH_SHORT).show();
            }
        });

        imageViewRanking = (ImageView)findViewById(R.id.imageViewRanking);
        imageViewRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuPrincipal.this, "imageViewRanking",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_sair:
                startActivity(new Intent(MenuPrincipal.this, Login.class));
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}
