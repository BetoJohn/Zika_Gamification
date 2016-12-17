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
    /**
     *
     private int usuario_id;
     private String usuario_nome;
     private String usuario_login;
     private String usuario_email;
     private String usuario_senha;
     private int tipo_status_id;
     private int tipo_usuario_id;
     *
     * FOREIGN KEY(trackartist) REFERENCES artist(artistid)
     * **/

    //UsuarioM
    public final static String TB_USUARIO = "usuario";
    public final static String CREATE_TB_USUARIO = "create table IF NOT EXISTS usuario (usuario_id integer PRIMARY KEY AUTOINCREMENT, usuario_nome text, usuario_login text,  usuario_email text, usuario_senha text,  usuario_tipo text)";
    public final static String[] COLUMNS_USUARIO = {"usuario_id", "usuario_nome", "usuario_login", "usuario_email", "usuario_senha",  "usuario_tipo"};


    //HistoricoM
    public final static String TB_HISTORICO = "historico";
    public final static String CREATE_TB_HISTORICO = "create table IF NOT EXISTS historico (historico_id integer PRIMARY KEY AUTOINCREMENT, usuario_id integer, modulo_id text, FOREIGN KEY(usuario_id) REFERENCES usuario(usuario_id))";
    public final static String[] COLUMNS_HISTORICO = {"historico_id", "usuario_id", "modulo_id"};

    //ModuloM
    public final static String TB_MODULO = "modulo";
    public final static String CREATE_TB_MODULO = "create table IF NOT EXISTS modulo (modulo_id text, modulo_nome text, modulo_desricao text, modulo_status boolean, etapa_id text, FOREIGN KEY(etapa_id) REFERENCES etapa(etapa_id))";
    public final static String[] COLUMNS_MODULO = {"modulo_id", "modulo_nome", "modulo_desricao", "modulo_status", "etapa_id"};


    //EtapaM
    public final static String TB_ETAPA = "etapa";
    public final static String CREATE_TB_ETAPA = "create table IF NOT EXISTS etapa (etapa_id text, etapa_nome text, etapa_desricao text, etapa_status boolean, pergunta_id text, FOREIGN KEY(pergunta_id) REFERENCES pergunta(pergunta_id))";
    public final static String[] COLUMNS_ETAPA = {"etapa_id", "etapa_nome", "etapa_desricao", "etapa_status", "pergunta_id"};


    //Visita
    public final static String TB_VISITA = "visita";
    public final static String CREATE_TB_VISITA = "create table visita (idvis integer primary key autoincrement,"
            + "idimovel integer, idagente integer,"
            + "isli BOOLEAN NOT NULL CHECK (isli IN (0,1)), data text, hora text, tipoatividade integer, ciclo text, "
            + "cicloano text, tipovis text, tipopendencia text, qtdhabitantes text)";
    public final static String[] COLUMNS_VISITA = {"idvis", "idagente",
            "idimovel", "isli", "data", "hora", "tipoatividade", "ciclo", "cicloano", "tipovis", "tipopendencia", "qtdhabitantes"};

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