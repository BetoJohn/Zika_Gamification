package com.ifs.mt.zika_gamification.telas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.UsuarioDao;
import com.ifs.mt.zika_gamification.model.Tipo_StatusM;
import com.ifs.mt.zika_gamification.model.Tipo_UsuarioM;
import com.ifs.mt.zika_gamification.model.UsuarioM;
import com.ifs.mt.zika_gamification.rest.UsuarioRest;
import com.ifs.mt.zika_gamification.validacao.AutenticarCadastro;
import com.ifs.mt.zika_gamification.validacao.AutenticarLogin;

import java.util.ArrayList;
import java.util.List;


public class Login extends Activity {

    private static UsuarioM usuarioLogado;
    private TextView clickCadastro, textViewTipoUsuario, tv_login, tv_cadastro;
    private EditText editLogin;
    private EditText editSenha;
    private EditText editNomeCadastro;
    private EditText editLoginCadastro;
    private EditText editEmailCadastro;
    private EditText editSenhaCadastro;
    private EditText editConfirmarSenhaCadastro;
    private Spinner spinnerTipoUsuario;
    private ImageView imageViewCadastro;
    private Button btnCadastro;
    private Button btnLogin;
    private Banco bancoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retira o titulo e o tolbar deixando fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        //Aparece o teclado ao iniciar a tela
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



        tv_login = (TextView) findViewById(R.id.tv_login);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/agency_fb.ttf");
        tv_login.setTypeface(font);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setTypeface(font);

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnCadastro.setTypeface(font);

        tv_cadastro = (TextView) findViewById(R.id.tv_cadastro);
        tv_cadastro.setTypeface(font);
        tv_cadastro.setVisibility(View.INVISIBLE);

        editLogin = (EditText) findViewById(R.id.editLogin);
        editLogin.requestFocus();
        editSenha = (EditText) findViewById(R.id.editSenha);
        editSenha.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO)
                    btnLogin.setFocusableInTouchMode(true);
                entrar(v);
                return false;
            }
        });

        editNomeCadastro = (EditText) findViewById(R.id.editNomeCadastro);
        editNomeCadastro.setVisibility(View.INVISIBLE);
        editLoginCadastro = (EditText) findViewById(R.id.editLoginCadastro);
        editLoginCadastro.setVisibility(View.INVISIBLE);
        editEmailCadastro = (EditText) findViewById(R.id.editEmailCadastro);
        editEmailCadastro.setVisibility(View.INVISIBLE);
        editSenhaCadastro = (EditText) findViewById(R.id.editSenhaCadastro);
        editSenhaCadastro.setVisibility(View.INVISIBLE);
        editConfirmarSenhaCadastro = (EditText) findViewById(R.id.editConfirmarSenhaCadastro);
        editConfirmarSenhaCadastro.setVisibility(View.INVISIBLE);


        spinnerTipoUsuario = (Spinner) findViewById(R.id.spinnerTipoUsuario);
        preencherSpinnerTipoUsuario(spinnerTipoUsuario);
        spinnerTipoUsuario.setBackgroundResource(R.drawable.spinner_edit);
        spinnerTipoUsuario.setVisibility(View.INVISIBLE);

       /* imageViewCadastro = (ImageView) findViewById(R.id.imageViewCadastro);
        imageViewCadastro.setVisibility(View.INVISIBLE);*/

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnCadastro.setVisibility(View.INVISIBLE);

        textViewTipoUsuario = (TextView) findViewById(R.id.textViewTipoUsuario);
        textViewTipoUsuario.setVisibility(View.INVISIBLE);

        clickCadastro = (TextView) findViewById(R.id.clickCadastro);
        clickCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible();
            }
        });
        clickCadastro.setTypeface(font);


        String tipoUsuario = String.valueOf(spinnerTipoUsuario.getSelectedItem());
        System.out.println("Tipo usuário: " + tipoUsuario);
    }

    public void entrar(View v) {
        //Esconde o teclado ao clicar no botão entrar
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        editLogin.setText("mateus");
        editSenha.setText("123");

        boolean login = AutenticarLogin.validateNotNull(editLogin,
                "Insira um login válido!");
       boolean senha =  AutenticarLogin.validateNotNull(editSenha,
                "Insira uma senha!");
        UsuarioM usuario = new UsuarioM();
        usuario.setUsuario_login(editLogin.getText().toString());
        usuario.setUsuario_senha(editSenha.getText().toString());
        Log.i("Login", "Dados: " + usuario.getUsuario_login() + " - " + usuario.getUsuario_senha());

        setUsuarioLogado(usuario);

        //-------------- Teste de Inserção do Usuario -----------
        /*bancoUsuario = new Banco(this);
        UsuarioDao dao = new UsuarioDao(bancoUsuario);
        //this.deleteDatabase("dbzika");
        //dao.apagaRegistrosTabela();
        List<UsuarioM> usuarios = dao.getAll();
        System.out.println("Quantidade de usuários: "+usuarios.size());
        for(UsuarioM usu: usuarios){
            System.out.println("Usuário Id: "+ usu.getUsuario_id()+" - "+usu.getUsuario_nome()+" - "+usu.getUsuario_email()+" - "+usu.getUsuario_login()+" - "+usu.getUsuario_tipo());
        }*/
        //-------------- Teste de Inserção do Usuario -----------



      /*AutenticacaoThread thread = new AutenticacaoThread();
        thread.execute(usuario);*/

        if(login && senha){
            bancoUsuario = new Banco(getApplicationContext());
            UsuarioDao dao = new UsuarioDao(bancoUsuario);
            usuario = dao.autenticacao(usuario);
            try {
                if (null != usuario) {

                    System.out.println("Usuario result: " + usuario.getUsuario_login() + " - " + usuario.getUsuario_senha());

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    //agenteLogado vai receber os dados do agente que se logou
                    usuarioLogado = usuario;
                    intent.putExtra("dados", bundle);
                    // se deu tudo certo chama a classe MenuApp
                    intent.setClass(Login.this, MenuPrincipal.class);
                    startActivity(intent);


                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                    alertDialog.setTitle("Erro!");
                    alertDialog.setMessage("Não foi encontrado nenhum usuário com esse Login e Senha. Deseja efetuar um cadastro?");
                    alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            editLoginCadastro.setText(editLogin.getText().toString());
                            setVisible();

                        }
                    });
                    alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    // alertDialog.setIcon(R.drawable.dengue_10dp);
                    alertDialog.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void cadastrar(View v) {

        String tipoUsuario = String.valueOf(spinnerTipoUsuario.getSelectedItem());
        System.out.println("Tipo usuário: " + tipoUsuario);

        AutenticarLogin.validateNotNull(editNomeCadastro,
                "Insira um nome!");
        AutenticarLogin.validateNotNull(editLoginCadastro,
                "Insira um login!");
        AutenticarLogin.validarEmail(editEmailCadastro, "Insira um email válido!");

        AutenticarLogin.validateNotNull(editSenhaCadastro,
                "Insira uma senha!");
        AutenticarCadastro.validarConfirmacaoSenha(editSenhaCadastro, editConfirmarSenhaCadastro,
                "As senha não são idênticas!");


        UsuarioM usuario = new UsuarioM();
        usuario.setUsuario_nome(editNomeCadastro.getText().toString());
        usuario.setUsuario_login(editLoginCadastro.getText().toString());
        usuario.setUsuario_email(editEmailCadastro.getText().toString());
        usuario.setUsuario_senha(editSenhaCadastro.getText().toString());
        usuario.setUsuario_tipo(tipoUsuario);
        Log.i("Login", "Dados: " + usuario.getUsuario_login() + " - " + usuario.getUsuario_senha());

        //PARA TESTES
        setUsuarioLogado(usuario);

        //-------------- Teste de Inserção do Usuario -----------
        bancoUsuario = new Banco(this);
        UsuarioDao dao = new UsuarioDao(bancoUsuario);
        dao.insert(usuario);
        //-------------- Teste de Inserção do Usuario -----------
        setUsuarioLogado(usuario);
        startActivity(new Intent(Login.this, MenuPrincipal.class));
    }

    public void setVisible() {
        editNomeCadastro.setVisibility(View.VISIBLE);
        editLoginCadastro.setVisibility(View.VISIBLE);
        editEmailCadastro.setVisibility(View.VISIBLE);
        editSenhaCadastro.setVisibility(View.VISIBLE);
        editConfirmarSenhaCadastro.setVisibility(View.VISIBLE);
        spinnerTipoUsuario.setVisibility(View.VISIBLE);
//        imageViewCadastro.setVisibility(View.VISIBLE);
        btnCadastro.setVisibility(View.VISIBLE);
        textViewTipoUsuario.setVisibility(View.VISIBLE);
        tv_cadastro.setVisibility(View.VISIBLE);
    }

    class AutenticacaoThread extends AsyncTask<UsuarioM, Void, UsuarioM> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            // pgBar.setVisibility(View.VISIBLE);
            // btnLogin.setProgress(1);
            Log.i("Thread", "entrou no onPreExecute()");
        }

        // DEPOIS DE OBTER O RESULTADO DO INDOBACKGROUND QUE ESSE M�TODO �
        // CHAMADO
        @Override
        protected void onPostExecute(UsuarioM result) {
            try {
               /* Log.i("Thread", "entrou no onPostExecute()");
                Log.i("Thread", "valor do result " + result);*/

                // pgBar.setVisibility(View.INVISIBLE);
                if (null != result) {

                    System.out.println("Usuario result: " + result.getUsuario_login() + " - " + result.getUsuario_senha());

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    //agenteLogado vai receber os dados do agente que se logou
                    usuarioLogado = result;
                    intent.putExtra("dados", bundle);
                    // se deu tudo certo chama a classe MenuApp
                    intent.setClass(Login.this, MenuPrincipal.class);
                    startActivity(intent);


                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                    alertDialog.setTitle("Erro!");
                    alertDialog.setMessage("Não foi encontrado nenhum usuário com esse Login e Senha. Deseja efetuar um cadastro?");
                    alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            editLoginCadastro.setText(editLogin.getText().toString());
                            setVisible();

                        }
                    });
                    alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    // alertDialog.setIcon(R.drawable.dengue_10dp);
                    alertDialog.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected UsuarioM doInBackground(UsuarioM... params) {
            Log.i("Thread", "entrou no doInBackground()");
            // faz a chamada do webService em background

            UsuarioRest agtRest = new UsuarioRest();
            UsuarioM usu = params[0];
            System.out.println("Valor do Usuario no doInBackground " + usu);
            try {
                //vai verificar se a tabela local esta vazia, se estiver faz a consulta via webService
                //popula a tabela local com o resultado da consulta e assim autentica o LOgin retornando
                //o agente.

                bancoUsuario = new Banco(getApplicationContext());
                UsuarioDao dao = new UsuarioDao(bancoUsuario);
                usu = dao.autenticacao(usu);
                System.out.println("Valor retornado para AgentT no doInBackground " + usu);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return usu;
        }

    }

    public static UsuarioM getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(UsuarioM usuarioLogado) {
        Login.usuarioLogado = usuarioLogado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    private void preencherSpinnerTipoUsuario(Spinner spinerTpUsuario) {
        List<String> tpLarvicida = new ArrayList<String>();
        tpLarvicida.add("Agente");
        tpLarvicida.add("Supervisor");
        tpLarvicida.add("Coordenador");

        ArrayAdapter<String> adapterTpLarvicida = new ArrayAdapter<String>(
                this, R.layout.spinner_itens, tpLarvicida);
        adapterTpLarvicida
                .setDropDownViewResource(R.layout.spinner_dropdown_itens);
        spinerTpUsuario.setAdapter(adapterTpLarvicida);
    }
}
