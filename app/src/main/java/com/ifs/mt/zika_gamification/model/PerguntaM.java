package com.ifs.mt.zika_gamification.model;

import java.util.Objects;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class PerguntaM {

    private String pergunta_Id;
    private String pergunta_Nome;
    private int pergunta_Pontuacao;
    private boolean pergunta_Status;
    private RespostaM respostaM;

    public String getPergunta_Id() {
        return pergunta_Id;
    }

    public void setPergunta_Id(String pergunta_Id) {
        this.pergunta_Id = pergunta_Id;
    }

    public String getPergunta_Nome() {
        return pergunta_Nome;
    }

    public void setPergunta_Nome(String pergunta_Nome) {
        this.pergunta_Nome = pergunta_Nome;
    }

    public int getPergunta_Pontuacao() {
        return pergunta_Pontuacao;
    }

    public void setPergunta_Pontuacao(int pergunta_Pontuacao) {
        this.pergunta_Pontuacao = pergunta_Pontuacao;
    }

    public void setPergunta_Status(boolean pergunta_Status) {
        this.pergunta_Status = pergunta_Status;
    }

    public boolean isPergunta_Status() {
        return pergunta_Status;
    }

    public RespostaM getRespostaM() {
        return respostaM;
    }

    public void setRespostaM(RespostaM respostaM) {
        this.respostaM = respostaM;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entrou no equals!!!!!!");
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RespostaM other = (RespostaM) obj;
        if (!Objects.equals(this.getRespostaM().getResposta_Id(), other.getResposta_Id())) {
            return false;
        }
        if (!Objects.equals(this.getRespostaM().getResposta_Item(), other.getResposta_Item())) {
            return false;
        }
        return true;
    }
}
