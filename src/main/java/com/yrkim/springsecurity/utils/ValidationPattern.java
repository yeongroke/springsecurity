package com.yrkim.springsecurity.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class ValidationPattern {
    public static final Pattern NON_WORLD_CHARACTER;
    public static final Pattern NON_WORLD_CHARACTER_AND_KOREAN;
    public static final Pattern EMAIL_Check;
    public static final Pattern ONLY_NUMBER;

    static {
        NON_WORLD_CHARACTER = Pattern.compile("^\\w*$");
        NON_WORLD_CHARACTER_AND_KOREAN = Pattern.compile("^[a-zA-Z_0-9ㄱ-ㅎ가-힣]*$");
        EMAIL_Check = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        ONLY_NUMBER = Pattern.compile("^[0-9]*$");
    }
}
