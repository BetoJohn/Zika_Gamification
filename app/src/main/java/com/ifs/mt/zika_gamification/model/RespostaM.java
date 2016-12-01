package com.ifs.mt.zika_gamification.model;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class RespostaM {
    private int resposta_Id;
    private String resposta_Item;
    private boolean resposta_Correta;

    public int getResposta_Id() {
        return resposta_Id;
    }

    public void setResposta_Id(int resposta_Id) {
        this.resposta_Id = resposta_Id;
    }

    public String getResposta_Item() {
        return resposta_Item;
    }

    public void setResposta_Item(String resposta_Item) {
        this.resposta_Item = resposta_Item;
    }

    public boolean isResposta_Correta() {
        return resposta_Correta;
    }

    public void setResposta_Correta(boolean resposta_Correta) {
        this.resposta_Correta = resposta_Correta;
    }
}
