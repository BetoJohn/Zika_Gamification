package com.ifs.mt.zika_gamification.model;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class PerguntaM {

    private int pergunta_Id;
    private int resposta_Id;
    private String pergunta_Nome;
    private String pergunta_Questao;
    private int pergunta_Pontuacao;
    private String pergunta_Status;

    public int getPergunta_Id() {
        return pergunta_Id;
    }

    public void setPergunta_Id(int pergunta_Id) {
        this.pergunta_Id = pergunta_Id;
    }

    public String getPergunta_Nome() {
        return pergunta_Nome;
    }

    public void setPergunta_Nome(String pergunta_Nome) {
        this.pergunta_Nome = pergunta_Nome;
    }

    public String getPergunta_Questao() {
        return pergunta_Questao;
    }

    public void setPergunta_Questao(String pergunta_Questao) {
        this.pergunta_Questao = pergunta_Questao;
    }

    public int getPergunta_Pontuacao() {
        return pergunta_Pontuacao;
    }

    public void setPergunta_Pontuacao(int pergunta_Pontuacao) {
        this.pergunta_Pontuacao = pergunta_Pontuacao;
    }

    public String getPergunta_Status() {
        return pergunta_Status;
    }

    public void setPergunta_Status(String pergunta_Status) {
        this.pergunta_Status = pergunta_Status;
    }

    public int getResposta_Id() {
        return resposta_Id;
    }

    public void setResposta_Id(int resposta_Id) {
        this.resposta_Id = resposta_Id;
    }
}
