package com.ifs.mt.zika_gamification.adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.StatusM;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<StatusM> expandableListTitle;
    private HashMap<StatusM, List<StatusM>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<StatusM> expandableListTitle,
                                       HashMap<StatusM, List<StatusM>> expandableListDetail) {
        this.context = context;
        Collections.sort(expandableListTitle);
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final StatusM expandedListText = (StatusM) getChild(listPosition, expandedListPosition);
       // if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_group, null);
       // }

        TextView expandedListTextView1 = (TextView) convertView
                .findViewById(R.id.expandedListItem1);

        expandedListTextView1.setText("Experiência: "+ String.valueOf(expandedListText.getExperiencia()));

        TextView expandedListTextView2 = (TextView) convertView
                .findViewById(R.id.expandedListItem2);

        expandedListTextView2.setText("Pontos: "+ String.valueOf(expandedListText.getPontuacao()));

        TextView expandedListTextView3 = (TextView) convertView
                .findViewById(R.id.expandedListItem3);

        expandedListTextView3.setText("Nível: "+ String.valueOf(expandedListText.getNivel()));

        ImageView iv_m1 = (ImageView)convertView.findViewById(R.id.iv_m1);
        if(expandedListText.isModulo_01_status()){
            iv_m1.setImageResource(R.drawable.modulo_01_c);
        }
        ImageView iv_m2 = (ImageView)convertView.findViewById(R.id.iv_m2);
        if(expandedListText.isModulo_02_status()){
            iv_m2.setImageResource(R.drawable.modulo_02_c);
        }
        ImageView iv_m3 = (ImageView)convertView.findViewById(R.id.iv_m3);
        if(expandedListText.isModulo_03_status()){
            iv_m3.setImageResource(R.drawable.modulo_03_c);
        }
        ImageView iv_m4 = (ImageView)convertView.findViewById(R.id.iv_m4);
        if(expandedListText.isModulo_04_status()){
            iv_m4.setImageResource(R.drawable.modulo_04_c);
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        StatusM listTitle = (StatusM) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView listTitleTextView1 = (TextView) convertView
                .findViewById(R.id.listTitleGroup1);

        listTitleTextView1.setTypeface(null, Typeface.BOLD);
        listTitleTextView1.setText(String.valueOf(listTitle.getExperiencia()));


        TextView listTitleTextView2 = (TextView) convertView
                .findViewById(R.id.listTitleGroup2);

        listTitleTextView2.setTypeface(null, Typeface.BOLD);
        listTitleTextView2.setText(listTitle.getUsuario_nome());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}