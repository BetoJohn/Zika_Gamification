package com.ifs.mt.zika_gamification.model;
import java.util.Objects;
/**
 * Created by Betto Silva on 01/12/2016.
 */
public class RespostaM {

    private String resposta_Id;
    private String resposta_Item;
    private boolean resposta_Correta;

    public String getResposta_Id() {
        return resposta_Id;
    }

    public void setResposta_Id(String resposta_Id) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.resposta_Id);
        hash = 79 * hash + Objects.hashCode(this.resposta_Item);
        return hash;
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
        if (!Objects.equals(this.resposta_Id, other.resposta_Id)) {
            return false;
        }
        if (!Objects.equals(this.resposta_Item, other.resposta_Item)) {
            return false;
        }
        return true;
    }
}
