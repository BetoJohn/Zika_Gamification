package com.ifs.mt.zika_gamification.model;

/**
 * Created by Beto on 05/08/2016.
 */
public class UsuarioM {

    private int usuario_id;
    private String usuario_nome;
    private String usuario_login;
    private String usuario_email;
    private String usuario_senha;
    private String usuario_tipo_status;
    private String usuario_tipo_usuario;

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

    public String getUsuario_tipo_status() {
        return usuario_tipo_status;
    }

    public void setUsuario_tipo_status(String usuario_tipo_status) {
        this.usuario_tipo_status = usuario_tipo_status;
    }

    public String getUsuario_tipo_usuario() {
        return usuario_tipo_usuario;
    }

    public void setUsuario_tipo_usuario(String usuario_tipo_usuario) {
        this.usuario_tipo_usuario = usuario_tipo_usuario;
    }
}

