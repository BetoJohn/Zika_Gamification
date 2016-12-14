package com.ifs.mt.zika_gamification.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.util.MySharedPreferencesController;

/**
 * Created by Beto on 05/08/2016.
 */
public class ImageAdapterEtapasM1 extends BaseAdapter {
    private Context mContext;
    public ImageView imageView;
    private MySharedPreferencesController mySharedPreferencesController;

    public ImageAdapterEtapasM1(Context c) {
        mContext = c;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        mySharedPreferencesController = MySharedPreferencesController.getInstance(mContext);
        if(mySharedPreferencesController.getData(MySharedPreferencesController.M1_E2)){
                    mThumbIds[1] = R.drawable.emblema2_a;
        }

        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.emblema1_a, R.drawable.emblema2_i

    };

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
}
