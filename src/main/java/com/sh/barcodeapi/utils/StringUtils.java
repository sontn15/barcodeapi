package com.sh.barcodeapi.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    public static String convertUTF8ToString(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String autoGenerateUserCodeCustomer(String customerName, Long max) {
        StringBuilder result = new StringBuilder();
        customerName = customerName.trim().toLowerCase().replaceAll("đ", "d");
        customerName = convertUTF8ToString(customerName);
        assert customerName != null;
        int index = customerName.indexOf("  ");
        while (index > 0) {
            customerName = customerName.replace("  ", " ");
            index = customerName.indexOf("  ");
        }
        String[] arrStr = customerName.split(" ");
        if (arrStr.length > 0) {
            for (String s : arrStr) {
                result.append(s).append(".");
            }
            result.append(max);
        } else {
            result = new StringBuilder(customerName).append(".").append(max);
        }
        return result.toString();
    }


}
