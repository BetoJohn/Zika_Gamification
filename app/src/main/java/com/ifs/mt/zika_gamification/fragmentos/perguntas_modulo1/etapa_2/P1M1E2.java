package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1.P2M1E1;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P1M1E2 extends Fragment {
    private Toolbar tb_bottom_next;
    private RadioGroup radioGroupP1M1E2;
    private static List<PerguntaM> listPerguntaResposta;
    private RespostaM resposta;
    private PerguntaM perguntaM;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta1_modulo1_etapa2,
                container, false);

        perguntaM = new PerguntaM();
        perguntaM.setPergunta_Id("P1M1E2");
        perguntaM.setPergunta_Nome("Pergunta 01");
        perguntaM.setPergunta_Status(true);

        resposta = new RespostaM();
        resposta.setResposta_Id("R1P1M1E2");

        radioGroupP1M1E2 = (RadioGroup) fragment.findViewById(R.id.radioGroupP1M1E2);
        radioGroupP1M1E2
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP1M1E2:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP1M1E2:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP1M1E2:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP1M1E2:
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

                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP1M1E2, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {

                    listPerguntaResposta = new ArrayList<>();
                    if (getListResposta().size() == 1){
                        getListResposta().remove(0);
                    }
                    perguntaM.setRespostaM(resposta);
                    getListResposta().add(0, perguntaM);
                    P2M1E2.setListResposta(listPerguntaResposta);
                    ((M1E2) getActivity()).trocarPagina(1);
                }

            }
        });

        perguntaM.setRespostaM(resposta);

        return fragment;
    }

    public static void setListResposta(List<PerguntaM> perguntas) {
        listPerguntaResposta = new ArrayList<>();
        listPerguntaResposta = perguntas;
        for(PerguntaM res: listPerguntaResposta){
            System.out.println("itens: "+res.getRespostaM().getResposta_Item());
        }

    }

    public List<PerguntaM> getListResposta() {
        if(null == listPerguntaResposta){
            listPerguntaResposta = new ArrayList<>();
            return listPerguntaResposta;
        }
        return listPerguntaResposta;
    }
}
