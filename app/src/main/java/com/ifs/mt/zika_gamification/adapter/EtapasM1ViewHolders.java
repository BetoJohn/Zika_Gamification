package com.ifs.mt.zika_gamification.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.M1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_2.M1E2;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

/**
 * Created by Betto Silva on 14/12/2016.
 */
public class EtapasM1ViewHolders extends RecyclerView.ViewHolder {
    public View view;
    public TextView countryName;
    public ImageView imageViewEtapa;
    public int currentItem;
    private MySharedPreferencesController mySharedPreferencesController;
    private boolean A = false, B = false;

    public EtapasM1ViewHolders(View v) {
        super(v);
        view = v;
        // countryName = (TextView) itemView.findViewById(R.id.country_name);
        imageViewEtapa = (ImageView) view.findViewById(R.id.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mySharedPreferencesController = MySharedPreferencesController.getInstance(v.getContext());
                // mySharedPreferencesController.clearData();

                A = true;
                B = mySharedPreferencesController.getData(MySharedPreferencesController.M1_E2);

                switch(currentItem){
                    case 0:
                        if (A)
                            v.getContext().startActivity(new Intent(v.getContext(), M1E1.class));
                        break;
                    case 1:
                        if (B)
                            v.getContext().startActivity(new Intent(v.getContext(), M1E2.class));
                        break;


                }

            }
        });
    }


}



