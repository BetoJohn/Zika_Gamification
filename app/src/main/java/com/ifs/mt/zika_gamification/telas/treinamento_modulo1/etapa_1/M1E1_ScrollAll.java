package com.ifs.mt.zika_gamification.telas.treinamento_modulo1.etapa_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.TextAlignment;
import com.ifs.mt.zika_gamification.R;

public class M1E1_ScrollAll extends AppCompatActivity {
    private DocumentView dvP1M1E1, dvP2M1E1, dvP3M1E1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_e1_scroll_all);

        dvP1M1E1 = (DocumentView) findViewById(R.id.dvP1M1E1);
        dvP1M1E1.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        dvP1M1E1.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.getInstance(DefaultHyphenator.HyphenPattern.PT));
        dvP1M1E1.getDocumentLayoutParams().setHyphenated(false);

        dvP2M1E1 = (DocumentView) findViewById(R.id.dvP2M1E1);
        dvP2M1E1.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        dvP2M1E1.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.getInstance(DefaultHyphenator.HyphenPattern.PT));
        dvP2M1E1.getDocumentLayoutParams().setHyphenated(false);

        WebView view = (WebView)findViewById(R.id.webView);
        view.setVerticalScrollBarEnabled(false);

        view.loadData(getString(R.string.hello),"text/html","utf-8");


        /*dvP3M1E1 = (DocumentView) findViewById(R.id.dvP3M1E1);
        dvP3M1E1.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        dvP3M1E1.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.getInstance(DefaultHyphenator.HyphenPattern.PT));
        dvP3M1E1.getDocumentLayoutParams().setHyphenated(false);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_m1_e1__scroll_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
