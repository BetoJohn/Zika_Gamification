package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo4.etapa_3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo2.etapa_3.M2E3;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo4.etapa_3.M4E3;
import com.ifs.mt.zika_gamification.util.Util;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P3M4E3 extends Fragment {
    private Toolbar tb_bottom_next;
    private RadioGroup radioGroupP3M4E3;
    private static List<PerguntaM> listPerguntaResposta;
    private RespostaM resposta;
    private PerguntaM perguntaM;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta3_modulo4_etapa3,
                container, false);

        perguntaM = new PerguntaM();
        perguntaM.setPergunta_Nome("Pergunta 03");
        perguntaM.setPergunta_Status(true);

        resposta = new RespostaM();
        resposta.setIdent("R3P3M4E3");

        radioGroupP3M4E3 = (RadioGroup) fragment.findViewById(R.id.radioGroupP3M4E3);
        radioGroupP3M4E3
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP3M4E3:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP3M4E3:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP3M4E3:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP3M4E3:
                                System.out.println("Opção D");
                                resposta.setResposta_Item("D");
                                break;
                        }

                    }
                });

        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_avancar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP3M4E3, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {

                    if (getListPergunta().size() == 3){
                        getListPergunta().remove(2);
                    }

                    Util util = new Util();
                    resposta.setResposta_Correta(util.validaSingleResposta(resposta));
                    perguntaM.setRespostaM(resposta);
                    getListPergunta().add(2, perguntaM);
                    P4M4E3.setListPergunta(listPerguntaResposta);
                    ((M4E3) getActivity()).trocarPagina(3);
                }

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (getListPergunta().size() == 3){
                    getListPergunta().remove(2);
                }
                P2M4E3.setListPergunta(listPerguntaResposta);
                ((M4E3) getActivity()).trocarPagina(1);
            }
        });


        return fragment;
    }

    public static void setListPergunta(List<PerguntaM> perguntas) {
        listPerguntaResposta = new ArrayList<>();
        listPerguntaResposta = perguntas;
        System.out.println("Posiçao 0: " + listPerguntaResposta.get(0).getRespostaM().getResposta_Item());

        for(PerguntaM res: listPerguntaResposta){
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
