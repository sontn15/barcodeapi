package com.sh.barcodeapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtils {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String fromDateToStringFormat(Date date) {
        if (Objects.nonNull(date))
            return simpleDateFormat.format(date);
        return null;
    }

    public static String getCurrentDateStr() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
    }

    public static Date parseDateFromString(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
