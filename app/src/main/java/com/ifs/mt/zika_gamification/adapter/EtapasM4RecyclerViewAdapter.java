package com.ifs.mt.zika_gamification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.EtapaDao;
import com.ifs.mt.zika_gamification.telas.Login;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class EtapasM4RecyclerViewAdapter extends RecyclerView.Adapter<EtapasM4ViewHolders> {

    private Context context;
    private Banco banco;

    public EtapasM4RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EtapasM4ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.etapas_list_card, null);
        //Faz as chamadas dos cliques
        EtapasM4ViewHolders rcv = new EtapasM4ViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(EtapasM4ViewHolders holder, int position) {
        banco = new Banco(context);
        EtapaDao etapaDao = new EtapaDao(banco);
        int statusEtapa01 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E1M4", "M4");
        if(statusEtapa01 == 1){
            mThumbIds[1] = R.drawable.emblema2_a;
        }
        int statusEtapa02 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E2M4", "M4");
        if(statusEtapa02 == 1){
            mThumbIds[2] = R.drawable.emblema3_a;
        }

        holder.currentItem = position;
        holder.imageViewEtapa.setImageResource(mThumbIds[position]);
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.emblema1_a, R.drawable.emblema2_i, R.drawable.emblema3_i
    };

    @Override
    public int getItemCount() {
        return this.mThumbIds.length;
    }

}