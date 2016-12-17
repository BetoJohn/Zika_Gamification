package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.HistoricoDao;
import com.ifs.mt.zika_gamification.model.EtapaM;
import com.ifs.mt.zika_gamification.model.HistoricoM;
import com.ifs.mt.zika_gamification.model.ModuloM;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.Login;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.M1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.P5M1E1_Video;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;
import com.ifs.mt.zika_gamification.util.Util;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P5M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    private VideoView videoView;
    private TextView textViewResultado;
    private RadioGroup radioGroupP5M1E1;
    private static final String TAG = "VideoPlayer";
    private ImageView imageViewPlay, imageViewEmblema;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mActiveSurface;
    private int mPosition;
    private static List<PerguntaM> listPerguntaResposta;
    private RespostaM resposta;
    private PerguntaM perguntaM;
    private ModuloM modulo;
    private EtapaM etapa;
    private Typeface font;
    private Banco bancoHistorico;
    private MySharedPreferencesController mySharedPreferencesController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment = inflater.inflate(R.layout.fragment_pergunta5_modulo1_etapa1,
                container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/agency_fb.ttf");

        perguntaM = new PerguntaM();
        perguntaM.setPergunta_Id("P5M1E1");
        perguntaM.setPergunta_Nome("Pergunta 05");
        perguntaM.setPergunta_Status(true);

        resposta = new RespostaM();
        resposta.setResposta_Id("R5P5M1E1");

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

                try {
                    boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP5M1E1, "Selecione uma resposta!", getActivity().getApplicationContext());
                    if (ok) {
                   /* Toast.makeText(getActivity().getApplicationContext(), "Concluir", Toast.LENGTH_SHORT).show();*/
                        perguntaM.setRespostaM(resposta);
                        getListPergunta().add(4, perguntaM);

                        /**
                         * Faço a comparação com o gabarito, dou um sleep e mostro o resultado
                         * ou coloco um dialog com o resultado (o ribbon e embaixo o resultado tipo:
                         * 2/5 ou 4/5)depois chamo a tela de treinamento já com a proxima etapa
                         * desbloqueada
                         **/
                        for (PerguntaM res : getListPergunta()) {
                            System.out.println("itens: " + res.getRespostaM().getResposta_Item());
                        }
                        Util util = new Util();
                        List<PerguntaM> perguntas = util.validaResposta(getListPergunta());

                        int numAcertos = 0;
                        for (PerguntaM respo : perguntas) {
                            if (respo.getRespostaM().isResposta_Correta()) {
                                numAcertos++;
                            }
                        }
                        //==========================================================
                        modulo = new ModuloM();
                        modulo.setModulo_Id("M1");
                        modulo.setModulo_Desricao("História do Aedes Aegypti no Brasil");
                        modulo.setModulo_Nome("Modulo 01");
                        modulo.setModulo_Status(true);

                        etapa = new EtapaM();
                        etapa.setEtapa_Id("E1");
                        etapa.setEtapa_Nome("Etapa 01");
                        etapa.setEtapa_Descricao("Introdução");
                        etapa.setEtapa_Status(true);
                        etapa.setPerguntas(perguntas);


                        //FAÇO A INSERÇÃO NO BANCO
                        //==========================================================
                        //A inserção vai seguir essa sequencia: Carrego aqui o Objeto HistoricoM passando o id do usuario logado,
                        //depois o id do modulo. ModuloM tem dependencia de EtapaM que dependa da PerguntaM e assim por diante
                        HistoricoM historicoM = new HistoricoM();
                        historicoM.setModuloM(modulo);
                        historicoM.setUsuarioM(Login.getUsuarioLogado());


                        bancoHistorico = new Banco(getActivity().getApplicationContext());
                        HistoricoDao historicoDao = new HistoricoDao(bancoHistorico);

                        int rowIdInsertHistorico = historicoDao.insert(historicoM);

                        //============== Adiciono valores no SharePreferences =======
                        mySharedPreferencesController = MySharedPreferencesController.getInstance(getActivity());
                        //Etapa 01 concluida desbloqueio a Etapa 02
                        mySharedPreferencesController.saveData(MySharedPreferencesController.M1_E2, true);

                        //APRESENTO O RESULTADO
                        //==========================================================
                        // custom dialog
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.resultado_etapa_dialog_layout);
                        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        textViewResultado = (TextView) dialog.findViewById(R.id.textViewResultado);
                        imageViewEmblema = (ImageView) dialog.findViewById(R.id.imageViewEmblema);
                        if (numAcertos < 2) {
                            imageViewEmblema.setImageResource(R.drawable.emblema_menor_que_3);//android:src="@drawable/emblema_menor_que_3"
                        } else if (numAcertos == 2 || numAcertos == 3) {
                            imageViewEmblema.setImageResource(R.drawable.emblema_maior_igual_a_3_menor_igual_a_7);
                        } else if (numAcertos > 3) {
                            imageViewEmblema.setImageResource(R.drawable.emblema_maior_que_7);
                        }

                        textViewResultado.setText(numAcertos + "/5");
                        textViewResultado.setTypeface(font);

                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        dialogButton.setTypeface(font);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                startActivity(new Intent(getActivity(), M1.class));
                            }
                        });
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        //Salvo no banco essas respostas referenteS ao m1e1
                        System.out.println("Acertou: " + numAcertos);

                    }
                }catch (Exception e){
                    System.out.println("Exception: "+e.getMessage());
                }


            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                P4M1E1.setListPergunta(listPerguntaResposta);
                ((M1E1) getActivity()).trocarPagina(3);
            }
        });


        return fragment;
    }


    public static void setListPergunta(List<PerguntaM> perguntas) {
        listPerguntaResposta = new ArrayList<>();
        listPerguntaResposta = perguntas;
        for (PerguntaM res : listPerguntaResposta) {
            System.out.println("itens: " + res.getRespostaM().getResposta_Item());
        }
    }

    public List<PerguntaM> getListPergunta() {
        if (null == listPerguntaResposta) {
            listPerguntaResposta = new ArrayList<>();
            return listPerguntaResposta;
        }
        return listPerguntaResposta;
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
