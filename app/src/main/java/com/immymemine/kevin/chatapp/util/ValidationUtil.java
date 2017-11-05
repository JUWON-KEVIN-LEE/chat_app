package com.immymemine.kevin.chatapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by quf93 on 2017-11-03.
 */

public class ValidationUtil {
    static Pattern p;
    static Matcher m;

    private static final String EMAIL_PATTERN = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    public static boolean isValidEmail(String email) {
        if (email==null)
            return false;

        p = Pattern.compile(EMAIL_PATTERN);
        m = p.matcher(email);
        return m.matches();
    }

    private static final String PW_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$";
    public static boolean isValidPassword(String password) {
        if(password == null)
            return false;

        p = Pattern.compile(PW_PATTERN);
        m = p.matcher(password);
        return m.matches();
    }

    private static final String PN_PATTERN = "^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$";
    public static boolean isValidPhoneNumber(String phoneNumber) {
        p = Pattern.compile(PN_PATTERN);
        m = p.matcher(phoneNumber);
        return m.matches();
    }

    private static final String NAME_PATTERN = "^[가-힣a-zA-z]*$";
    public static boolean isValidName(String name) {
        if(name == null)
            return false;

        p = Pattern.compile(NAME_PATTERN);
        m = p.matcher(name);
        return m.matches();
    }

    private static final String BIRTH_PATTERN = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])$";
    public static boolean isValidBirth(String birth) {
        p = Pattern.compile(BIRTH_PATTERN);
        m = p.matcher(birth);
        return m.matches();
    }
}
