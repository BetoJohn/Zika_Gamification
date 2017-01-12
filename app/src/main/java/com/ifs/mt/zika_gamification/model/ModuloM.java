package com.ifs.mt.zika_gamification.model;

import java.util.List;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class ModuloM {
    private int modulo_Id;
    private String modulo_Nome;
    private String modulo_Desricao;
    private boolean modulo_Status;
    private EtapaM etapa;

    public int getModulo_Id() {
        return modulo_Id;
    }

    public void setModulo_Id(int modulo_Id) {
        this.modulo_Id = modulo_Id;
    }

    public String getModulo_Nome() {
        return modulo_Nome;
    }

    public void setModulo_Nome(String modulo_Nome) {
        this.modulo_Nome = modulo_Nome;
    }

    public String getModulo_Desricao() {
        return modulo_Desricao;
    }

    public void setModulo_Desricao(String modulo_Desricao) {
        this.modulo_Desricao = modulo_Desricao;
    }

    public boolean isModulo_Status() {
        return modulo_Status;
    }

    public void setModulo_Status(boolean modulo_Status) {
        this.modulo_Status = modulo_Status;
    }

    public EtapaM getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaM etapas) {
        this.etapa = etapas;
    }
}
