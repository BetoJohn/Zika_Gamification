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
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class EtapasM2RecyclerViewAdapter extends RecyclerView.Adapter<EtapasM2ViewHolders> {

    private Context context;
    private Banco banco;

    public EtapasM2RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EtapasM2ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.etapas_list_card, null);
        //Faz as chamadas dos cliques
        EtapasM2ViewHolders rcv = new EtapasM2ViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(EtapasM2ViewHolders holder, int position) {
        banco = new Banco(context);
        EtapaDao etapaDao = new EtapaDao(banco);
        int statusEtapa01 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E1M2", "M2");
        if(statusEtapa01 == 1){
            mThumbIds[1] = R.drawable.emblema2_a;
        }
        int statusEtapa02 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E2M2", "M2");
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