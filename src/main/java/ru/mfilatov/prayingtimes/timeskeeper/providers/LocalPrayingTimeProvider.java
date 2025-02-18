/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.providers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfilatov.prayingtimes.calculator.enums.CalculationMethods;
import ru.mfilatov.prayingtimes.calculator.model.Times;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

@Service
public class LocalPrayingTimeProvider implements PrayingTimesProvider {

  private final PrayingTimesCalculatorImpl calculator;

  private static final DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm");

  @Autowired
  LocalPrayingTimeProvider(PrayingTimesCalculatorImpl calculator) {
    this.calculator = calculator;
  }

  @Override
  public PrayingTimes getTimesByCoordinates(
      Coordinates coordinates, TimeZone timeZone, String method) {
    var timezone = timeZone.utc();
    var time = LocalDate.now(ZoneId.ofOffset("UTC", ZoneOffset.ofHours(timezone)));

    Times times = calculator.calculate(coordinates, time, timeZone, method);

    return new PrayingTimes(
        time.toString(),
        formatTimezone(timezone),
        CalculationMethods.valueOf(method).getName(),
        times.imsak().format(formater),
        times.fajr().format(formater),
        times.sunrise().format(formater),
        times.dhuhr().format(formater),
        times.asr().format(formater),
        times.sunset().format(formater),
        times.maghrib().format(formater),
        times.isha().format(formater),
        times.midnight().format(formater));
  }

  private String formatTimezone(Integer timezone) {
    String result;
    if (timezone > 0) {
      result = "+" + timezone;
    } else if (timezone < 0) {
      result = timezone.toString();
    } else {
      result = "UTC";
    }
    return result;
  }
}
