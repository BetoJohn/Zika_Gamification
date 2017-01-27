package com.ifs.mt.zika_gamification.model;

/**
 * Created by Betto Silva on 23/01/2017.
 */

public class StatusM implements Comparable<StatusM>{

    /*"status_id", "usuario_id",
    "modulo_01_status", "modulo_02_status",
    "modulo_03_status", "modulo_04_status"*/

    private int status_id;
    private int usuario_id;
    private String usuario_nome;
    private int pontuacao;
    private int nivel;
    private int experiencia;
    private boolean modulo_01_status;
    private boolean modulo_02_status;
    private boolean modulo_03_status;
    private boolean modulo_04_status;

    //Necessário para ordenar a expandablelistview
    @Override
    public int compareTo(StatusM o) {
        int compareExp = ((StatusM) o).getExperiencia();

        //Se tiverem mesma experiencia verifica pelos pontos
        if(compareExp == this.experiencia){
            int comparePontuacao = ((StatusM) o).getPontuacao();
            return comparePontuacao - this.pontuacao;
        }
        //descending order
        return compareExp - this.experiencia;
        //ascending order
        //return this.experiencia - compareExp;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public boolean isModulo_01_status() {
        return modulo_01_status;
    }

    public void setModulo_01_status(boolean modulo_01_status) {
        this.modulo_01_status = modulo_01_status;
    }

    public boolean isModulo_02_status() {
        return modulo_02_status;
    }

    public void setModulo_02_status(boolean modulo_02_status) {
        this.modulo_02_status = modulo_02_status;
    }

    public boolean isModulo_03_status() {
        return modulo_03_status;
    }

    public void setModulo_03_status(boolean modulo_03_status) {
        this.modulo_03_status = modulo_03_status;
    }

    public boolean isModulo_04_status() {
        return modulo_04_status;
    }

    public void setModulo_04_status(boolean modulo_04_status) {
        this.modulo_04_status = modulo_04_status;
    }
}
