package com.ifs.mt.zika_gamification.validacao;

import android.text.TextUtils;

public class ValidarLogin {
	
	public final static boolean validateSenha(String senha){
		
		if (TextUtils.isEmpty(senha)) {
            return false;
        } else {
        	return true;
        }
	}
	
	public final static boolean validateEmail(String txtEmail) {
        if (TextUtils.isEmpty(txtEmail)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();
        }
    }
}
