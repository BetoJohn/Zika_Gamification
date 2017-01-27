package com.ifs.mt.zika_gamification.telas;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.adapter.CustomExpandableListAdapter;
import com.ifs.mt.zika_gamification.model.StatusM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ranking extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<StatusM> expandableListTitle;
    private Toolbar tb_ranking;
    private TextView tv_tb_ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        tb_ranking = (Toolbar) findViewById(R.id.tb_ranking);
        tb_ranking.findViewById(R.id.iv_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ranking.this, MenuPrincipal.class));
            }
        });
        tv_tb_ranking = (TextView) findViewById(R.id.tv_tb_ranking);
        tv_tb_ranking.setTypeface(font);

        //Recupera a conexão do FireBAse
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("connected");
                } else {
                    System.out.println("not connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });
        //ExpandableList FUNCIONANDO
        mDatabase.child("usuarios-status").addValueEventListener(new ValueEventListener() {
            HashMap<StatusM, List<StatusM>> expandableListDetail = new HashMap<>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    StatusM status = messageSnapshot.getValue(StatusM.class);
                    List<StatusM> statusItem = new ArrayList<>();
                    statusItem.add(status);
                    expandableListDetail.put(status, statusItem);
                }

                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                expandableListAdapter = new CustomExpandableListAdapter(Ranking.this, expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
