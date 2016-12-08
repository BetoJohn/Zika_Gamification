package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1;

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
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P1M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    private DocumentView dvText;
    private TextView textView;
    private RadioGroup radioGroupP1M1E1;
    private static List<RespostaM> listResposta;
    private RespostaM resposta;


    EditText A_input;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta1_modulo1_etapa1,
                container, false);
        resposta = new RespostaM();
        resposta.setResposta_Id("P1M1E1");
        //---------------------teste bundle-----------------------
        A_input = (EditText) fragment.findViewById(R.id.a_input);
        //---------------------teste bundle-----------------------

        textView = (TextView) fragment.findViewById(R.id.dvText);

        radioGroupP1M1E1 = (RadioGroup) fragment.findViewById(R.id.radioGroupP1M1E1);
        radioGroupP1M1E1
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP1M1E1:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP1M1E1:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP1M1E1:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP1M1E1:
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
               /* String textPassToB = A_input.getText().toString();
                String TagOfFragmentB = ((M1E1) getActivity()).getTagFragmentP2();
                P2M1E1 fragmentB = (P2M1E1) getActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag(TagOfFragmentB);*/


                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP1M1E1, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {

                    listResposta = new ArrayList<>();
                    if (getListResposta().size() == 1){
                        getListResposta().remove(0);
                    }
                    getListResposta().add(0, resposta);
                    P2M1E1.setListResposta(listResposta);
                    ((M1E1) getActivity()).trocarPagina(1);
                }

            }
        });


        return fragment;
    }

    public static void setListResposta(List<RespostaM> respostas) {
        listResposta = new ArrayList<>();
        listResposta = respostas;
        for(RespostaM res: listResposta){
            System.out.println("itens: "+res.getResposta_Item());
        }

    }

    public List<RespostaM> getListResposta() {
        if(null == listResposta){
            listResposta = new ArrayList<>();
            return listResposta;
        }
        return listResposta;
    }
}
