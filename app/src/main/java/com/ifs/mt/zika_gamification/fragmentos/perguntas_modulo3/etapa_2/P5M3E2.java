package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo3.etapa_2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.EtapaDao;
import com.ifs.mt.zika_gamification.dao.HistoricoDao;
import com.ifs.mt.zika_gamification.dao.ModuloDao;
import com.ifs.mt.zika_gamification.dao.PerguntaDao;
import com.ifs.mt.zika_gamification.dao.RespostaDao;
import com.ifs.mt.zika_gamification.dao.StatusDao;
import com.ifs.mt.zika_gamification.model.EtapaM;
import com.ifs.mt.zika_gamification.model.HistoricoM;
import com.ifs.mt.zika_gamification.model.ModuloM;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.model.StatusM;
import com.ifs.mt.zika_gamification.telas.Login;
import com.ifs.mt.zika_gamification.telas.Treinamento;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.P5M1E2_Video;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.etapa_2.P5M3E2_Video;
import com.ifs.mt.zika_gamification.util.Util;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P5M3E2 extends Fragment {
    private Toolbar tb_bottom_next;
    private TextView textViewResultado;
    private RadioGroup radioGroupP5M3E2;
    private static final String TAG = "VideoPlayer";
    private ImageView imageViewPlay, imageViewEmblema;
    private static List<PerguntaM> listPerguntaResposta;
    private RespostaM resposta;
    private PerguntaM perguntaM;
    private ModuloM modulo;
    private EtapaM etapa;
    private Typeface font;
    private Banco banco;

    private DatabaseReference mDatabase;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment = inflater.inflate(R.layout.fragment_pergunta5_modulo3_etapa2,
                container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/agency_fb.ttf");

        perguntaM = new PerguntaM();
        perguntaM.setPergunta_Nome("Pergunta 05");
        perguntaM.setPergunta_Status(true);

        resposta = new RespostaM();
        resposta.setIdent("R5P5M3E2");

        radioGroupP5M3E2 = (RadioGroup) fragment.findViewById(R.id.radioGroupP5M3E2);
        radioGroupP5M3E2
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP5M3E2:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP5M3E2:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP5M3E2:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP5M3E2:
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
                startActivity(new Intent(getActivity(), P5M3E2_Video.class));
            }
        });


        mDatabase = FirebaseDatabase.getInstance().getReference();

        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_concluir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP5M3E2, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {
                   /* Toast.makeText(getActivity().getApplicationContext(), "Concluir", Toast.LENGTH_SHORT).show();*/
                    Util util = new Util();
                    resposta.setResposta_Correta(util.validaSingleResposta(resposta));
                    perguntaM.setRespostaM(resposta);
                    getListPergunta().add(4, perguntaM);
                    //==========================================================
                    modulo = new ModuloM();
                    //modulo.setModulo_Id("M1");
                    modulo.setModulo_Desricao("História do Aedes Aegypti no Brasil");
                    modulo.setModulo_Nome("M3");
                    //Ja coloco true porque é a ultima etapa do modulo
                    modulo.setModulo_Status(true);

                    etapa = new EtapaM();
                    etapa.setEtapa_Nome("E2M3");
                    etapa.setEtapa_Descricao("História");

                    int numAcertos = 0;
                    for (PerguntaM respo : getListPergunta()) {
                        if (respo.getRespostaM().isResposta_Correta()) {
                            numAcertos++;
                        }
                    }
                    etapa.setEtapa_Pontuacao(numAcertos);
                    etapa.setEtapa_Status(true);


                    banco = new Banco(getActivity().getApplicationContext());
                    ModuloDao moduloDao = new ModuloDao(banco);
                    HistoricoDao historicoDao = new HistoricoDao(banco);
                    EtapaDao etapaDao = new EtapaDao(banco);
                    RespostaDao respostaDao = new RespostaDao(banco);
                    PerguntaDao perguntaDao = new PerguntaDao(banco);
                    StatusDao statusDao = new StatusDao(banco);

                    int rowIdInsertEtapa = etapaDao.insert(etapa);
                    etapa.setEtapa_Id(rowIdInsertEtapa);

                    for (PerguntaM pergunta : getListPergunta()) {
                        pergunta.setEtapaM(etapa);
                        int resultResposta = respostaDao.insert(pergunta.getRespostaM());
                        resposta.setResposta_Id(resultResposta);
                        pergunta.setRespostaM(resposta);
                        int resultPergunta = perguntaDao.insert(pergunta);
                    }


                    HistoricoM historicoM = new HistoricoM();
                    historicoM.setUsuarioM(Login.getUsuarioLogado());
                    modulo.setEtapa(etapa);
                    int rowIdInsertModulo = moduloDao.insert(modulo);
                    modulo.setModulo_Id(rowIdInsertModulo);
                    historicoM.setModuloM(modulo);

                    int rowIdInsertHistorico = historicoDao.insert(historicoM);

                    StatusM statusM = new StatusM();
                    String userUid = Login.getUsuarioLogado().getUsuario_uid();
                    System.out.println("UserUid: "+userUid);
                    if (userUid != null) {
                        statusM.setUsuario_id(Login.getUsuarioLogado().getUsuario_id());
                        StatusM statusBanco = statusDao.getStatusByUsuario(statusM.getUsuario_id());
                        statusM.setPontuacao(numAcertos + statusBanco.getPontuacao());
                        statusM.setNivel(util.getNivel(numAcertos + statusBanco.getNivel()));
                        int experiencia = util.getExperiencia(numAcertos, etapa);
                        System.out.println("Experiencia no P5M3E2: "+experiencia);
                        statusM.setExperiencia( experiencia + statusBanco.getExperiencia());
                        System.out.println("Experiencia atualizada no P5M3E2: "+statusM.getExperiencia());
                        statusM.setModulo_03_status(true);
                        //Atualização local
                        statusM.setStatus_id(statusDao.update(statusM));

                        DatabaseReference ref = mDatabase.child("usuarios-status").child(userUid);
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("pontuacao", statusM.getPontuacao());
                        updates.put("nivel", statusM.getNivel());
                        updates.put("experiencia", statusM.getExperiencia());
                        //Somente na última etapa do módulo que atualiza o módulo
                        updates.put("modulo_03_status", statusM.isModulo_03_status());
                        //Atualização remota
                        ref.updateChildren(updates);
                    }



                    //APRESENTO O RESULTADO DA ETAPA
                    //==========================================================
                    // custom dialog
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.resultado_etapa_dialog_layout);
                    // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    textViewResultado = (TextView) dialog.findViewById(R.id.textViewResultado);
                    imageViewEmblema = (ImageView) dialog.findViewById(R.id.imageViewEmblema);
                    imageViewEmblema.setImageResource(util.getEmblema(numAcertos));

                    textViewResultado.setText(numAcertos + "/5");
                    textViewResultado.setTypeface(font);

                    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                    dialogButton.setTypeface(font);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            startActivity(new Intent(getActivity(), Treinamento.class));
                        }
                    });
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                    dialog.onBackPressed();
                    dialog.show();
                    //Salvo no banco essas respostas referenteS ao m1e1
                    System.out.println("Acertou: " + numAcertos);

                }

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                P4M3E2.setListPergunta(listPerguntaResposta);
                ((M1E2) getActivity()).trocarPagina(3);
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
