package com.addonpayments.androidsdk.validaciones;

import android.widget.EditText;

/*
    Validar formato de fecha de caducidad. Sólo permite 2 caracteres numéricos. El mes debe estar entre 1 y 12
*/

public class ValidateMesFormat {

    public static boolean perform(EditText Mes) {
        // Prueba de longitud numérica de 2 como máximo
        if (Mes.getText().toString() == null || !Mes.getText().toString().matches("^\\d{2}$")) {
            Mes.setError("Debe introducir dos números");
            Mes.requestFocus();
            return false;
        }

        int month = Integer.parseInt(Mes.getText().toString());

        // Prueba del mes para que el rango sea 1-12
        if (month < 1 || month > 12) {
            Mes.setError("Debe indicar el mes (valores del 1 al 12)");
            Mes.requestFocus();
            return false;
        }

        return true;
    }

}
