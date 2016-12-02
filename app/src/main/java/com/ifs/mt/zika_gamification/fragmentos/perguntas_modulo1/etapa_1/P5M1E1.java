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



/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P5M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    private VideoView videoView;
    TextView b_received;
    private DocumentView dvText;
    private static final String TAG = "VideoPlayer";
    private SurfaceHolder mFirstSurface;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mActiveSurface;

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


        /*MediaController mc= new MediaController(getActivity().getApplicationContext());
        VideoView view = (VideoView)fragment.findViewById(R.id.videoView);
        String path = "android.resource://" + getActivity().getPackageName() + "/raw/m1e1p5";// + R.raw.m1e1p5;
        System.out.println("Path: "+path);
        view.setVideoURI(Uri.parse(path));
        view.setMediaController(mc);
        view.start();

        videoView = (VideoView)fragment.findViewById(R.id.videoView);
        Uri uri =  Uri.parse("android.resource://com.ifs.mt.zika_gamification/raw/m1e1p5");
        videoView.setVideoURI(uri);
        videoView.setMediaController(new MediaController(getActivity().getApplicationContext()));
        videoView.start();*/

        SurfaceView first = (SurfaceView) fragment.findViewById(R.id.firstSurface);
        first.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Log.d(TAG, "First surface created!");
                mFirstSurface = surfaceHolder;
                Uri mVideoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.m1e1p5);
                if (mVideoUri != null) {
                    mMediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(),
                            mVideoUri, mFirstSurface);
                    mActiveSurface = mFirstSurface;
                    mMediaPlayer.start();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Log.d(TAG, "First surface destroyed!");
            }
        });


        tb_bottom_next = (Toolbar)fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_concluir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Concluir", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(fragment.getContext(), Teste.class));

                /*final Dialog dialog = new Dialog(fragment.getContext());// add here your class name
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);//add your own xml with defied with and height of videoview
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                        GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT);
                lp.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(lp);
                String uriPath= "android.resource://" + getActivity().getPackageName() + "/" + R.raw.m1e1p5;

                getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
                Log.v("Vidoe-URI", uriPath+ "");

                videoView = (VideoView) dialog.findViewById(R.id.vv);
                videoView.setVideoURI(Uri.parse(uriPath));
                videoView.start();*/






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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        videoView = (VideoView)getActivity().findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(getActivity()));
    }

    public void playVideo() {
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/m1e1p5");
        Log.d(TAG, "Uri is: " + uri);
        setVideoLocation(uri);
        if (!videoView.isPlaying()) {
            videoView.start();
        }

//        videoView.start();
    }

    private void setVideoLocation(Uri uri) {
        try {
            videoView.setVideoURI(uri);
        } catch (Exception e) {
            Log.e(TAG, "VideoPlayer uri was invalid", e);
            Toast.makeText(getActivity(), "Not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void pauseVideo() {
        videoView.pause();
    }

    public void b_updateText(String t){
        b_received.setText(t);
    }

}
