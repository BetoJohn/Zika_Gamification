package com.ifs.mt.zika_gamification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.ModuloDao;
import com.ifs.mt.zika_gamification.telas.Login;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class ModulosRecyclerViewAdapter extends RecyclerView.Adapter<ModulosViewHolders> {

    private Context context;
    private Banco banco;
    private MySharedPreferencesController mySharedPreferencesController;

    public ModulosRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ModulosViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.modulos_list_card, null);
        ModulosViewHolders rcv = new ModulosViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ModulosViewHolders holder, int position) {
        mySharedPreferencesController = MySharedPreferencesController.getInstance(context);
        banco = new Banco(context);
        ModuloDao moduloDao = new ModuloDao(banco);
        int statusModulo01 = moduloDao.getStatusModuloByUsuario(Login.getUsuarioLogado().getUsuario_id(),  "M1");
        //Se o modulo
        if(statusModulo01 == 1){
            mThumbIds[1] = R.drawable.modulo_02_a;
            mThumbIds[0] = R.drawable.modulo_01_c;
        }

        holder.currentItem = position;
        holder.countryPhoto.setImageResource(mThumbIds[position]);

    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.modulo_01_a, R.drawable.modulo_02_i,
            R.drawable.modulo_03_i, R.drawable.modulo_04_i


    };

    @Override
    public int getItemCount() {
        return this.mThumbIds.length;
    }

}