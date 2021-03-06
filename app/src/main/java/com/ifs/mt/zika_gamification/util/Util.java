package com.ifs.mt.zika_gamification.util;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.EtapaM;
import com.ifs.mt.zika_gamification.model.ModuloM;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.RespostaM;

import java.util.ArrayList;
import java.util.List;

import static com.ifs.mt.zika_gamification.R.id.imageViewEmblema;

/**
 * Created by Betto Silva on 09/12/2016.
 */
public class Util {

    public int getNivel(int pontos) {
        int nivel = 0;
        if (pontos <= 5) {
            nivel = 1;
        }
        if (pontos >= 6 && pontos <= 10) {
            nivel = 2;
        }
        if (pontos > 10 && pontos <= 15) {
            nivel = 3;
        }
        if (pontos > 15 && pontos <= 20) {
            nivel = 4;
        }
        if (pontos > 20 && pontos <= 25) {
            nivel = 5;
        }
        if (pontos > 25 && pontos <= 30) {
            nivel = 6;
        }
        if (pontos > 30 && pontos <= 35) {
            nivel = 7;
        }
        if (pontos > 35 && pontos <= 40) {
            nivel = 8;
        }
        if(pontos > 40 && pontos <= 45){
            nivel = 9;
        }
        if(pontos > 45 ){
            nivel = 10;
        }
        return nivel;
    }

    public int getExperiencia(int pontos, EtapaM etapaM) {
        int experiencia = 0;
        //20
        if (etapaM.getEtapa_Nome().equals("E1M1") || etapaM.getEtapa_Nome().equals("E2M1")) {
            experiencia = pontos * 2;
        }
        //30
        if (etapaM.getEtapa_Nome().equals("E1M2") || etapaM.getEtapa_Nome().equals("E2M2")) {
            experiencia = pontos * 3;
        }
        //30
        if (etapaM.getEtapa_Nome().equals("E3M2")) {
            experiencia = pontos * 6;
        }
        //60
        if (etapaM.getEtapa_Nome().equals("E1M3") || etapaM.getEtapa_Nome().equals("E2M3")) {
            experiencia = pontos * 6;
        }
        //35
        if (etapaM.getEtapa_Nome().equals("E1M4")) {
            experiencia = pontos * 7;
        }
        //40
        if (etapaM.getEtapa_Nome().equals("E2M4")) {
            experiencia = pontos * 8;
        }
        //45
        if (etapaM.getEtapa_Nome().equals("E3M4")) {
            experiencia = pontos * 9;
        }


        return experiencia;
    }

    public boolean validaSingleResposta(RespostaM respostaM) {

        List<RespostaM> respostaGab = gabarito();
        for (RespostaM respostaGabarito : respostaGab) {
            if (respostaM.getIdent().equals(respostaGabarito.getIdent())) {
                if (respostaM.getResposta_Item().equals(respostaGabarito.getResposta_Item())) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;

    }

    public int getEmblema(int num) {
        int valor = 0;
        if (num < 2) {
            valor = R.drawable.emblema_menor_que_3;//android:src="@drawable/emblema_menor_que_3"
        } else if (num == 2 || num == 3) {
            valor = R.drawable.emblema_maior_igual_a_3_menor_igual_a_7;
        } else if (num > 3) {
            valor = R.drawable.emblema_maior_que_7;
        }

        return valor;
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

         DA PARA APRIMORAR VERIFICANDO ANTES DE INSERIR EM QUAL MÓDULO ESTA, ASSIM SÓ INSERE NO LISTA O GABARITO DO MÓDULO EM QUESTÃO
         **/

        //=============== Módulo 01 Etapa 01 =============
        List<RespostaM> list = new ArrayList<>();
        RespostaM m1e1p1 = new RespostaM();
        m1e1p1.setIdent("R1P1M1E1");
        m1e1p1.setResposta_Item("A");
        list.add(m1e1p1);

        RespostaM m1e1p2 = new RespostaM();
        m1e1p2.setIdent("R2P2M1E1");
        m1e1p2.setResposta_Item("B");
        list.add(m1e1p2);

        RespostaM m1e1p3 = new RespostaM();
        m1e1p3.setIdent("R3P3M1E1");
        m1e1p3.setResposta_Item("D");
        list.add(m1e1p3);

        RespostaM m1e1p4 = new RespostaM();
        m1e1p4.setIdent("R4P4M1E1");
        m1e1p4.setResposta_Item("D");
        list.add(m1e1p4);

        RespostaM m1e1p5 = new RespostaM();
        m1e1p5.setIdent("R5P5M1E1");
        m1e1p5.setResposta_Item("D");
        list.add(m1e1p5);

        //=============== Módulo 01 Etapa 02 =============
        RespostaM m1e2p1 = new RespostaM();
        m1e2p1.setIdent("R1P1M1E2");
        m1e2p1.setResposta_Item("B");
        list.add(m1e2p1);

        RespostaM m1e2p2 = new RespostaM();
        m1e2p2.setIdent("R2P2M1E2");
        m1e2p2.setResposta_Item("D");
        list.add(m1e2p2);

        RespostaM m1e2p3 = new RespostaM();
        m1e2p3.setIdent("R3P3M1E2");
        m1e2p3.setResposta_Item("B");
        list.add(m1e2p3);

        RespostaM m1e2p4 = new RespostaM();
        m1e2p4.setIdent("R4P4M1E2");
        m1e2p4.setResposta_Item("A");
        list.add(m1e2p4);

        RespostaM m1e2p5 = new RespostaM();
        m1e2p5.setIdent("R5P5M1E2");
        m1e2p5.setResposta_Item("C");
        list.add(m1e2p5);

        //=============== Módulo 02 Etapa 01 =============
        RespostaM m2e1p1 = new RespostaM();
        m2e1p1.setIdent("R1P1M2E1");
        m2e1p1.setResposta_Item("A");
        list.add(m2e1p1);

        RespostaM m2e1p2 = new RespostaM();
        m2e1p2.setIdent("R2P2M2E1");
        m2e1p2.setResposta_Item("A");
        list.add(m2e1p2);

        RespostaM m2e1p3 = new RespostaM();
        m2e1p3.setIdent("R3P3M2E1");
        m2e1p3.setResposta_Item("B");
        list.add(m2e1p3);

        RespostaM m2e1p4 = new RespostaM();
        m2e1p4.setIdent("R4P4M2E1");
        m2e1p4.setResposta_Item("C");
        list.add(m2e1p4);

        RespostaM m2e1p5 = new RespostaM();
        m2e1p5.setIdent("R5P5M2E1");
        m2e1p5.setResposta_Item("A");
        list.add(m2e1p5);

        //=============== Módulo 02 Etapa 02 =============
        RespostaM m2e2p1 = new RespostaM();
        m2e2p1.setIdent("R1P1M2E2");
        m2e2p1.setResposta_Item("A");
        list.add(m2e2p1);

        RespostaM m2e2p2 = new RespostaM();
        m2e2p2.setIdent("R2P2M2E2");
        m2e2p2.setResposta_Item("D");
        list.add(m2e2p2);

        RespostaM m2e2p3 = new RespostaM();
        m2e2p3.setIdent("R3P3M2E2");
        m2e2p3.setResposta_Item("A");
        list.add(m2e2p3);

        RespostaM m2e2p4 = new RespostaM();
        m2e2p4.setIdent("R4P4M2E2");
        m2e2p4.setResposta_Item("A");
        list.add(m2e2p4);

        RespostaM m2e2p5 = new RespostaM();
        m2e2p5.setIdent("R5P5M2E2");
        m2e2p5.setResposta_Item("D");
        list.add(m2e2p5);

        //=============== Módulo 02 Etapa 03 =============
        RespostaM m2e3p1 = new RespostaM();
        m2e3p1.setIdent("R1P1M2E3");
        m2e3p1.setResposta_Item("C");
        list.add(m2e3p1);

        RespostaM m2e3p2 = new RespostaM();
        m2e3p2.setIdent("R2P2M2E3");
        m2e3p2.setResposta_Item("C");
        list.add(m2e3p2);

        RespostaM m2e3p3 = new RespostaM();
        m2e3p3.setIdent("R3P3M2E3");
        m2e3p3.setResposta_Item("D");
        list.add(m2e3p3);

        RespostaM m2e3p4 = new RespostaM();
        m2e3p4.setIdent("R4P4M2E3");
        m2e3p4.setResposta_Item("D");
        list.add(m2e3p4);

        RespostaM m2e3p5 = new RespostaM();
        m2e3p5.setIdent("R5P5M2E3");
        m2e3p5.setResposta_Item("D");
        list.add(m2e3p5);

        //=============== Módulo 03 Etapa 01 =============
        RespostaM m3e1p1 = new RespostaM();
        m3e1p1.setIdent("R1P1M3E1");
        m3e1p1.setResposta_Item("C");
        list.add(m3e1p1);

        RespostaM m3e1p2 = new RespostaM();
        m3e1p2.setIdent("R2P2M3E1");
        m3e1p2.setResposta_Item("D");
        list.add(m3e1p2);

        RespostaM m3e1p3 = new RespostaM();
        m3e1p3.setIdent("R3P3M3E1");
        m3e1p3.setResposta_Item("C");
        list.add(m3e1p3);

        RespostaM m3e1p4 = new RespostaM();
        m3e1p4.setIdent("R4P4M3E1");
        m3e1p4.setResposta_Item("D");
        list.add(m3e1p4);

        RespostaM m3e1p5 = new RespostaM();
        m3e1p5.setIdent("R5P5M3E1");
        m3e1p5.setResposta_Item("B");
        list.add(m3e1p5);

        //=============== Módulo 03 Etapa 02 =============
        RespostaM m3e2p1 = new RespostaM();
        m3e2p1.setIdent("R1P1M3E2");
        m3e2p1.setResposta_Item("A");
        list.add(m3e2p1);

        RespostaM m3e2p2 = new RespostaM();
        m3e2p2.setIdent("R2P2M3E2");
        m3e2p2.setResposta_Item("D");
        list.add(m3e2p2);

        RespostaM m3e2p3 = new RespostaM();
        m3e2p3.setIdent("R3P3M3E2");
        m3e2p3.setResposta_Item("A");
        list.add(m3e2p3);

        RespostaM m3e2p4 = new RespostaM();
        m3e2p4.setIdent("R4P4M3E2");
        m3e2p4.setResposta_Item("D");
        list.add(m3e2p4);

        RespostaM m3e2p5 = new RespostaM();
        m3e2p5.setIdent("R5P5M3E2");
        m3e2p5.setResposta_Item("A");
        list.add(m3e2p5);

        //=============== Módulo 04 Etapa 01 =============
        RespostaM m4e1p1 = new RespostaM();
        m4e1p1.setIdent("R1P1M4E1");
        m4e1p1.setResposta_Item("D");
        list.add(m4e1p1);

        RespostaM m4e1p2 = new RespostaM();
        m4e1p2.setIdent("R2P2M4E1");
        m4e1p2.setResposta_Item("D");
        list.add(m4e1p2);

        RespostaM m4e1p3 = new RespostaM();
        m4e1p3.setIdent("R3P3M4E1");
        m4e1p3.setResposta_Item("D");
        list.add(m4e1p3);

        RespostaM m4e1p4 = new RespostaM();
        m4e1p4.setIdent("R4P4M4E1");
        m4e1p4.setResposta_Item("B");
        list.add(m4e1p4);

        RespostaM m4e1p5 = new RespostaM();
        m4e1p5.setIdent("R5P5M4E1");
        m4e1p5.setResposta_Item("A");
        list.add(m4e1p5);

        //=============== Módulo 04 Etapa 02 =============
        RespostaM m4e2p1 = new RespostaM();
        m4e2p1.setIdent("R1P1M4E2");
        m4e2p1.setResposta_Item("D");
        list.add(m4e2p1);

        RespostaM m4e2p2 = new RespostaM();
        m4e2p2.setIdent("R2P2M4E2");
        m4e2p2.setResposta_Item("C");
        list.add(m4e2p2);

        RespostaM m4e2p3 = new RespostaM();
        m4e2p3.setIdent("R3P3M4E2");
        m4e2p3.setResposta_Item("A");
        list.add(m4e2p3);

        RespostaM m4e2p4 = new RespostaM();
        m4e2p4.setIdent("R4P4M4E2");
        m4e2p4.setResposta_Item("D");
        list.add(m4e2p4);

        RespostaM m4e2p5 = new RespostaM();
        m4e2p5.setIdent("R5P5M4E2");
        m4e2p5.setResposta_Item("C");
        list.add(m4e2p5);

        //=============== Módulo 04 Etapa 03 =============
        RespostaM m4e3p1 = new RespostaM();
        m4e3p1.setIdent("R1P1M4E3");
        m4e3p1.setResposta_Item("A");
        list.add(m4e3p1);

        RespostaM m4e3p2 = new RespostaM();
        m4e3p2.setIdent("R2P2M4E3");
        m4e3p2.setResposta_Item("D");
        list.add(m4e3p2);

        RespostaM m4e3p3 = new RespostaM();
        m4e3p3.setIdent("R3P3M4E3");
        m4e3p3.setResposta_Item("B");
        list.add(m4e3p3);

        RespostaM m4e3p4 = new RespostaM();
        m4e3p4.setIdent("R4P4M4E3");
        m4e3p4.setResposta_Item("A");
        list.add(m4e3p4);

        RespostaM m4e3p5 = new RespostaM();
        m4e3p5.setIdent("R5P5M4E3");
        m4e3p5.setResposta_Item("D");
        list.add(m4e3p5);

        return list;

    }
}
