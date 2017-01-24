package com.ifs.mt.zika_gamification.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.ifs.mt.zika_gamification.model.PerguntaM;

import java.util.List;

public class Banco extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static int VERSAO = 2;
    public final static String DB_ZIKA = "dbzika";

    //UsuarioM
    public final static String TB_USUARIO = "usuario";
    public final static String CREATE_TB_USUARIO = "create table IF NOT EXISTS usuario (usuario_id integer PRIMARY KEY AUTOINCREMENT, usuario_nome text, usuario_login text,   usuario_senha text,  usuario_tipo text)";
    public final static String[] COLUMNS_USUARIO = {"usuario_id", "usuario_nome", "usuario_login",  "usuario_senha", "usuario_tipo"};


    //HistoricoM
    public final static String TB_HISTORICO = "historico";
    public final static String CREATE_TB_HISTORICO = "create table IF NOT EXISTS historico (historico_id integer PRIMARY KEY AUTOINCREMENT, usuario_id integer, modulo_id text, FOREIGN KEY(usuario_id) REFERENCES usuario(usuario_id))";
    public final static String[] COLUMNS_HISTORICO = {"historico_id", "usuario_id", "modulo_id"};

    //ModuloM
    public final static String TB_MODULO = "modulo";
    public final static String CREATE_TB_MODULO = "create table IF NOT EXISTS modulo (modulo_id integer PRIMARY KEY AUTOINCREMENT, modulo_nome text, modulo_descricao text, modulo_status BOOLEAN NOT NULL CHECK (modulo_status IN (0,1)), etapa_id text, FOREIGN KEY(etapa_id) REFERENCES etapa(etapa_id))";
    public final static String[] COLUMNS_MODULO = {"modulo_id", "modulo_nome", "modulo_descricao", "modulo_status", "etapa_id"};


    //EtapaM
    public final static String TB_ETAPA = "etapa";
    public final static String CREATE_TB_ETAPA = "create table IF NOT EXISTS etapa (etapa_id integer PRIMARY KEY AUTOINCREMENT, etapa_nome text, etapa_desricao text, etapa_pontuacao integer, etapa_status BOOLEAN NOT NULL CHECK (etapa_status IN (0,1)))";
    public final static String[] COLUMNS_ETAPA = {"etapa_id", "etapa_nome", "etapa_desricao", "etapa_pontuacao", "etapa_status"};

    //PerguntaM
    public final static String TB_PERGUNTA = "pergunta";
    public final static String CREATE_TB_PERGUNTA = "create table IF NOT EXISTS pergunta (pergunta_id integer PRIMARY KEY AUTOINCREMENT, pergunta_nome text, pergunta_status BOOLEAN NOT NULL CHECK (pergunta_status IN (0,1)), resposta_id text, etapa_id text)";
    public final static String[] COLUMNS_PERGUNTA = {"pergunta_id", "pergunta_nome", "pergunta_status", "resposta_id", "etapa_id"};


    //RespostaM
    public final static String TB_RESPOSTA = "resposta";
    public final static String CREATE_TB_RESPOSTA = "create table IF NOT EXISTS resposta (resposta_id integer PRIMARY KEY AUTOINCREMENT, resposta_item text, resposta_correta boolean)";
    public final static String[] COLUMNS_RESPOSTA = {"resposta_id", "resposta_item", "resposta_correta"};

    //StatusM
    public final static String TB_STATUS = "status";
    public final static String CREATE_TB_STATUS = "create table IF NOT EXISTS status (status_id integer PRIMARY KEY AUTOINCREMENT, usuario_id integer, usuario_nome text, pontuacao integer, nivel integer, experiencia integer, modulo_01_status BOOLEAN NOT NULL CHECK (modulo_01_status IN (0,1)), modulo_02_status BOOLEAN NOT NULL CHECK (modulo_02_status IN (0,1)), modulo_03_status BOOLEAN NOT NULL CHECK (modulo_03_status IN (0,1)), modulo_04_status BOOLEAN NOT NULL CHECK (modulo_04_status IN (0,1)))";
    public final static String[] COLUMNS_STATUS = {"status_id", "usuario_id", "usuario_nome", "pontuacao", "nivel", "experiencia", "modulo_01_status", "modulo_02_status", "modulo_03_status", "modulo_04_status"};


    public Banco(Context context) {
        // TODO Auto-generated constructor stub
        super(context, DB_ZIKA, null, VERSAO);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_TB_USUARIO);
        db.execSQL(CREATE_TB_HISTORICO);
        db.execSQL(CREATE_TB_ETAPA);
        db.execSQL(CREATE_TB_RESPOSTA);
        db.execSQL(CREATE_TB_MODULO);
        db.execSQL(CREATE_TB_PERGUNTA);
        db.execSQL(CREATE_TB_STATUS);
    }

    public boolean tabelaExists(String tabela) {
        boolean tabelaExiste = false;
        try {
            @SuppressWarnings("unused")
            Cursor cursor = db.query(tabela, null, null, null, null, null, null);
            tabelaExiste = true;
        } catch (SQLiteException e) {
            if (e.getMessage().toString().contains("no such table")) {
                tabelaExiste = false;
            }
        }
        return tabelaExiste;
    }


    public void limparDadosVisitas() {
        db.execSQL(String.format("DELETE FROM %s", Banco.TB_USUARIO));
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}