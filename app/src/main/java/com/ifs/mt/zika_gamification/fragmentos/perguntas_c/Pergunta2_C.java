package com.ifs.mt.zika_gamification.fragmentos.perguntas_c;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.treinamento_c.C;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class Pergunta2_C extends Fragment {
private Toolbar tb_bottom_next;
    String myString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta2_c,
                container, false);

        tb_bottom_next = (Toolbar)fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_avancar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();
                ((C) getActivity()).trocarPagina(2);
            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();
                ((C) getActivity()).trocarPagina(0);
            }
        });




        return  fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //---------------------teste bundle-----------------------
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myString = bundle.getString("CID");
            System.out.println(myString);
        }
        System.out.println(myString);

        //---------------------teste bundle-----------------------
    }
}
