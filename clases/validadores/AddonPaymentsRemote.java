package com.addonpayments.androidsdk.validaciones;


import android.widget.EditText;

public class AddonPaymentsRemote {

    /**
     * Validar CVN de Amex. Solo permite 4 caracteres numéricos.
     *
     * @param cvn el CVN de Amex a validar
     * @return true si el CVN de Amex es válido
     */
    public static boolean validateAmexCvn(String cvn) {
        return ValidateAmexCvn.perform(cvn);
    }

    /**
     * Validar el nombre del titular de la tarjeta. Devuelve verdadero si el titular de la tarjeta es válido. Sólo permite valores ISO/IEC 8859-1 no vacíos de 100 caracteres o menos.
     *
     * @param Nombre el nombre del titular de la tarjeta a validar
     * @return true si el nombre del titular de la tarjeta es válido
     */
    public static boolean ValidateCardHolderName(EditText Nombre) {
        return ValidateCardHolderName.perform(Nombre);
    }

    /**
     * Valida el número de la tarjeta. Devuelve true si el número de tarjeta es válido. Sólo permite valores numéricos no vacíos entre 12 y 19 caracteres. También se realiza un chequeo de Luhn contra el número de tarjeta.
     *
     * @param Numero el número de tarjeta de crédito que debe verificarse
     * @return true si el número de la tarjeta es válido
     */
    public static boolean ValidateCardNumber(EditText Numero) {
        return ValidateCardNumber.perform(Numero);
    }

    /**
     * Validar CVN. Se aplica a los tipos de tarjetas que no son de Amex. Sólo permite 3 caracteres numéricos.
     *
     * @param CVV el CVN para validar
     * @return true si el CVN es válido
     */
    public static boolean validateCvn(EditText CVV) {
        return ValidateCvn.perform(CVV);
    }

    /**
     *  Validar formato de mes. Sólo permite 2 caracteres numéricos. El mes debe estar entre 1 y 12.
     *
     * @param Mes la fecha de caducidad de la tarjeta para validar
     * @return true si la fecha de caducidad es válida
     */
    public static boolean ValidateMesFormat(EditText Mes) {
        return ValidateMesFormat.perform(Mes);
    }

    /**
     *  Validar formato de año. Sólo permite 4 caracteres numéricos.
     *
     * @param Any the card expiry date to validate
     * @return true if the expiry date is valid
     */

    public static boolean ValidateYearFormat(EditText Any) {
        return ValidateYearFormat.perform(Any);
    }

    /**
     *  Validar formato de fecha de caducidad. Sólo permite 4 caracteres numéricos.
     *
     * @param Exp the card expiry date to validate
     * @return true if the expiry date is valid
     */
    public static boolean ValidateExpiryDataFormat(String Exp) {
        return ValidateExpiryDataFormat.perform(Exp);
    }

    /**
     * Validar que la fecha de expiración no sea anterior a la actual.
     *
     * @param date la fecha de caducidad de la tarjeta para validar
     * @return true si la fecha de caducidad no es anterior
     */
    public static boolean validateExpiryDateNotInPast(String date) {
        return ValidateExpiryDateNotInPast.perform(date);
    }
}
