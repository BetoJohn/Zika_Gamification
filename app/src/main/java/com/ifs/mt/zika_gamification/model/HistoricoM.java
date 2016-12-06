package com.ifs.mt.zika_gamification.model;

import java.util.List;

/**
 * Created by Betto Silva on 01/12/2016.
 */
public class HistoricoM {

    private UsuarioM usuarioM;
    private int historico_Id;
    //private int modulo_Id;
    private List<ModuloM> modulos;

    public int getHistorico_Id() {
        return historico_Id;
    }

    public void setHistorico_Id(int historico_Id) {
        this.historico_Id = historico_Id;
    }

   /* public int getModulo_Id() {
        return modulo_Id;
    }

    public void setModulo_Id(int modulo_Id) {
        this.modulo_Id = modulo_Id;
    }*/

    public List<ModuloM> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloM> modulos) {
        this.modulos = modulos;
    }

    public UsuarioM getUsuarioM() {
        return usuarioM;
    }

    public void setUsuarioM(UsuarioM usuarioM) {
        this.usuarioM = usuarioM;
    }
}
