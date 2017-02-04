package com.ifs.mt.zika_gamification.telas.treinamento_modulo4.etapa_3;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ifs.mt.zika_gamification.R;

public class P5M4E3_Video extends AppCompatActivity {

    private ImageView imageViewVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_m4_e3_video);
        MediaController mc= new MediaController(this);
        VideoView view = (VideoView)findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/raw/m1e2p5";// + R.raw.m1e1p5;
        System.out.println("Path: "+path);
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);
        view.start();

        imageViewVoltar = (ImageView) findViewById(R.id.imageViewVoltar);
        imageViewVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }

}
