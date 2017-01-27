package com.ifs.mt.zika_gamification.model;

/**
 * Created by Beto on 05/08/2016.
 */
public class UsuarioM {

    private int usuario_id;
    private String usuario_uid;
    private String usuario_nome;
    private String usuario_login;
    private String usuario_senha;
    private String usuario_tipo;

    public int getUsuario_id() {
        return usuario_id;
    }
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_uid() {
        return usuario_uid;
    }

    public void setUsuario_uid(String usuario_uid) {
        this.usuario_uid = usuario_uid;
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
   /* public String getUsuario_email() {
        return usuario_email;
    }
    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }*/
    public String getUsuario_senha() {
        return usuario_senha;
    }
    public void setUsuario_senha(String usuario_senha) {
        this.usuario_senha = usuario_senha;
    }

    public String getUsuario_tipo() {
        return usuario_tipo;
    }

    public void setUsuario_tipo(String usuario_tipo) {
        this.usuario_tipo = usuario_tipo;
    }
}

