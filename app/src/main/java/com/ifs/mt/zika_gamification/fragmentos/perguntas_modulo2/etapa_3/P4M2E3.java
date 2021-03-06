package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.etapa_3;

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
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo2.etapa_2.M2E2;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo2.etapa_3.M2E3;
import com.ifs.mt.zika_gamification.util.Util;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P4M2E3 extends Fragment {
    private Toolbar tb_bottom_next;
    private RadioGroup radioGroupP4M2E3;
    private static List<PerguntaM> listPerguntaResposta;
    private RespostaM resposta;
    private PerguntaM perguntaM;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta4_modulo2_etapa3,
                container, false);

        perguntaM = new PerguntaM();
        perguntaM.setPergunta_Nome("Pergunta 04");
        perguntaM.setPergunta_Status(true);

        resposta = new RespostaM();
        resposta.setIdent("R4P4M2E3");

        radioGroupP4M2E3 = (RadioGroup) fragment.findViewById(R.id.radioGroupP4M2E3);
        radioGroupP4M2E3
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP4M2E3:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP4M2E3:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP4M2E3:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP4M2E3:
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

                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP4M2E3, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {

                    if (getListPergunta().size() == 4){
                        getListPergunta().remove(3);
                    }

                    Util util = new Util();
                    resposta.setResposta_Correta(util.validaSingleResposta(resposta));
                    perguntaM.setRespostaM(resposta);
                    getListPergunta().add(3, perguntaM);
                    P5M2E3.setListPergunta(listPerguntaResposta);
                    ((M2E3) getActivity()).trocarPagina(4);
                }

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (getListPergunta().size() == 4){
                    getListPergunta().remove(3);
                }
                P3M2E3.setListPergunta(listPerguntaResposta);
                ((M2E3) getActivity()).trocarPagina(2);
            }
        });


        return fragment;
    }

    public static void setListPergunta(List<PerguntaM> perguntas) {
        listPerguntaResposta = new ArrayList<>();
        listPerguntaResposta = perguntas;
        for(PerguntaM res: listPerguntaResposta){
            System.out.println("itens: " + res.getRespostaM().getResposta_Item());
        }
    }

    public List<PerguntaM> getListPergunta() {
        if(null == listPerguntaResposta){
            listPerguntaResposta = new ArrayList<>();
            return listPerguntaResposta;
        }
        return listPerguntaResposta;
    }

}
