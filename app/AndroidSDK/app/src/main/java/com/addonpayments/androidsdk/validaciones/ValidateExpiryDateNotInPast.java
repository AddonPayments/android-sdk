package com.addonpayments.androidsdk.validaciones;

/*
    Validar la fecha de caducidad para que sea igual o superior a la actual. También ejecuta la comprobación de la fecha desde validateExpiryDateFormat
*/

import java.util.Calendar;

public class ValidateExpiryDateNotInPast {

    public static boolean perform(String expiryDate) {
        // Validamos la fecha
        if (!ValidateExpiryDataFormat.perform(expiryDate)) {
            return false;
        }

        int month = Integer.parseInt(expiryDate.substring(0, 2), 10);
        int year = Integer.parseInt(expiryDate.substring(2, 4), 10);

        // test date is not in the past
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        if (year < (currentYear % 100)) {
            return false;
        } else if (year == (currentYear % 100) && month < currentMonth) {
            return false;
        }

        return true;
    }
}