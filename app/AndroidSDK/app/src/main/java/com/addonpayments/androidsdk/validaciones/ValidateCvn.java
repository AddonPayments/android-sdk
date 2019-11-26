package com.addonpayments.androidsdk.validaciones;

/*
    Validar CVN. Se aplica a los tipos de tarjetas que no son de Amex. Sólo permite 3 caracteres numéricos.
*/

import android.widget.EditText;

public class ValidateCvn {

    public static boolean perform(EditText CVV) {
        // Prueba de longitud numérica 3
        if (CVV.getText().toString() == null || !CVV.getText().toString().matches("^\\d{3}$")) {
            CVV.setError("Debe introducir como máximo 3 números");
            CVV.requestFocus();
            return false;
        }

        return true;
    }
}
