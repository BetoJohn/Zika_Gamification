package com.ifs.mt.zika_gamification.validacao;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AutenticarLogin {

	public static boolean validarEmail(View pView, String pMessage) {
		if (pView instanceof EditText) {
			EditText edText = (EditText) pView;
			Editable text = edText.getText();
			if (text != null) {
				String strText = text.toString();
				if (!TextUtils.isEmpty(strText)) {
					if(android.util.Patterns.EMAIL_ADDRESS.matcher(strText).matches()){
						return true;
					}
				}
			}
			edText.setError(pMessage);
			edText.setFocusable(true);
			edText.requestFocus();
			return false;
		}
		return false;

		/*if (TextUtils.isEmpty(nome)) {
			return false;
		} else {
			//Valida email
			return android.util.Patterns.EMAIL_ADDRESS.matcher(nome).matches();

		}*/
	}

	public static boolean validateNotNull(View pView, String pMessage) {
		if (pView instanceof EditText) {
			EditText edText = (EditText) pView;
			Editable text = edText.getText();
			if (text != null) {
				String strText = text.toString();
				if (!TextUtils.isEmpty(strText)) {
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
	public static boolean validarSenha(View pView, String pMessage) {
		if (pView instanceof EditText) {
			EditText edText = (EditText) pView;
			Editable text = edText.getText();
			if (text != null) {
				String strText = text.toString();
				if (strText.length() > 6) {
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
