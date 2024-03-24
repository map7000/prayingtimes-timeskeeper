/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.providers;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import ru.mfilatov.PrayingTimesCalculator;
import ru.mfilatov.enums.CalculationMethods;
import ru.mfilatov.functions.Times;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

@Service
public class LocalPrayingTimeProvider implements PrayingTimesProvider {

  @Override
  public PrayingTimes getTimesByCoordinates(
      Coordinates coordinates, TimeZone timeZone, Integer method) {
    double timezone = timeZone.utc();
    PrayingTimesCalculator calculator =
        new PrayingTimesCalculator(
            OffsetDateTime.now(),
            timeZone.utc(),
            coordinates.latitude(),
            coordinates.longitude(),
            CalculationMethods.RUSSIA);

    Times times = calculator.calculate();

    return new PrayingTimes(
        LocalDate.now().toString(),
        timeZone.utc().toString(),
        "Spiritual Administration of Muslims of Russia",
        calculator.getFormattedTime(times.fajr()),
        calculator.getFormattedTime(times.sunrise()),
        calculator.getFormattedTime(times.dhuhr()),
        calculator.getFormattedTime(times.asr()),
        calculator.getFormattedTime(times.sunset()),
        calculator.getFormattedTime(times.maghrib()),
        calculator.getFormattedTime(times.isha()));
  }
}
