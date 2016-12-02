package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.C;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class Pergunta2_C extends Fragment {
    private Toolbar tb_bottom_next;
    TextView b_received;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta2_c,
                container, false);


        //---------------------teste bundle-----------------------

        b_received = (TextView)fragment.findViewById(R.id.b_received);
        String myTag = getTag();

        //quando o fragment é carregado ele recupera sua tag e seta no metodo  public void setTabFragmentB(String t) da AppCompatActivity
        ((C)getActivity()).setTagFragmentB(myTag);

        Toast.makeText(getActivity(),
                "MyFragmentB.onCreateView(): " + myTag,
                Toast.LENGTH_LONG).show();



        //---------------------teste bundle-----------------------


        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
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


        return fragment;

    }

    public void b_updateText(String t){
        b_received.setText(t);
    }
}
