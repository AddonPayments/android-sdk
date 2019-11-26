package com.addonpayments.androidsdk.validaciones;

/*
    Validar Amex CVN. Se aplica a los tipos de tarjetas Amex. Sólo permite 4 caracteres numéricos.
*/

public class ValidateAmexCvn {

    public static boolean perform(String cvn) {
        // El valor de este campo no puede sser superior a 4 caracteres
        if (cvn == null || !cvn.matches("^\\d{4}$")) {
            return false;
        }

        return true;
    }
}
