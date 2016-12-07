package com.ifs.mt.zika_gamification.validacao;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AutenticarCadastro {
	
	public final static boolean validarSenha(String senha) {

		if (TextUtils.isEmpty(senha)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean validarConfirmacaoSenha( View senha1, View senha2, String pMessage) {
		if (senha1 instanceof EditText && senha2 instanceof EditText) {
			EditText edTextSenha1 = (EditText) senha1;
			Editable textSenha1 = edTextSenha1.getText();

			EditText edTextSenha2 = (EditText) senha2;
			Editable textSenha2 = edTextSenha2.getText();

			if(textSenha1 != null && textSenha2 != null ){
				String senha = textSenha1.toString();
				String confirmSenha = textSenha2.toString();
				if (!TextUtils.isEmpty(senha) && !TextUtils.isEmpty(confirmSenha)) {
					if(senha.equals(confirmSenha)){
						return true;
					}

				}
			}
			/*edTextSenha1.setError(pMessage);
			edTextSenha1.setFocusable(true);
			edTextSenha1.requestFocus();*/
			edTextSenha2.setError(pMessage);
			edTextSenha2.setFocusable(true);
			edTextSenha2.requestFocus();
			return false;
		}
		return false;
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
