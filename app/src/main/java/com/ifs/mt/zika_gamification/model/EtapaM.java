package com.ifs.mt.zika_gamification.model;

import java.util.List;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class EtapaM {
    private int etapa_Id;
    private String etapa_Nome;
    private String etapa_Descricao;
    private String etapa_Status;
    private List<PerguntaM> perguntas;

    public int getEtapa_Id() {
        return etapa_Id;
    }

    public void setEtapa_Id(int etapa_Id) {
        this.etapa_Id = etapa_Id;
    }

    public String getEtapa_Nome() {
        return etapa_Nome;
    }

    public void setEtapa_Nome(String etapa_Nome) {
        this.etapa_Nome = etapa_Nome;
    }

    public String getEtapa_Descricao() {
        return etapa_Descricao;
    }

    public void setEtapa_Descricao(String etapa_Descricao) {
        this.etapa_Descricao = etapa_Descricao;
    }

    public String getEtapa_Status() {
        return etapa_Status;
    }

    public void setEtapa_Status(String etapa_Status) {
        this.etapa_Status = etapa_Status;
    }
}
