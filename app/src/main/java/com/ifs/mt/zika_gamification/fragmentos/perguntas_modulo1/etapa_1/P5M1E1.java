package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.TextAlignment;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.Teste;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.P5M1E1_Video;


/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P5M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    private VideoView videoView;
    TextView b_received;
    private DocumentView dvText;
    private static final String TAG = "VideoPlayer";
    private ImageView imageViewPlay;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mActiveSurface;
    private int mPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment = inflater.inflate(R.layout.fragment_pergunta5_modulo1_etapa1,
                container, false);


        dvText = (DocumentView) fragment.findViewById(R.id.dvText);
        dvText.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        dvText.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.getInstance(DefaultHyphenator.HyphenPattern.PT));
        dvText.getDocumentLayoutParams().setHyphenated(false);

        imageViewPlay = (ImageView) fragment.findViewById(R.id.imageViewPlay);
        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), P5M1E1_Video.class));
            }
        });

        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_concluir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Concluir", Toast.LENGTH_SHORT).show();

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();
                ((M1E1) getActivity()).trocarPagina(3);
            }
        });


        return fragment;
    }


    public void b_updateText(String t) {
        b_received.setText(t);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
