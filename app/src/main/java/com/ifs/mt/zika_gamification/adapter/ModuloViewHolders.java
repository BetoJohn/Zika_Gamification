package com.ifs.mt.zika_gamification.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.M1;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class ModuloViewHolders extends RecyclerView.ViewHolder {
    public View view;
    public TextView countryName;
    public ImageView countryPhoto;
    public int currentItem;
    private boolean m1 = true, m2 = false, m3 = false, m4 = false;

    public ModuloViewHolders(View v) {
        super(v);
        view = v;
        // countryName = (TextView) itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView) view.findViewById(R.id.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentItem == 0) {
                    if (m1){
                        v.getContext().startActivity(new Intent(v.getContext(), M1.class));
                    }
                }

            }
        });
    }


}



