package com.ifs.mt.zika_gamification.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.EtapaDao;
import com.ifs.mt.zika_gamification.telas.Login;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.etapa_1.M3E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.etapa_2.M3E2;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class EtapasM3ViewHolders extends RecyclerView.ViewHolder {
    public View view;
    public ImageView imageViewEtapa;
    public int currentItem;
    private Banco banco;

    public EtapasM3ViewHolders(View v) {
        super(v);
        view = v;
        imageViewEtapa = (ImageView) view.findViewById(R.id.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                banco = new Banco(v.getContext());
                EtapaDao etapaDao = new EtapaDao(banco);

                int statusEtapa01 = 0;
                int statusEtapa02 = 0;
                switch (currentItem) {
                    case 0:
                        //Primeira etapa por padrão tem que estar ativa
                        statusEtapa01 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E1M3", "M3");
                        if (statusEtapa01 != 1) {
                            v.getContext().startActivity(new Intent(v.getContext(), M3E1.class));
                            break;
                        } else {
                            Toast.makeText(v.getContext(), "Etapa 01 Concluída", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    case 1:
                        statusEtapa01 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E1M3", "M3");
                        statusEtapa02 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E2M3", "M3");
                        if (statusEtapa02 != 1 && statusEtapa01 == 1) {
                            v.getContext().startActivity(new Intent(v.getContext(), M3E2.class));
                            break;
                        } if (statusEtapa02 == 1){
                            Toast.makeText(v.getContext(), "Etapa 02 Concluída", Toast.LENGTH_SHORT).show();
                            break;
                        }

                }

            }
        });
    }


}



