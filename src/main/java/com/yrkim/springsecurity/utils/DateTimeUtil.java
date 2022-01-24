package com.yrkim.springsecurity.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
  public static final DateTimeFormatter DATE_FORMAT_yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter DATE_FORMAT_yyyyMMdd_HHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter DATE_FORMAT_yyyyMMdd_HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static Date calcExpiration(long toTime) {
    Date expiration = new Date();
    long TimeMillis = expiration.getTime();
    TimeMillis += toTime;
    expiration.setTime(TimeMillis);
    return expiration;
  }

  public static LocalDateTime parseDateFormat(@NotNull String date , DateTimeFormatter dateTimeFormatter) {
    return LocalDateTime.parse(date, dateTimeFormatter);
  }

  public static String now() {
    return LocalDateTime.now().format(DATE_FORMAT_yyyyMMdd_HHmmss);
  }

  public static String nowDay() {
    return LocalDateTime.now().format(DATE_FORMAT_yyyyMMdd);
  }
}
