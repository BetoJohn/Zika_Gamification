package com.ifs.mt.zika_gamification.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ifs.mt.zika_gamification.model.RespostaM;
import com.ifs.mt.zika_gamification.model.UsuarioM;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private SQLiteDatabase sqldb;
    private SQLiteOpenHelper db;
    private String[] parametros;

    public UsuarioDao(SQLiteOpenHelper db) {
        this.db = db;
        this.sqldb = db.getWritableDatabase();
    }

    public void apagaRegistrosTabela() {
        //sqldb.execSQL("DROP TABLE IF EXISTS " +  Banco.TB_USUARIO);
        sqldb.execSQL(String.format("DELETE FROM %s", Banco.TB_USUARIO));
    }

    public int insert(UsuarioM usu) {
        /**
         * "usuario_id", "usuario_nome", "usuario_login", "usuario_email", "usuario_senha", "usuario_tipo"
         *
         **/
        ContentValues valores = new ContentValues();
        valores.put("usuario_nome", usu.getUsuario_nome());
        valores.put("usuario_login", usu.getUsuario_login());
        valores.put("usuario_email", usu.getUsuario_email());
        valores.put("usuario_senha", usu.getUsuario_senha());
        valores.put("usuario_tipo", usu.getUsuario_tipo());
        int result = (int) db.getWritableDatabase().insert(Banco.TB_USUARIO, null, valores);
        System.out.println("Id tubito no insert: " + result);
        System.out.println("Inseriu UsuarioM");

        return result;
    }

    public boolean isEmptyTable() {
        Cursor rs = db.getReadableDatabase().query(Banco.TB_USUARIO, Banco.COLUMNS_USUARIO, null,
                null, null, null, "usuario_nome");
        return !rs.moveToFirst();
    }

    //DANDO ERRO AO CONVERTER  SENHA
    public UsuarioM autenticacao(UsuarioM usu) {
        //parametros = new String[]{agente.getLogin(), convertStringToMd5(agente.getSenha())};
        parametros = new String[]{usu.getUsuario_login(), usu.getUsuario_senha()};
        Cursor rs = db.getReadableDatabase().query(Banco.TB_USUARIO, Banco.COLUMNS_USUARIO, "usuario_login = ? and usuario_senha = ?",
                parametros, null, null, "usuario_nome");
        List<UsuarioM> list = cursorToList(rs);
        System.out.println("List na Autenticação " + list.size());
        rs.close();
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<UsuarioM> cursorToList(Cursor rs) {
        List<UsuarioM> list = new ArrayList<UsuarioM>();
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            UsuarioM usu = new UsuarioM();
            usu.setUsuario_id(rs.getInt(0));
            usu.setUsuario_nome(rs.getString(1));
            usu.setUsuario_login(rs.getString(2));
            usu.setUsuario_email(rs.getString(3));
            usu.setUsuario_senha(rs.getString(4));
            usu.setUsuario_tipo(rs.getString(5));
            list.add(usu);
            rs.moveToNext();
        }
        //}
        return list;
    }


    public List<UsuarioM> getAll() {
        Cursor rs = db.getReadableDatabase().query(Banco.TB_USUARIO, Banco.COLUMNS_USUARIO, null,
                null, null, null, "usuario_nome");
        List<UsuarioM> list = cursorToList(rs);
        rs.close();
        return list;
    }



    /*private String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            //Instanciamos o nosso HASH MD5, poder�amos usar outro como 
            //SHA, por exemplo, mas optamos por MD5. 
            mDigest = MessageDigest.getInstance("MD5");
            //Convert a String valor para um array de bytes em MD5 
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
            //Convertemos os bytes para hexadecimal, assim podemos salvar 
            //no banco para posterior compara��o de senhas 
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
