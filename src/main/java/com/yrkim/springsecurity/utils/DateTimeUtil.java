package com.yrkim.springsecurity.utils;

import java.util.Date;

public class DateTimeUtil {

  public static Date calcExpiration(long toTime) {
    Date expiration = new Date();
    long TimeMillis = expiration.getTime();
    TimeMillis += toTime;
    expiration.setTime(TimeMillis);
    return expiration;
  }
}
