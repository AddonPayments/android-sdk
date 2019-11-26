package com.addonpayments.androidsdk.ui.hpp.clases;

import java.util.Random;

public class orderID {

    // Indicamos una cadena a mostrar de forma aleatoria
    public static final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static Random RANDOM = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }

        return "SDK_Android-200-"+sb.toString();
    }
}
