package com.addonpayments.androidsdk.validaciones;

import android.widget.EditText;

import java.util.Calendar;

/*
    Validar formato de fecha de caducidad. Sólo permite 4 caracteres numéricos.
*/

public class ValidateYearFormat {
    public static boolean perform(EditText Any) {
        // test numeric of length 4
        if (Any.getText().toString() == null || !Any.getText().toString().matches("^\\d{4}$")) {
            Any.setError("Debe introducir el año completo");
            Any.requestFocus();
            return false;
        }

        int Year = Integer.parseInt(Any.getText().toString());

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        if (Year < (currentYear)) {
            Any.setError("Debe introducir una fecha de expiración correcta");
            Any.requestFocus();
            return false;
        }

        return true;
    }
}
