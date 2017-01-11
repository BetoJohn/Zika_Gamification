package com.ifs.mt.zika_gamification.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.HistoricoDao;
import com.ifs.mt.zika_gamification.dao.ModuloDao;
import com.ifs.mt.zika_gamification.telas.Login;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.M1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class ModulosViewHolders extends RecyclerView.ViewHolder {
    public View view;
    private Banco banco;
    public ImageView countryPhoto;
    public int currentItem;
    //private boolean m1 = true, m2 = false, m3 = false, m4 = false;

    public ModulosViewHolders(View v) {
        super(v);
        view = v;
        // countryName = (TextView) itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView) view.findViewById(R.id.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                banco = new Banco(v.getContext());
                ModuloDao moduloDao = new ModuloDao(banco);

                switch (currentItem) {
                    case 0:
                        int statusModulo01 = moduloDao.getStatusModuloByUsuario(Login.getUsuarioLogado().getUsuario_id(), "M1");
                        if (statusModulo01 != 1) {
                            v.getContext().startActivity(new Intent(v.getContext(), M1.class));
                            break;
                        } else {
                            Toast.makeText(v.getContext(), "Módulo Concluído", Toast.LENGTH_SHORT).show();
                            break;
                        }

                }

            }
        });
    }


}



