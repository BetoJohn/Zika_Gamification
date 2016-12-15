package com.ifs.mt.zika_gamification.model;

import java.util.List;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class HistoricoM {

    private int historico_Id;
    private UsuarioM usuarioM;
    private ModuloM moduloM;

    public int getHistorico_Id() {
        return historico_Id;
    }

    public void setHistorico_Id(int historico_Id) {
        this.historico_Id = historico_Id;
    }

    public ModuloM getModuloM() {
        return moduloM;
    }

    public void setModuloM(ModuloM moduloM) {
        this.moduloM = moduloM;
    }

    public UsuarioM getUsuarioM() {
        return usuarioM;
    }

    public void setUsuarioM(UsuarioM usuarioM) {
        this.usuarioM = usuarioM;
    }
}
