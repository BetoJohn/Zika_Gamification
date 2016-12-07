package com.ifs.mt.zika_gamification.validacao;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AutenticarResposta {

    public static boolean validarRadioGroup(View pView, String pMessage, Context context) {
        if (pView instanceof RadioGroup) {
            RadioGroup rdText = (RadioGroup) pView;
            int check = rdText.getCheckedRadioButtonId();
            if (check != -1) { //Nada foi checado
                return true;
            }
        }
        Toast.makeText(context, pMessage, Toast.LENGTH_SHORT).show();
        return false;
    }


}
