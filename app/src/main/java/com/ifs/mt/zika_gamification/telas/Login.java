package com.ifs.mt.zika_gamification.telas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifs.mt.zika_gamification.R;
import com.ifs.mt.zika_gamification.dao.Banco;
import com.ifs.mt.zika_gamification.dao.StatusDao;
import com.ifs.mt.zika_gamification.dao.UsuarioDao;
import com.ifs.mt.zika_gamification.model.StatusM;
import com.ifs.mt.zika_gamification.model.UsuarioM;
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
    private ScrollView scrollLogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private UsuarioM usuarioLoginFirebase;
    private UsuarioM usuarioCadastroFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retira o titulo e o tolbar deixando fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        //Aparece o teclado ao iniciar a tela
        // this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


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

        scrollLogin = (ScrollView) findViewById(R.id.scrollLogin);
        editNomeCadastro = (EditText) findViewById(R.id.editNomeCadastro);
        editNomeCadastro.setVisibility(View.INVISIBLE);
        editLoginCadastro = (EditText) findViewById(R.id.editLoginCadastro);
        editLoginCadastro.setVisibility(View.INVISIBLE);
       /* editEmailCadastro = (EditText) findViewById(R.id.editEmailCadastro);
        editEmailCadastro.setVisibility(View.INVISIBLE);*/
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
                scrollLogin.post(new Runnable() {
                    public void run() {
                        scrollLogin.smoothScrollTo(0, tv_cadastro.getTop());
                    }
                });
                setVisible();
                editNomeCadastro.requestFocus();
            }
        });
        clickCadastro.setTypeface(font);
        String tipoUsuario = String.valueOf(spinnerTipoUsuario.getSelectedItem());
        System.out.println("Tipo usuário: " + tipoUsuario);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    public void entrar(View v) {
        //Esconde o teclado ao clicar no botão entrar
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        boolean login = AutenticarLogin.validarEmail(editLogin,
                "Insira um email válido!");
        boolean senha = AutenticarLogin.validarSenha(editSenha,
                "Insira uma senha válida!");

        if (login && senha) {
            try {
                LoginThread loginThread = new LoginThread();
                UsuarioM usuario = new UsuarioM();
                usuario.setUsuario_login(editLogin.getText().toString().trim());
                usuario.setUsuario_senha(editSenha.getText().toString().trim());

                //loginThread.execute(usuario);


                bancoUsuario = new Banco(getApplicationContext());
                UsuarioDao dao = new UsuarioDao(bancoUsuario);
                usuario = dao.autenticacao(usuario);
                if (null != usuario) {

                    System.out.println("Usuario result: " + usuario.getUsuario_login() + " - " + usuario.getUsuario_senha());

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    setUsuarioLogado(usuario);
                    intent.putExtra("dados", bundle);
                    // se deu tudo certo chama a classe MenuApp
                    intent.setClass(Login.this, MenuPrincipal.class);
                    startActivity(intent);


                } else {

                    progressDialog.setMessage("Entrando...");
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(editLogin.getText().toString().trim(), editSenha.getText().toString().trim())
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String userId = task.getResult().getUser().getUid();
                                        mDatabase.child("usuarios").child(userId).addListenerForSingleValueEvent(
                                                new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        final UsuarioM usuarioM;
                                                        usuarioM = dataSnapshot.getValue(UsuarioM.class);
                                                        setUsuarioLogado(usuarioM);
                                                        System.out.println("User: " + usuarioM.getUsuario_login() + " - " + usuarioM.getUsuario_nome());
                                                        progressDialog.dismiss();
                                                        startActivity(new Intent(Login.this, MenuPrincipal.class));
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                        Log.w("CAncelled", "getUser:onCancelled", databaseError.toException());
                                                        // ...
                                                    }
                                                });

                                    } else {
                                        progressDialog.dismiss();
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                                        alertDialog.setTitle("Erro!");
                                        alertDialog.setMessage("Não foi encontrado nenhum usuário com esse Login e Senha. Deseja efetuar um cadastro?");
                                        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                scrollLogin.post(new Runnable() {
                                                    public void run() {
                                                        scrollLogin.smoothScrollTo(0, btnLogin.getBottom());
                                                    }
                                                });

                                                editLoginCadastro.setText(editLogin.getText().toString());
                                                setVisible();
                                                editNomeCadastro.requestFocus();

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
                                }
                            });


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void cadastrar(View v) {

        String tipoUsuario = String.valueOf(spinnerTipoUsuario.getSelectedItem());

        AutenticarLogin.validateNotNull(editNomeCadastro,
                "Insira um nome!");
        boolean login = AutenticarLogin.validarEmail(editLoginCadastro,
                "Insira um email válido!");

        boolean senha = AutenticarLogin.validarSenha(editSenhaCadastro,
                "Insira uma senha válida com mais de 6 digítos!");
        AutenticarCadastro.validarConfirmacaoSenha(editSenhaCadastro, editConfirmarSenhaCadastro,
                "As senha não são idênticas!");


        if (login && senha) {
            CadastroThread cadastroThread = new CadastroThread();
            final UsuarioM usuario = new UsuarioM();
            usuario.setUsuario_nome(editNomeCadastro.getText().toString());
            usuario.setUsuario_login(editLoginCadastro.getText().toString());
            usuario.setUsuario_senha(editSenhaCadastro.getText().toString());
            usuario.setUsuario_tipo(tipoUsuario);

            //cadastroThread.execute(usuario);

            progressDialog.setMessage("Registrando Usuário...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            //creating a new user
            mAuth.createUserWithEmailAndPassword(usuario.getUsuario_login(), usuario.getUsuario_senha())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                //display some message here

                                //-------------- Teste de Inserção do Usuario -----------
                                bancoUsuario = new Banco(Login.this);
                                UsuarioDao dao = new UsuarioDao(bancoUsuario);
                                usuario.setUsuario_id(dao.insert(usuario));

                                String userId = task.getResult().getUser().getUid();
                                mDatabase.child("usuarios").child(userId).setValue(usuario);
                                StatusM statusM = new StatusM();

                                StatusDao statusDao = new StatusDao(bancoUsuario);
                                statusM.setUsuario_id(usuario.getUsuario_id());
                                statusM.setUsuario_nome(usuario.getUsuario_nome());
                                statusM.setPontuacao(0);
                                statusM.setNivel(0);
                                statusM.setExperiencia(0);
                                statusM.setStatus_id(statusDao.insert(statusM));
                                mDatabase.child("usuarios-status").child(userId).setValue(statusM);

                                setUsuarioLogado(usuario);
                                Toast.makeText(Login.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(Login.this, MenuPrincipal.class));
                            } else {
                                //display some message here
                                Toast.makeText(Login.this, "Erro ao cadastrar usuário, email já cadastrado, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }


    }


    class LoginThread extends AsyncTask<UsuarioM, Void, UsuarioM> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            // pgBar.setVisibility(View.VISIBLE);
            // btnLogin.setProgress(1);
            progressDialog.setMessage("Entrando...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        // DEPOIS DE OBTER O RESULTADO DO INDOBACKGROUND QUE ESSE M�TODO �
        // CHAMADO
        @Override
        protected void onPostExecute(UsuarioM result) {
            try {
                Log.i("onPostExecute", "usuario resposta do doInbackground: " + result);
                if (result != null) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    //agenteLogado vai receber os dados do agente que se logou
                    usuarioLogado = result;
                    intent.putExtra("dados", bundle);
                    // se deu tudo certo chama a classe MenuApp
                    intent.setClass(Login.this, MenuPrincipal.class);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                    alertDialog.setTitle("Erro!");
                    alertDialog.setMessage("Não foi encontrado nenhum usuário com esse Login e Senha. Deseja efetuar um cadastro?");
                    alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            scrollLogin.post(new Runnable() {
                                public void run() {
                                    scrollLogin.smoothScrollTo(0, btnLogin.getBottom());
                                }
                            });

                            editLoginCadastro.setText(editLogin.getText().toString());
                            setVisible();
                            editNomeCadastro.requestFocus();


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
            try {
                //vai verificar se a tabela local esta vazia, se estiver faz a consulta via webService
                //popula a tabela local com o resultado da consulta e assim autentica o LOgin retornando

                UsuarioM usuarioM;
                bancoUsuario = new Banco(getApplicationContext());
                final UsuarioDao dao = new UsuarioDao(bancoUsuario);
                usuarioM = params[0];
                UsuarioM usuarioBanco = dao.autenticacao(usuarioM);

                Log.i("doInBackground", "consulta banco " + usuarioBanco);
                if (null != usuarioBanco) {
                    return usuarioBanco;
                } else {

                    mAuth.signInWithEmailAndPassword(usuarioM.getUsuario_login(), usuarioM.getUsuario_senha())
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    System.out.println("Entrou no mAuth");
                                    if (task.isSuccessful()) {
                                        String userId = task.getResult().getUser().getUid();
                                        mDatabase.child("usuarios").child(userId).addListenerForSingleValueEvent(
                                                new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        usuarioLoginFirebase = dataSnapshot.getValue(UsuarioM.class);
                                                        progressDialog.dismiss();
                                                        Log.i("doInBackground", "onDataChange()" + usuarioCadastroFirebase.getUsuario_nome());
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {
                                                        Log.w("CAncelled", "getUser:onCancelled", databaseError.toException());
                                                        // ...
                                                    }
                                                });
                                    }
                                }

                            });
                    return usuarioLoginFirebase;


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    class CadastroThread extends AsyncTask<UsuarioM, Void, UsuarioM> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            // pgBar.setVisibility(View.VISIBLE);
            // btnLogin.setProgress(1);
            progressDialog.setMessage("Registrando Usuário...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        // DEPOIS DE OBTER O RESULTADO DO INDOBACKGROUND QUE ESSE M�TODO �
        // CHAMADO
        @Override
        protected void onPostExecute(UsuarioM result) {
            try {
                if (result != null) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    //agenteLogado vai receber os dados do agente que se logou
                    usuarioLogado = result;
                    intent.putExtra("dados", bundle);
                    // se deu tudo certo chama a classe MenuApp
                    intent.setClass(Login.this, MenuPrincipal.class);
                    startActivity(intent);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected UsuarioM doInBackground(UsuarioM... params) {

            try {

                bancoUsuario = new Banco(getApplicationContext());
                usuarioCadastroFirebase = params[0];

                mAuth.createUserWithEmailAndPassword(usuarioCadastroFirebase.getUsuario_login(), usuarioCadastroFirebase.getUsuario_senha())
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if (task.isSuccessful()) {
                                    //display some message here
                                    bancoUsuario = new Banco(Login.this);
                                    UsuarioDao dao = new UsuarioDao(bancoUsuario);
                                    usuarioCadastroFirebase.setUsuario_id(dao.insert(usuarioCadastroFirebase));

                                    String userId = task.getResult().getUser().getUid();
                                    mDatabase.child("usuarios").child(userId).setValue(usuarioCadastroFirebase);
                                    setUsuarioLogado(usuarioCadastroFirebase);
                                    Toast.makeText(Login.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                } else {
                                    //display some message here
                                    Toast.makeText(Login.this, "Erro ao cadastrar usuário, email já cadastrado, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
                return usuarioCadastroFirebase;


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }


    public void setVisible() {
        editNomeCadastro.setVisibility(View.VISIBLE);
        editLoginCadastro.setVisibility(View.VISIBLE);
//        editEmailCadastro.setVisibility(View.VISIBLE);
        editSenhaCadastro.setVisibility(View.VISIBLE);
        editConfirmarSenhaCadastro.setVisibility(View.VISIBLE);
        editConfirmarSenhaCadastro.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO)
                    btnCadastro.setFocusableInTouchMode(true);
                cadastrar(v);
                return false;
            }
        });
        spinnerTipoUsuario.setVisibility(View.VISIBLE);
        btnCadastro.setVisibility(View.VISIBLE);
        textViewTipoUsuario.setVisibility(View.VISIBLE);
        tv_cadastro.setVisibility(View.VISIBLE);


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

    @Override
    public void onBackPressed() {
    }

    /*@Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/
}
