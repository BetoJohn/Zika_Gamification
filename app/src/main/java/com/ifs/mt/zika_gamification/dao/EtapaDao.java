package com.ifs.mt.zika_gamification.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ifs.mt.zika_gamification.model.EtapaM;
import com.ifs.mt.zika_gamification.model.ModuloM;
import com.ifs.mt.zika_gamification.model.PerguntaM;
import com.ifs.mt.zika_gamification.model.UsuarioM;

import java.util.ArrayList;
import java.util.List;

public class EtapaDao {

    private SQLiteDatabase sqldb;
    private SQLiteOpenHelper db;
    private String[] parametros;

    public EtapaDao(SQLiteOpenHelper db) {
        this.db = db;
        this.sqldb = db.getWritableDatabase();
    }

    public void apagaRegistrosTabela() {
        //sqldb.execSQL("DROP TABLE IF EXISTS " +  Banco.TB_USUARIO);
        sqldb.execSQL(String.format("DELETE FROM %s", Banco.TB_ETAPA));
    }

    public int insert(EtapaM etapaM) {
        /**
         * "etapa_id", "etapa_nome", "etapa_desricao", "etapa_status", "pergunta_id"
         **/
        ContentValues valoresEtapa = new ContentValues();
       // Banco bancoPergunta;
            valoresEtapa.put("etapa_id", etapaM.getEtapa_Id());
            valoresEtapa.put("etapa_nome", etapaM.getEtapa_Nome());
            valoresEtapa.put("etapa_desricao",etapaM.getEtapa_Descricao());
            valoresEtapa.put("etapa_pontuacao",etapaM.getEtapa_Pontuacao());
            valoresEtapa.put("etapa_status",etapaM.isEtapa_Status()? 1 : 0);

            int resultadoEtapa = (int) db.getWritableDatabase().insert(Banco.TB_ETAPA, null, valoresEtapa);
            System.out.println("Inseriu TB_ETAPA");
            return resultadoEtapa;

    }

    public EtapaM getStatus(EtapaM etapaM) {
        try {
            parametros = new String[]{"E1"};
            Cursor rs = db.getReadableDatabase().query(Banco.TB_ETAPA, Banco.COLUMNS_ETAPA, "etapa_id = ?",
                    parametros, null, null, null);
            List<EtapaM> list = cursorToList(rs);
            rs.close();
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<EtapaM> cursorToList(Cursor rs) {
        //etapa_id", "etapa_nome", "etapa_desricao", "etapa_pontuacao", "etapa_status
            List<EtapaM> list = new ArrayList<>();
            rs.moveToFirst();
            while (!rs.isAfterLast()) {
                EtapaM etapaM = new EtapaM();
                etapaM.setEtapa_Id(rs.getString(0));
                etapaM.setEtapa_Nome(rs.getString(1));
                etapaM.setEtapa_Descricao(rs.getString(2));
                etapaM.setEtapa_Pontuacao(rs.getInt(3));
                etapaM.setEtapa_Status(rs.getInt(4) == 0 ? false : true);

                list.add(etapaM);
                rs.moveToNext();
            }
            //}
            return list;
        }



}
