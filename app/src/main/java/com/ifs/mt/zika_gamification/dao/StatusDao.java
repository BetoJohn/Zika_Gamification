package com.ifs.mt.zika_gamification.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ifs.mt.zika_gamification.model.StatusM;
import com.ifs.mt.zika_gamification.model.UsuarioM;

import java.util.ArrayList;
import java.util.List;

public class StatusDao {

    private SQLiteDatabase sqldb;
    private SQLiteOpenHelper db;
    private String[] parametros;

    public StatusDao(SQLiteOpenHelper db) {
        this.db = db;
        this.sqldb = db.getWritableDatabase();
    }

    public void apagaRegistrosTabela() {
        //sqldb.execSQL("DROP TABLE IF EXISTS " +  Banco.TB_USUARIO);
        sqldb.execSQL(String.format("DELETE FROM %s", Banco.TB_STATUS));
    }

    public int insert(StatusM status) {
        /**
         "status_id", "usuario_id", "usuario_nome", "pontuacao", "nivel", "experiencia", "modulo_01_status", "modulo_02_status", "modulo_03_status", "modulo_04_status"

         **/
        ContentValues valores = new ContentValues();
        valores.put("usuario_id", status.getUsuario_id());
        valores.put("usuario_nome", status.getUsuario_nome());
        valores.put("pontuacao", status.getPontuacao());
        valores.put("nivel", status.getNivel());
        valores.put("experiencia", status.getExperiencia());
        valores.put("modulo_01_status", status.isModulo_01_status() ? 1 : 0);
        valores.put("modulo_02_status", status.isModulo_02_status() ? 1 : 0);
        valores.put("modulo_03_status", status.isModulo_03_status() ? 1 : 0);
        valores.put("modulo_04_status", status.isModulo_04_status() ? 1 : 0);
        int result = (int) db.getWritableDatabase().insert(Banco.TB_STATUS, null, valores);
        System.out.println("Inseriu StatusM id: "+result);

        return result;
    }

    public int update(StatusM statusM) {
        try {
            parametros = new String[]{String.valueOf(statusM.getUsuario_id())};
            ContentValues valores = new ContentValues();
            valores.put("pontuacao", statusM.getPontuacao());
            valores.put("nivel", statusM.getNivel());
            valores.put("experiencia", statusM.getExperiencia());
            valores.put("modulo_01_status", statusM.isModulo_01_status() ? 1 : 0);
            valores.put("modulo_02_status", statusM.isModulo_02_status() ? 1 : 0);
            valores.put("modulo_03_status", statusM.isModulo_03_status() ? 1 : 0);
            valores.put("modulo_04_status", statusM.isModulo_04_status() ? 1 : 0);

            int updatedCount = db.getWritableDatabase().update(Banco.TB_STATUS, valores,
                    "usuario_id = ?", parametros);

            return updatedCount;

        } catch (Exception e) {
            System.out.println("Exceção do updateStatus: " + e.getMessage());
        }
        return 0;
    }


    public boolean isEmptyTable() {
        Cursor rs = db.getReadableDatabase().query(Banco.TB_STATUS, Banco.COLUMNS_STATUS, null,
                null, null, null, "usuario_id");
        return !rs.moveToFirst();
    }

    public StatusM getStatusByUsuario(int usu_id) {
        String query = "select * from status WHERE usuario_id = ?";
        Cursor rs = db.getReadableDatabase().rawQuery(query, new String[]{String.valueOf(usu_id)});
        List<StatusM> list = cursorToList(rs);
        rs.close();
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<StatusM> getAll() {
        Cursor rs = db.getReadableDatabase().query(Banco.TB_STATUS, Banco.COLUMNS_STATUS, null,
                null, null, null, "usuario_nome");
        List<StatusM> list = cursorToList(rs);
        rs.close();
        return list;
    }


    private List<StatusM> cursorToList(Cursor rs) {
        /**
         "status_id", "usuario_id", "usuario_nome", "pontuacao", "nivel", "experiencia", "modulo_01_status", "modulo_02_status", "modulo_03_status", "modulo_04_status"

         **/
        List<StatusM> list = new ArrayList<StatusM>();
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            StatusM statusM = new StatusM();
            statusM.setStatus_id(rs.getInt(0));
            statusM.setUsuario_id(rs.getInt(1));
            statusM.setUsuario_nome(rs.getString(2));
            statusM.setPontuacao(rs.getInt(3));
            statusM.setNivel(rs.getInt(4));
            statusM.setExperiencia(rs.getInt(5));
            statusM.setModulo_01_status(rs.getInt(6) == 0 ? false : true);
            statusM.setModulo_02_status(rs.getInt(7) == 0 ? false : true);
            statusM.setModulo_03_status(rs.getInt(8) == 0 ? false : true);
            statusM.setModulo_04_status(rs.getInt(9) == 0 ? false : true);
            list.add(statusM);
            rs.moveToNext();
        }
        //}
        return list;
    }

}
