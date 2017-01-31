package com.ifs.mt.zika_gamification.telas;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.ifs.mt.zika_gamification.R;

public class Help extends AppCompatActivity {
    private Toolbar tb_help;
    private TextView tv_tb_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tb_help = (Toolbar) findViewById(R.id.tb_help);
        tb_help.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Help.this, MenuPrincipal.class));
            }
        });
        tv_tb_help = (TextView) findViewById(R.id.tv_tb_help);
        tv_tb_help.setTypeface(font);


        TextView tv_link01 = (TextView) findViewById(R.id.tv_link01);
        tv_link01.setText(Html.fromHtml("<a href=http://www.stackoverflow.com> STACK OVERFLOW "));
        tv_link01.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
