package com.ifs.mt.zika_gamification.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static int VERSAO = 2;
    public final static String DB_ENDEMIA = "dbendemia";

    //Admnistrador
    public final static String TB_ADMIN = "admin";
    public final static String CREATE_TB_ADMIN = "create table IF NOT EXISTS admin (idadmin integer,  nome text,login text, senha text)";
    public final static String[] COLUMNS_ADMIN = {"idadmin", "nome", "login", "senha"};

    //Servidor
   /* public final static String TB_SERVIDOR = "servidor";
    public final static String CREATE_TB_SERVIDOR = "create table IF NOT EXISTS servidor (idservidor integer primary key autoincrement,  ip text)";
    public final static String[] COLUMNS_SERVIDOR = {"idservidor", "ip"};*/

    //Agente
    public final static String TB_AGENTE = "agente";
    public final static String CREATE_TB_AGENTE = "create table IF NOT EXISTS agente (idagente integer, idequipe integer, nome text, cargo text,  login text, senha text)";
    public final static String[] COLUMNS_AGENTE = {"idagente", "idequipe", "nome", "cargo", "login",
            "senha"};

    //Quarteirao
    public final static String TB_QUARTEIRAO = "quarteirao";
    public final static String CREATE_TB_QUARTEIRAO = "create table IF NOT EXISTS quarteirao (idqua integer, idzona integer, numero integer, sequencia text)";
    public final static String[] COLUMNS_QUARTEIRAO = {"idqua", "idzona", "numero", "sequencia"};


    //Imovel
    public final static String TB_IMOVEL = "imovel";
    public final static String CREATE_TB_IMOVEL = "create table IF NOT EXISTS imovel (idimovel integer, idquarteirao integer, numero text, logradouro text, sequencia text, complemento text, tipo text, latitude REAL, longitude REAL, lado integer )";
    public final static String[] COLUMNS_IMOVEL = {"idimovel", "idquarteirao", "numero", "logradouro", "sequencia", "complemento", "tipo", "latitude", "longitude",
            "lado"};

    //Visita
    public final static String TB_VISITA = "visita";
    public final static String CREATE_TB_VISITA = "create table visita (idvis integer primary key autoincrement,"
            + "idimovel integer, idagente integer,"
            + "isli BOOLEAN NOT NULL CHECK (isli IN (0,1)), data text, hora text, tipoatividade integer, ciclo text, "
            + "cicloano text, tipovis text, tipopendencia text, qtdhabitantes text)";
    public final static String[] COLUMNS_VISITA = {"idvis", "idagente",
            "idimovel", "isli", "data", "hora", "tipoatividade", "ciclo", "cicloano", "tipovis", "tipopendencia", "qtdhabitantes"};

    //deposito
    public final static String TB_DEPOSITO = "deposito";
    public final static String CREATE_TB_DEPOSITO = "create table deposito (iddep integer primary key autoincrement,"
            + " idvis integer, tipo text, acao text,"
            + "tipofocal text, cargafocal double, qtdlitros integer)";
    public final static String[] COLUMNS_DEPOSITO = {"iddep", "idvis", "tipo",
            "acao", "tipofocal", "cargafocal", "qtdlitros"};

    //Tubito
    public final static String TB_TUBITO = "tubito";
    public final static String CREATE_TB_TUBITO = "create table tubito (idtub integer primary key autoincrement,"
            + "iddep integer, numamostra text, numlarvas text)";
    public final static String[] COLUMNS_TUBITO = {"idtub", "iddep",
            "numamostra", "numlarvas"};

    public Banco(Context context) {
        // TODO Auto-generated constructor stub
        super(context, DB_ENDEMIA, null, VERSAO);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_TB_ADMIN);
       // db.execSQL(CREATE_TB_SERVIDOR);
        db.execSQL(CREATE_TB_AGENTE);
        db.execSQL(CREATE_TB_QUARTEIRAO);
        db.execSQL(CREATE_TB_IMOVEL);
        db.execSQL(CREATE_TB_VISITA);
        db.execSQL(CREATE_TB_DEPOSITO);
        db.execSQL(CREATE_TB_TUBITO);
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
        db.execSQL(String.format("DELETE FROM %s", Banco.TB_TUBITO));
        db.execSQL(String.format("DELETE FROM %s", Banco.TB_DEPOSITO));
        db.execSQL(String.format("DELETE FROM %s", Banco.TB_VISITA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}