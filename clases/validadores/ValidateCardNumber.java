package com.addonpayments.androidsdk.validaciones;

import android.widget.EditText;

/*
    Valide el número de la tarjeta. Devuelve true si el número de tarjeta es válido. Sólo permite valores numéricos no vacíos entre 12 y 19 caracteres. También se realiza un chequeo de Luhn contra el número de tarjeta.
*/
public class ValidateCardNumber {

    public static boolean perform(EditText Numero) {

        // Prueba numérica y longitud entre 12 y 19
        if (Numero.getText().toString() == null || !Numero.getText().toString().matches("^\\d{12,19}$")) {
            Numero.setError("Debe introducir como mínimo 12 números y como máximo 19");
            Numero.requestFocus();
            return false;
        }


        // Comprobación de Luhn
        int sum = 0;
        int digit = 0;
        int addend = 0;
        boolean timesTwo = false;

        for (int i = Numero.length() - 1; i >= 0; i--) {
            digit = Integer.parseInt(Numero.getText().toString().substring(i, i + 1), 10);
            if (timesTwo) {
                addend = digit * 2;
                if (addend > 9) {
                    addend -= 9;
                }
            } else {
                addend = digit;
            }
            sum += addend;
            timesTwo = !timesTwo;
        }

        int modulus = sum % 10;
        if (modulus != 0) {
            return false;
        }

        return true;
    }
}
