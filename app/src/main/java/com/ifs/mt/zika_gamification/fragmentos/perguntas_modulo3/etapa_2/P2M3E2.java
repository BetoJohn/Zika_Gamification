package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo3.etapa_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.etapa_2.M3E2;
import com.ifs.mt.zika_gamification.util.Util;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P2M3E2 extends Fragment {
    private Toolbar tb_bottom_next;
    private RadioGroup radioGroupP2M3E2;
    private static List<PerguntaM> listPerguntaResposta;
    private RespostaM resposta;
    private PerguntaM perguntaM;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta2_modulo3_etapa2,
                container, false);

        perguntaM = new PerguntaM();
        perguntaM.setPergunta_Nome("Pergunta 02");
        perguntaM.setPergunta_Status(true);

        resposta = new RespostaM();
        resposta.setIdent("R2P2M3E2");


        radioGroupP2M3E2 = (RadioGroup) fragment.findViewById(R.id.radioGroupP2M3E2);
        radioGroupP2M3E2
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP2M3E2:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP2M3E2:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP2M3E2:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP2M3E2:
                                System.out.println("Opção D");
                                resposta.setResposta_Item("D");
                                break;
                        }

                    }
                });

        //---------------------teste bundle-----------------------


        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_avancar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP2M3E2, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {
                    if (getListPergunta().size() == 2) {
                        getListPergunta().remove(1);
                    }
                    Util util = new Util();
                    resposta.setResposta_Correta(util.validaSingleResposta(resposta));
                    perguntaM.setRespostaM(resposta);
                    getListPergunta().add(1, perguntaM);
                    P3M3E2.setListPergunta(listPerguntaResposta);
                    ((M3E2) getActivity()).trocarPagina(2);
                }

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getListPergunta().size() == 2) {
                    getListPergunta().remove(1);
                }
                P1M3E2.setListPergunta(listPerguntaResposta);
                ((M3E2) getActivity()).trocarPagina(0);
            }
        });


        return fragment;
    }


    public static void setListPergunta(List<PerguntaM> perguntas) {
        listPerguntaResposta = new ArrayList<>();
        listPerguntaResposta = perguntas;
        System.out.println("Posiçao 0: " + listPerguntaResposta.get(0).getRespostaM().getResposta_Item());

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


}
