package com.addonpayments.androidsdk.validaciones;

/*
    Validar formato de fecha de caducidad. Sólo permite 4 caracteres numéricos. El mes debe estar entre 1 y 12
*/

public class ValidateExpiryDataFormat {

    public static boolean perform(String expiryDate) {
        // El número no puede ser mayor a 4 caracteres
        if (expiryDate == null || !expiryDate.matches("^\\d{4}$")) {
            return false;
        }

        int month = Integer.parseInt(expiryDate.substring(0, 2), 10);
        int year = Integer.parseInt(expiryDate.substring(2, 4), 10);

        // El mes no puede superar el rango de 1-12
        if (month < 1 || month > 12) {
            return false;
        }

        return true;
    }
}
