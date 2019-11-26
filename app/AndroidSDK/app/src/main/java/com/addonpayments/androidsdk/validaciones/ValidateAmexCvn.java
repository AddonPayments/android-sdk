package com.addonpayments.androidsdk.validaciones;

/*
    Validar Amex CVN. Se aplica a los tipos de tarjetas Amex. Sólo permite 4 caracteres numéricos.
*/

public class ValidateAmexCvn {

    public static boolean perform(String cvn) {
        // test numérico longitud 4
        if (cvn == null || !cvn.matches("^\\d{4}$")) {
            return false;
        }

        return true;
    }
}
