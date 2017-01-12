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
           // valoresEtapa.put("etapa_id", etapaM.getEtapa_Id());
            valoresEtapa.put("etapa_nome", etapaM.getEtapa_Nome());
            valoresEtapa.put("etapa_desricao",etapaM.getEtapa_Descricao());
            valoresEtapa.put("etapa_pontuacao",etapaM.getEtapa_Pontuacao());
            valoresEtapa.put("etapa_status",etapaM.isEtapa_Status()? 1 : 0);

            int resultadoEtapa = (int) db.getWritableDatabase().insert(Banco.TB_ETAPA, null, valoresEtapa);
            System.out.println("Inseriu TB_ETAPA");
            return resultadoEtapa;

    }

    /***
     * USUARIO - "usuario_id", "usuario_nome", "usuario_login", "usuario_email", "usuario_senha", "usuario_tipo"
     * HISTORICO - "historico_id","usuario_id","modulo_id"
     * MODULO - "modulo_id","modulo_nome","modulo_desricao","modulo_status","etapa_id"
     * ETAPA - "etapa_id", "etapa_nome", "etapa_desricao", "etapa_pontuacao", "etapa_status"
     * private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";
     * db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});
     * <p>
     * <p>
     * -- CONSULTA PELO ID DO USÚARIO E ID DO M.ÓDULO SE O MESMO JÁ FOI CONCLUÍDO PELO USÚARIO
     * select m.modulo_status, m.modulo_nome from historico h INNER JOIN modulo m ON h.modulo_id = m.modulo_id WHERE h.usuario_id = 1
     * <p>
     * -- CONSULTA PELO ID DO USÚARIO, ID DA ETAPA E ID DO MODULO SE A ETAPA JÁ FOI CONCLUÍDA PELO USÚARIO
     * select e.etapa_status, e.etapa_nome
     * from historico h INNER JOIN modulo m ON h.modulo_id = m.modulo_id INNER JOIN etapa e ON e.etapa_id = m.etapa_id
     * WHERE h.usuario_id = 1 and e.etapa_id = 'E1' and m.modulo_id = 'M1'
     */
    public int getStatusEtapaByUsuario(int usu_id, String etapa_nome, String modulo_nome) {
        System.out.println("Id do Usuário: "+usu_id);
        String query = "select e.etapa_status from historico h INNER JOIN modulo m ON h.modulo_id = m.modulo_id INNER JOIN etapa e ON e.etapa_id = m.etapa_id WHERE h.usuario_id = ? and e.etapa_nome = ? and m.modulo_nome = ?";
        Cursor rs = db.getReadableDatabase().rawQuery(query, new String[]{String.valueOf(usu_id), String.valueOf(etapa_nome), String.valueOf(modulo_nome)});
        int statusEtapa = 0;
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            statusEtapa = rs.getInt(0);
            rs.moveToNext();
        }
        //String etapaName = rs.getString(1);

        //System.out.println("Status: " + statusEtapa + " Etapa: " + etapaName);
        rs.close();

        return statusEtapa;
    }

    private List<EtapaM> cursorToList(Cursor rs) {
        //etapa_id", "etapa_nome", "etapa_desricao", "etapa_pontuacao", "etapa_status
            List<EtapaM> list = new ArrayList<>();
            rs.moveToFirst();
            while (!rs.isAfterLast()) {
                EtapaM etapaM = new EtapaM();
                etapaM.setEtapa_Id(rs.getInt(0));
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
