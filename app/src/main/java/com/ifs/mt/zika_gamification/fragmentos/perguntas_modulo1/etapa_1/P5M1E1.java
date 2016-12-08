package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.P5M1E1_Video;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P5M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    private VideoView videoView;
    TextView b_received;
    private RadioGroup radioGroupP5M1E1;
    private static final String TAG = "VideoPlayer";
    private ImageView imageViewPlay;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mActiveSurface;
    private int mPosition;
    private static List<RespostaM> listResposta;
    private RespostaM resposta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment = inflater.inflate(R.layout.fragment_pergunta5_modulo1_etapa1,
                container, false);

        resposta = new RespostaM();
        resposta.setResposta_Id("P5M1E1");

        radioGroupP5M1E1 = (RadioGroup) fragment.findViewById(R.id.radioGroupP5M1E1);
        radioGroupP5M1E1
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP5M1E1:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP5M1E1:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP5M1E1:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP5M1E1:
                                System.out.println("Opção D");
                                resposta.setResposta_Item("D");
                                break;
                        }

                    }
                });


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

                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP5M1E1, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {
                    Toast.makeText(getActivity().getApplicationContext(), "Concluir", Toast.LENGTH_SHORT).show();
                    getListResposta().add(4, resposta);

                    /**
                     * Faço a comparação com o gabarito, dou um sleep e mostro o resultado
                     * ou coloco um dialog com o resultado (o ribbon e embaixo o resultado tipo:
                     * 2/5 ou 4/5)depois chamo a tela de treinamento já com a proxima etapa
                     * desbloqueada
                     **/
                    for(RespostaM res: getListResposta()){
                        System.out.println("itens: "+res.getResposta_Item());
                    }
                }

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                P4M1E1.setListResposta(listResposta);
                ((M1E1) getActivity()).trocarPagina(3);
            }
        });


        return fragment;
    }


    public static void setListResposta(List<RespostaM> respostas) {
        listResposta = new ArrayList<>();
        listResposta = respostas;
    }

    public List<RespostaM> getListResposta() {
        if(null == listResposta){
            listResposta = new ArrayList<>();
            return listResposta;
        }
        return listResposta;
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
