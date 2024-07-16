package com.forward.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class PaymentUtility {


    public static String accountNumber() {
        return "SBA-".concat(String.valueOf(generateRandom(12)));

    }

    private static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static String paymentDate() {
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date paymentDate=new Date();
        return format.format(paymentDate);
    }

    public static String trnIdGenerate() {
        return "TNX-"+ UUID.randomUUID().toString().replace("-", "");
    }
}
