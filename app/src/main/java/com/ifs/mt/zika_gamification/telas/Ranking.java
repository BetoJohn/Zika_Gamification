package com.ifs.mt.zika_gamification.telas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.model.UsuarioM;

public class Ranking extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
/*
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listView = (ListView)findViewById(R.id.listView);

        FirebaseListAdapter<UsuarioM> adapter = new FirebaseListAdapter<UsuarioM>(this, UsuarioM.class, android.R.layout.simple_list_item_1, mDatabase.child("usuarios").orderByChild("usuario_nome")) {
            @Override
            protected void populateView(View v, UsuarioM model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(String.valueOf(model.getUsuario_nome()));

            }
        };
        listView.setAdapter(adapter);*/
    }
}
