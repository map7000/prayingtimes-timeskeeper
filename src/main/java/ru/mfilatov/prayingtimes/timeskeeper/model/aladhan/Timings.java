package ru.mfilatov.prayingtimes.timeskeeper.model.aladhan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public record Timings(
    String fajr,
    String sunrise,
    String dhuhr,
    String asr,
    String sunset,
    String maghrib,
    String isha) {}
