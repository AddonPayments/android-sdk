package com.addonpayments.androidsdk.validaciones;


/*
    Valide el nombre del titular de la tarjeta. Devuelve verdadero si el titular de la tarjeta es válido. Sólo permite valores ISO/IEC 8859-1 no vacíos de 100 caracteres o menos.
*/

import android.widget.EditText;

public class ValidateCardHolderName {

    public static boolean perform(EditText Nombre) {
        // En caso de que el nombre del titular esté vacío, mostrar el mensaje de error "Debe introducir el nombre del titular de la tarjeta"
        if (Nombre == null) {
            Nombre.setError("Debe introducir el nombre del titular de la tarjeta");
            Nombre.requestFocus();
            return false;
        }

        // Mostrar error cuando se intente enviar un espacio en blanco
        if (Nombre.getText().toString().trim().length() == 0) {
            Nombre.setError("Error espacio en blanco");
            Nombre.requestFocus();
            return false;
        }

        // Máximo 100 caracteres
        if (!Nombre.getText().toString().matches("^[\\u0020-\\u007E\\u00A0-\\u00FF]{1,100}$")) {
            Nombre.setError("Hasta 100 caracteres");
            Nombre.requestFocus();
            return false;
        }

        return true;
    }
}
