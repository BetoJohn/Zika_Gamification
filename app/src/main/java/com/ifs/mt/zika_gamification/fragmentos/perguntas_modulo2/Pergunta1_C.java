package com.ifs.mt.zika_gamification.fragmentos.perguntas_modulo2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.TextAlignment;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.treinamento_modulo3.C;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class Pergunta1_C extends Fragment {
    private Toolbar tb_bottom_next;
    private WebView webview;
    private DocumentView dvText;


    EditText A_input;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta1_c,
                container, false);

        //---------------------teste bundle-----------------------
        A_input = (EditText) fragment.findViewById(R.id.a_input);

        //---------------------teste bundle-----------------------


       /* webview = (WebView) fragment.findViewById(R.id.webview);
        String text = "<html><body>"
                + "<p align=\"justify\">"
                + "Sabe- se que a Microcefalia é identificada nos  bebês quando a circunferência da cabeça da criança é inferior a 32cm. Qual item abaixo não é uma causa de Microcefalia?\n"
                + "</p> "
                + "</body></html>";

        webview.loadData(text, "text/html;charset=UTF-8", null);*/

        dvText = (DocumentView) fragment.findViewById(R.id.dvText);
        dvText.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        dvText.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.getInstance(DefaultHyphenator.HyphenPattern.PT));
        dvText.getDocumentLayoutParams().setHyphenated(false);

        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_avancar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();

                String textPassToB = A_input.getText().toString();

                String TagOfFragmentB = ((C) getActivity()).getTagFragmentB();

                Pergunta2_C fragmentB = (Pergunta2_C) getActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag(TagOfFragmentB);

                fragmentB.b_updateText(textPassToB);

                Toast.makeText(getActivity(),
                        "text sent to Fragment B:\n " + TagOfFragmentB,
                        Toast.LENGTH_LONG).show();

                ((C) getActivity()).trocarPagina(1);
            }
        });


        return fragment;
    }


}
