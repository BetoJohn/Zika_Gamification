package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo1.etapa_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.TextAlignment;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2.Pergunta2_C;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1.M1E1;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.C;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class P2M1E1 extends Fragment {
    private Toolbar tb_bottom_next;
    static  TextView b_received;
    private DocumentView dvText;



    EditText A_input;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta2_modulo1_etapa1,
                container, false);

        //---------------------teste bundle-----------------------
        b_received = (TextView)fragment.findViewById(R.id.b_received);
        String myTag = getTag();

        //quando o fragment é carregado ele recupera sua tag e seta no metodo  public void setTabFragmentB(String t) da AppCompatActivity
        ((M1E1)getActivity()).setTagFragmentP2(myTag);

       /* Toast.makeText(getActivity(),
                "MyFragmentB.onCreateView(): " + myTag,
                Toast.LENGTH_LONG).show();*/

        //---------------------teste bundle-----------------------

        dvText = (DocumentView) fragment.findViewById(R.id.dvText);
        dvText.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        dvText.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.getInstance(DefaultHyphenator.HyphenPattern.PT));
        dvText.getDocumentLayoutParams().setHyphenated(false);

        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_avancar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();
                ((M1E1) getActivity()).trocarPagina(2);
            }
        });
        tb_bottom_next.findViewById(R.id.iv_voltar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();
                ((M1E1) getActivity()).trocarPagina(0);
            }
        });


        return fragment;
    }

    public static void b_updateText(String t){
        b_received.setText(t);
    }

}
