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

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.validacao.AutenticarResposta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P2M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    static TextView b_received;
    private RadioGroup radioGroupP2M1E1;
    private static List<RespostaM> listResposta;
    private RespostaM resposta;


    EditText A_input;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta2_modulo1_etapa1,
                container, false);

        resposta = new RespostaM();
        resposta.setResposta_Id("P2M1E1");
        //---------------------teste bundle-----------------------
        b_received = (TextView) fragment.findViewById(R.id.b_received);
        String myTag = getTag();

        //quando o fragment é carregado ele recupera sua tag e seta no metodo  public void setTabFragmentB(String t) da AppCompatActivity
        ((M1E1) getActivity()).setTagFragmentP2(myTag);

        radioGroupP2M1E1 = (RadioGroup) fragment.findViewById(R.id.radioGroupP2M1E1);
        radioGroupP2M1E1
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rbOpAP2M1E1:
                                System.out.println("Opção A");
                                resposta.setResposta_Item("A");
                                break;
                            case R.id.rbOpBP2M1E1:
                                System.out.println("Opção B");
                                resposta.setResposta_Item("B");
                                break;
                            case R.id.rbOpCP2M1E1:
                                System.out.println("Opção C");
                                resposta.setResposta_Item("C");
                                break;
                            case R.id.rbOpDP2M1E1:
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
                boolean ok = AutenticarResposta.validarRadioGroup(radioGroupP2M1E1, "Selecione uma resposta!", getActivity().getApplicationContext());
                if (ok) {
                    if (getListResposta().size() == 2){
                        getListResposta().remove(1);
                    }
                    getListResposta().add(1, resposta);
                    P3M1E1.setListResposta(listResposta);
                    ((M1E1) getActivity()).trocarPagina(2);
                }

            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if (null != resposta.getResposta_Item()) {
                    getListResposta().remove(1);
                }*/

                if (getListResposta().size() == 2){
                    getListResposta().remove(1);
                }
                P1M1E1.setListResposta(listResposta);
                ((M1E1) getActivity()).trocarPagina(0);
            }
        });


        return fragment;
    }


    public static void setListResposta(List<RespostaM> respostas) {
        listResposta = new ArrayList<>();
        listResposta = respostas;
        System.out.println("Posiçao 0: "+ listResposta.get(0).getResposta_Item());

        if(listResposta.size() > 2){
            System.out.println("Posiçao 1: " + listResposta.get(1).getResposta_Item());
            System.out.println("Posiçao 2: " + listResposta.get(2).getResposta_Item());
        }

        b_received.setText(listResposta.get(0).getResposta_Item());
    }

    public List<RespostaM> getListResposta() {
        if (null == listResposta) {
            listResposta = new ArrayList<>();
            return listResposta;
        }
        return listResposta;
    }


}
