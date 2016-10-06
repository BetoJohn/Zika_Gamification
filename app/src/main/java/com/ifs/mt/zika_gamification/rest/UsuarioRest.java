package com.ifs.mt.zika_gamification.rest;

import android.content.Context;

import com.google.gson.Gson;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.UsuarioDao;
import com.ifs.mt.zika_gamification.model.UsuarioM;

/**
 * Created by Beto on 05/08/2016.
 */
public class UsuarioRest {
    private static final String URL_WS = "/WebServiceControleDengue/webresources/controledenguews/usuario/get/";


    public UsuarioM autenticar(UsuarioM agente, Context cont) throws Exception {

        Banco banco = new Banco(cont);
       UsuarioDao usuDao = new UsuarioDao(banco);

        if (usuDao.isEmptyTable()) {
           // agenteWS(agente, agenteDao, cont);
        }
        //DANDO ERRO AO CONVERTER A SENHA na classe dao
        UsuarioM usuRetorno = usuDao.autenticacao(agente);
        if (null == usuRetorno) {
            usuarioWS(agente, usuDao, cont);
            usuRetorno = usuDao.autenticacao(agente);
        }
        System.out.println("Valor do agenteRetorno " + usuRetorno);
        return usuRetorno;

    }

    public void usuarioWS(UsuarioM agente, UsuarioDao usuDao, Context cont) throws Exception {


        String ipRetorno = "Pegar ip";//recuperaIpServidor(cont);
        String portaRetorno = "Pegar porta";//recuperaPortaServidor(cont);
        String ip = "http://" + ipRetorno+":"+portaRetorno;

        if (null != ipRetorno) {
            System.out.println("Valor da url: " + ip + URL_WS
                    + agente.getUsuario_login() + "/" + agente.getUsuario_senha());


            String[] resposta = new WebServiceRestClient().get(ip + URL_WS
                    + agente.getUsuario_login() + "/" + agente.getUsuario_senha());
            System.out.println("Resposta do WebServiceRestClient: " + resposta[0]);
            if ("200".equals(resposta[0]) && null != resposta[1]) {
                Gson gson = new Gson();
                UsuarioM agt = gson.fromJson(resposta[1], UsuarioM.class);

                //se for diferente de null insere no banco local
                if (null != agt)
                    usuDao.insert(agt);
                System.out.println("Lista de agentes atualizada! " + agt);
            }
        }


    }
}
