package com.ifs.mt.zika_gamification.model;

import java.util.List;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class EtapaM {
    private String etapa_Id;
    private String etapa_Nome;
    private String etapa_Descricao;
    private int etapa_Pontuacao;
    private boolean etapa_Status;
   // private List<PerguntaM> perguntas;

    public String getEtapa_Id() {
        return etapa_Id;
    }

    public void setEtapa_Id(String etapa_Id) {
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

    public int getEtapa_Pontuacao() {
        return etapa_Pontuacao;
    }

    public void setEtapa_Pontuacao(int etapa_Pontuacao) {
        this.etapa_Pontuacao = etapa_Pontuacao;
    }

    public void setEtapa_Status(boolean etapa_Status) {
        this.etapa_Status = etapa_Status;
    }

    public boolean isEtapa_Status() {
        return etapa_Status;
    }

  /*  public List<PerguntaM> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntaM> perguntas) {
        this.perguntas = perguntas;
    }*/
}
