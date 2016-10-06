package com.ifs.mt.zika_gamification.validacao;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AutenticarLogin {
	
	public final static boolean validarSenha(String senha) {

		if (TextUtils.isEmpty(senha)) {
			return false;
		} else {
			return true;
		}
	}

	public final static boolean validarNome(String nome) {
		if (TextUtils.isEmpty(nome)) {
			return false;
		} else {
			//Valida email
			return android.util.Patterns.EMAIL_ADDRESS.matcher(nome).matches();

		}
	}

	public static boolean validateNotNull(View pView, String pMessage) {
		if (pView instanceof EditText) {
			EditText edText = (EditText) pView;
			Editable text = edText.getText();
			if (text != null) {
				String strText = text.toString();
				if (!TextUtils.isEmpty(strText)) {
					//Log.i("validateNotNull", "REtornou true");
					return true;
				}
			}
			edText.setError(pMessage);
			edText.setFocusable(true);
			edText.requestFocus();
			return false;
		}
		return false;
	}
}
