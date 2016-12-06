package com.ifs.mt.zika_gamification.telas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.ifs.mt.zika_gamification.validacao.AutenticarLogin;

import java.util.ArrayList;
import java.util.List;


public class Login extends Activity {

    private static UsuarioM usuarioLogado;
    private Button entrar;
    private EditText editLogin;
    private EditText editSenha;
    private EditText editNomeCadastro;
    private EditText editLoginCadastro;
    private EditText editEmailCadastro;
    private EditText editSenhaCadastro;
    private EditText editConfirmarSenhaCadastro;
    private Spinner spinnerTipoUsuario;


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
        editLoginCadastro = (EditText) findViewById(R.id.editLoginCadastro);
        editEmailCadastro = (EditText) findViewById(R.id.editEmailCadastro);
        editSenhaCadastro = (EditText) findViewById(R.id.editSenhaCadastro);
        editConfirmarSenhaCadastro = (EditText) findViewById(R.id.editConfirmarSenhaCadastro);


        spinnerTipoUsuario = (Spinner) findViewById(R.id.spinnerTipoUsuario);
        preencherSpinnerTipoUsuario(spinnerTipoUsuario);
        spinnerTipoUsuario.setBackgroundResource(R.drawable.spinner_edit);
    }
    public void entrar(View v){
        //Esconde o teclado ao clicar no botão entrar
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


        /*Parte de autenticação que foi comentada*/
        //TUDO OK, S PARA EVITAR FADIGA
        AutenticacaoThread thread = new AutenticacaoThread();

        //------ Objeto para Teste --------------
       /* Tipo_StatusM tipo_status = new Tipo_StatusM();
        tipo_status.setTipo_Status_Id(1);
        tipo_status.setTipo_Status_Nome("TesteStatus");
        tipo_status.setTipo_Status_Valor("Ok");

        Tipo_UsuarioM tipo_usuario = new Tipo_UsuarioM();
        tipo_usuario.setTipo_Usuario_Id(1);
        tipo_usuario.setTipo_Usuario_Nome("Comum");
        tipo_usuario.setTipo_Usuario_Valor("N");

        UsuarioM usuario = new UsuarioM();
        usuario.setUsuario_id(1);
        usuario.setUsuario_email("mateus@mateus.com");
        usuario.setTipo_status(tipo_status);
        usuario.setTipo_usuario(tipo_usuario);*/

        //------ Objeto para Teste --------------



        AutenticarLogin.validateNotNull(editLogin,
                "Insira um email válido!");
        AutenticarLogin.validateNotNull(editSenha,
                "Insira uma senha!");
        UsuarioM usuario = new UsuarioM();
        usuario.setUsuario_login(editLogin.getText().toString());
        usuario.setUsuario_senha(editSenha.getText().toString());
        Log.i("Login", "Dados: " + usuario.getUsuario_login() + " - " + usuario.getUsuario_senha());

        //PARA TESTES
        usuario.setUsuario_nome("Mateus Oliveira");
        setUsuarioLogado(usuario);

        //-------------- Teste de Inserção do Usuario -----------
       /* bancoUsuario = new Banco(this);
        UsuarioDao dao = new UsuarioDao(bancoUsuario);
        dao.insert(usuario);*/
        //-------------- Teste de Inserção do Usuario -----------


        startActivity(new Intent(Login.this, MenuPrincipal.class));
       /* if (usuario.getUsuario_login().equals("demo") && usuario.getUsuario_senha().equals("demo")) {
            usuario.setUsuario_login("Matheus Oliveira");
            usuarioLogado = usuario;
            startActivity(new Intent(Login.this, MenuPrincipal.class));
        }else{
            Toast.makeText(Login.this,"Login ou Senha inválido(s)!",Toast.LENGTH_SHORT).show();
        }*/

        /*// se passar na valida��o chama a thread
        if (AutenticarLogin.validarNome(editLogin.getText().toString())
                && AutenticarLogin.validarSenha(editSenha.getText()
                .toString())) {

            if (usuario.getLogin().equals("admin@admin.com") && usuario.getSenha().equals("admin")) {
               // startActivity(new Intent(Login.this, LoginAdmin.class));
            } else {
                thread.execute(usuario);
            }

        } else {
           // btnLogin.setProgress(-1);
        }*/
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
                boolean autenticado = false;
                // pgBar.setVisibility(View.INVISIBLE);
                if (null != result) {
                /*    if (result.getIdAgente() != 0) {

                        System.out.println("Agente result: " + result.getLogin() + " - " + result.getSenha());


                      //  btnLogin.setProgress(100);
                        // SystemClock.sleep(3000);

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        //agenteLogado vai receber os dados do agente que se logou
                        usuarioLogado = result;
  //                      bundle.putSerializable("agenteLogado", result);
                        intent.putExtra("dados", bundle);
                        // se deu tudo certo chama a classe MenuApp
                        intent.setClass(Login.this, MenuPrincipal.class);
                        autenticado = true;
                        startActivity(intent);
                    }*/

                }
                if (!autenticado) {

   //                 String ipRetorno = recuperaIpServidor(Login.this);
   //                 System.out.println("Ip retorno preference: " + ipRetorno);
   //                 if (ipRetorno.equals("") || ipRetorno.equals("0") || ipRetorno.equals(null)) {
   //                     Toast.makeText(Login.this, "Verifique com o Administrador se foi cadastrado o IP para o Servidor", Toast.LENGTH_SHORT).show();
   //                 }

                  //  btnLogin.setProgress(-1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected UsuarioM doInBackground(UsuarioM... params) {
            Log.i("Thread", "entrou no doInBackground()");
            // faz a chamada do webService em background

            UsuarioRest agtRest = new UsuarioRest();
            UsuarioM agente = params[0];
            System.out.println("Valor do AgentT no doInBackground " + agente);
            try {
                //vai verificar se a tabela local esta vazia, se estiver faz a consulta via webService
                //popula a tabela local com o resultado da consulta e assim autentica o LOgin retornando
                //o agente.
                agente = agtRest.autenticar(agente, getApplicationContext());
                System.out.println("Valor retornado para AgentT no doInBackground " + agente);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return agente;
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
