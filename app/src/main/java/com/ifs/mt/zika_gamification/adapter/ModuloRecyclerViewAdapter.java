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
public class ModuloRecyclerViewAdapter extends RecyclerView.Adapter<ModuloViewHolders> {

    private Context context;
    private MySharedPreferencesController mySharedPreferencesController;

    public ModuloRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ModuloViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.modulos_list_card, null);
        ModuloViewHolders rcv = new ModuloViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ModuloViewHolders holder, int position) {
        mySharedPreferencesController = MySharedPreferencesController.getInstance(context);
        if (mySharedPreferencesController.getData(MySharedPreferencesController.M2)) {
            mThumbIds[1] = R.drawable.modulo_02_a;
        }

        holder.currentItem = position;
        holder.countryPhoto.setImageResource(mThumbIds[position]);
      //holder.countryName.setText(itemList.get(position).getName());

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