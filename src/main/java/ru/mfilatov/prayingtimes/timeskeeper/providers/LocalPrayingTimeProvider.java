/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.providers;

import java.time.LocalDate;
import org.springframework.stereotype.Service;
import ru.mfilatov.prayingtimes.calculator.PrayingTimesCalculator;
import ru.mfilatov.prayingtimes.calculator.enums.CalculationMethods;
import ru.mfilatov.prayingtimes.calculator.model.Times;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

@Service
public class LocalPrayingTimeProvider implements PrayingTimesProvider {

  @Override
  public PrayingTimes getTimesByCoordinates(
      Coordinates coordinates, TimeZone timeZone, Integer method) {
    var timezone = timeZone.utc();
    PrayingTimesCalculator calculator =
        new PrayingTimesCalculator(
            LocalDate.now(),
            timezone,
            coordinates.latitude(),
            coordinates.longitude(),
            CalculationMethods.RUSSIA);

    Times times = calculator.calculate();

    return new PrayingTimes(
        LocalDate.now().toString(),
        timezone.toString(),
        "Spiritual Administration of Muslims of Russia",
        times.fajr().toString(),
        times.sunrise().toString(),
        times.dhuhr().toString(),
        times.asr().toString(),
        times.sunset().toString(),
        times.maghrib().toString(),
        times.isha().toString());
  }
}
