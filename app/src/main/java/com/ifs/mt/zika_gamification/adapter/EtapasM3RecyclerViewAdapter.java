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
public class EtapasM3RecyclerViewAdapter extends RecyclerView.Adapter<EtapasM3ViewHolders> {

    private Context context;
    private Banco banco;

    public EtapasM3RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EtapasM3ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.etapas_list_card, null);
        //Faz as chamadas dos cliques
        EtapasM3ViewHolders rcv = new EtapasM3ViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(EtapasM3ViewHolders holder, int position) {
        banco = new Banco(context);
        EtapaDao etapaDao = new EtapaDao(banco);
        int statusEtapa01 = etapaDao.getStatusEtapaByUsuario(Login.getUsuarioLogado().getUsuario_id(), "E1M3", "M3");
        if(statusEtapa01 == 1){
            mThumbIds[1] = R.drawable.emblema2_a;
        }

        holder.currentItem = position;
        holder.imageViewEtapa.setImageResource(mThumbIds[position]);
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.emblema1_a, R.drawable.emblema2_i
    };

    @Override
    public int getItemCount() {
        return this.mThumbIds.length;
    }

}