package ru.mfilatov.prayingtimes.timeskeeper.model;

public record PrayingTimes(
    String date,
    String timezone,
    String method,
    String fajr,
    String sunrise,
    String dhuhr,
    String asr,
    String sunset,
    String maghrib,
    String isha) {}
