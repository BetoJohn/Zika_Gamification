package com.ifs.mt.zika_gamification.model;

/**
 * Created by Beto on 05/08/2016.
 */
public class UsuarioM {

    private int usuario_id;
    private int historico_Id;
    private String usuario_nome;
    private String usuario_login;
    private String usuario_email;
    private String usuario_senha;
    private int tipo_status_id;
    private int tipo_usuario_id;

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
    public String getUsuario_login() {
        return usuario_login;
    }
    public void setUsuario_login(String usuario_login) {
        this.usuario_login = usuario_login;
    }
    public String getUsuario_email() {
        return usuario_email;
    }
    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }
    public String getUsuario_senha() {
        return usuario_senha;
    }
    public void setUsuario_senha(String usuario_senha) {
        this.usuario_senha = usuario_senha;
    }
    public int getTipo_status_id() {
        return tipo_status_id;
    }
    public void setTipo_status_id(int tipo_status_id) {
        this.tipo_status_id = tipo_status_id;
    }
    public int getTipo_usuario_id() {
        return tipo_usuario_id;
    }
    public void setTipo_usuario_id(int tipo_usuario_id) {
        this.tipo_usuario_id = tipo_usuario_id;
    }

    public int getHistorico_Id() {
        return historico_Id;
    }

    public void setHistorico_Id(int historico_Id) {
        this.historico_Id = historico_Id;
    }
}

