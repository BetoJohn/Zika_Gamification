package com.ifs.mt.zika_gamification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class Etapa1M1RecyclerViewAdapter extends RecyclerView.Adapter<EtapasM1ViewHolders> {

    private Context context;
    private MySharedPreferencesController mySharedPreferencesController;

    public Etapa1M1RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EtapasM1ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.modulos_list_card, null);
        EtapasM1ViewHolders rcv = new EtapasM1ViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(EtapasM1ViewHolders holder, int position) {
        mySharedPreferencesController = MySharedPreferencesController.getInstance(context);
        if(mySharedPreferencesController.getData(MySharedPreferencesController.M1_E2)){
            mThumbIds[1] = R.drawable.emblema2_a;
        }

        holder.currentItem = position;
        holder.imageViewEtapa.setImageResource(mThumbIds[position]);
      //holder.countryName.setText(itemList.get(position).getName());

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