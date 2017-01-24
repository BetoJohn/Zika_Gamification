package com.ifs.mt.zika_gamification.telas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.StatusDao;
import com.ifs.mt.zika_gamification.model.StatusM;
import com.ifs.mt.zika_gamification.util.ColorTool;

public class MenuPrincipal extends AppCompatActivity  implements View.OnTouchListener {

    private Toolbar tb;
    private ImageView imageViewTreinamento, imageViewStatus, imageViewRanking;
    private TextView tv_usuario_logado, textViewTreinamento, textViewStatus, textViewRanking;
    private Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);

        tv_usuario_logado = (TextView) findViewById(R.id.tv_usuario_logado);
        //Posso esta recperando o usuário da sessão com o Firebase
        tv_usuario_logado.setText("Bem Vindo, " + Login.getUsuarioLogado().getUsuario_nome());
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_usuario_logado.setTypeface(font);

        /*textViewTreinamento = (TextView) findViewById(R.id.textViewTreinamento);
        textViewTreinamento.setTypeface(font);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        textViewStatus.setTypeface(font);
        textViewRanking = (TextView) findViewById(R.id.textViewRanking);
        textViewRanking.setTypeface(font);


        imageViewTreinamento = (ImageView) findViewById(R.id.imageViewTreinamento);
        imageViewTreinamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this, Treinamento.class));
            }
        });

        imageViewStatus = (ImageView) findViewById(R.id.imageViewStatus);
        imageViewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuPrincipal.this, "imageViewStatus", Toast.LENGTH_SHORT).show();
            }
        });

        imageViewRanking = (ImageView) findViewById(R.id.imageViewRanking);
        imageViewRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuPrincipal.this, "imageViewRanking", Toast.LENGTH_SHORT).show();
            }
        });*/



        ImageView iv = (ImageView) findViewById(R.id.iv_menu_principal_back);
        if (iv != null) {
            iv.setOnTouchListener(this);
        }

    }

    /**
     * Respond to the user touching the screen.
     * Change images to make things appear and disappear from the screen.
     */

    public boolean onTouch(View v, MotionEvent ev) {
        boolean handledHere = false;

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;            // resource id of the next image to display

        // If we cannot find the imageView, return.
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_menu_principal_back);
        if (imageView == null) return false;

        // When the action is Down, see if we should show the "pressed" image for the default image.
        // We do this when the default image is showing. That condition is detectable by looking at the
        // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
        Integer tagNum = (Integer) imageView.getTag();
        int currentResource = (tagNum == null) ? R.drawable.menu1 : tagNum.intValue();

        // Now that we know the current resource being displayed we can handle the DOWN and UP events.

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (currentResource == R.drawable.menu1) {
                    handledHere = true;
                } else handledHere = true;
                break;

            case MotionEvent.ACTION_UP:
                // On the UP, we do the click action.
                // The hidden image (image_areas) has three different hotspots on it.
                // The colors are red, blue, and yellow.
                // Use image_areas to determine which region the user touched.
                int touchColor = getHotspotColor(R.id.iv_menu_principal_invisivel, evX, evY);

                // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
                // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
                // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
                // varying pixel density.
                ColorTool ct = new ColorTool();
                int tolerance = 25;
                nextImage = R.drawable.menu1;

                if (ct.closeMatch(Color.RED, touchColor, tolerance))
                    startActivity(new Intent(MenuPrincipal.this, Treinamento.class));
                else if (ct.closeMatch(Color.BLUE, touchColor, tolerance))
                    toast("Ranking");
                else if (ct.closeMatch(Color.YELLOW, touchColor, tolerance))
                    toast("Status");
               /* else if (ct.closeMatch(Color.WHITE, touchColor, tolerance))
                    toast("Branco");*/

                // If the next image is the same as the last image, go back to the default.
                // toast ("Current image: " + currentResource + " next: " + nextImage);
                if (currentResource == nextImage) {
                    nextImage = R.drawable.menu1;
                }
                handledHere = true;
                break;

            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage > 0) {
                imageView.setImageResource(nextImage);
                imageView.setTag(nextImage);
            }
        }
        return handledHere;
    }

    /**
     * Get the color from the hotspot image at point x-y.
     */

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById(hotspotId);
        if (img == null) {
            Log.d("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    } // end toast
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sair:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuPrincipal.this, Login.class));
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}
