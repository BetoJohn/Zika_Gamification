package com.ifs.mt.zika_gamification.fragmentos.perguntas_c;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.telas.treinamento_c.C;

/**
 * Created by Betto Silva on 08/08/2016.
 */
public class Pergunta1_C extends Fragment {
    private Toolbar tb_bottom_next;
    private WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_pergunta1_c,
                container, false);
        webview = (WebView) fragment.findViewById(R.id.webview);
        String text = "<html><body>"
                + "<p align=\"justify\">"
                +"Sabe- se que a Microcefalia é identificada nos  bebês quando a circunferência da cabeça da criança é inferior a 32cm. Qual item abaixo não é uma causa de Microcefalia?\n"
                + "</p> "
                + "</body></html>";

        webview.loadData(text, "text/html;charset=UTF-8", null);

        tb_bottom_next = (Toolbar) fragment.findViewById(R.id.tb_bottom_next);
        tb_bottom_next.findViewById(R.id.iv_avancar_pergunta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Muda pagina", Toast.LENGTH_SHORT).show();

                //----------------------------------testes bundle---------------------------------

                String cid = "TESTE";
                Fragment frag = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("CID", cid);
                frag.setArguments(bundle);


                //----------------------------------testes bundle---------------------------------

                ((C) getActivity()).trocarPagina(1);
            }
        });





        return fragment;
    }
}
