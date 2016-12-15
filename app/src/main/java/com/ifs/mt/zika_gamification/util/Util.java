package com.ifs.mt.zika_gamification.util;

import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Betto Silva on 09/12/2016.
 */
public class Util {

    public List<PerguntaM> validaResposta(List<PerguntaM> usu) {

        List<PerguntaM> resultado = new ArrayList<>();
        List<RespostaM> respostaGab = gabarito();
        for (PerguntaM respostaUsuario : usu) {
            for (RespostaM respostaGabarito : respostaGab) {
                if (respostaUsuario.getRespostaM().getResposta_Id().equals(respostaGabarito.getResposta_Id())) {

                    if (respostaUsuario.getRespostaM().getResposta_Item().equals(respostaGabarito.getResposta_Item())) {
                        respostaUsuario.getRespostaM().setResposta_Correta(true);
                        resultado.add(respostaUsuario);
                    } else {
                        respostaUsuario.getRespostaM().setResposta_Correta(false);
                        resultado.add(respostaUsuario);
                    }

                }
            }
        }
        return resultado;

    }

    public List<RespostaM> gabarito() {

        /**
         Modulo 01:
         Etapa 01:A/B/D/D/D
         Etapa 02:B/D/B/A/C
         Modulo 02
         Etapa 01:A/A/B/C/A
         Etapa 02:A/D/A/A/D
         Etapa 03:C/C/D/D/D
         Modulo 03:
         Etapa 01:C/D/C/D/B
         Etapa 02:A/D/A/D/A
         Modulo 04
         Etapa 01:D/D/D/B/A
         Etapa 02:D/C/A/D/C
         Etapa 03:A/D/B/A/D
         **/

        //=============== Módulo 01 Etapa 01 =============
        List<RespostaM> list = new ArrayList<>();
        RespostaM m1e1p1 = new RespostaM();
        m1e1p1.setResposta_Id("R1P1M1E1");
        m1e1p1.setResposta_Item("A");
        list.add(m1e1p1);

        RespostaM m1e1p2 = new RespostaM();
        m1e1p2.setResposta_Id("R2P2M1E1");
        m1e1p2.setResposta_Item("B");
        list.add(m1e1p2);

        RespostaM m1e1p3 = new RespostaM();
        m1e1p3.setResposta_Id("R3P3M1E1");
        m1e1p3.setResposta_Item("D");
        list.add(m1e1p3);

        RespostaM m1e1p4 = new RespostaM();
        m1e1p4.setResposta_Id("R4P4M1E1");
        m1e1p4.setResposta_Item("D");
        list.add(m1e1p4);

        RespostaM m1e1p5 = new RespostaM();
        m1e1p5.setResposta_Id("R5P5M1E1");
        m1e1p5.setResposta_Item("D");
        list.add(m1e1p5);
        
        //=============== Módulo 02 Etapa 02 =============
        RespostaM m1e2p1 = new RespostaM();
        m1e2p1.setResposta_Id("R1P1M1E2");
        m1e2p1.setResposta_Item("B");
        list.add(m1e2p1);

        RespostaM m1e2p2 = new RespostaM();
        m1e2p2.setResposta_Id("R2P2M1E2");
        m1e2p2.setResposta_Item("D");
        list.add(m1e2p2);

        RespostaM m1e2p3 = new RespostaM();
        m1e2p3.setResposta_Id("R3P3M1E2");
        m1e2p3.setResposta_Item("B");
        list.add(m1e2p3);

        RespostaM m1e2p4 = new RespostaM();
        m1e2p4.setResposta_Id("R4P4M1E2");
        m1e2p4.setResposta_Item("A");
        list.add(m1e2p4);

        RespostaM m1e2p5 = new RespostaM();
        m1e2p5.setResposta_Id("R5P5M1E2");
        m1e2p5.setResposta_Item("C");
        list.add(m1e2p5);

        return list;

    }
}
