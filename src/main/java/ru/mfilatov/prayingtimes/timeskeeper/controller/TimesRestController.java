/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */

package ru.mfilatov.prayingtimes.timeskeeper.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.mfilatov.prayingtimes.timeskeeper.calculation.PrayTime;
import ru.mfilatov.prayingtimes.timeskeeper.clients.AladhanRestApiClient;
import ru.mfilatov.prayingtimes.timeskeeper.mappers.AladhanMapper;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.providers.CoordinatesProvider;
import ru.mfilatov.prayingtimes.timeskeeper.providers.TimeZoneProvider;

@RestController
public class TimesRestController {
  private final CoordinatesProvider coordinatesProvider;
  private final TimeZoneProvider timeZoneProvider;

  @Autowired
  public TimesRestController(
      CoordinatesProvider coordinatesProvider, TimeZoneProvider timeZoneProvider) {
    this.coordinatesProvider = coordinatesProvider;
    this.timeZoneProvider = timeZoneProvider;
  }

  @GetMapping("/getTimesByLocation/{country}/{city}/{method}")
  PrayingTimes getTimesByLocation(
      @PathVariable String country, @PathVariable String city, @PathVariable String method) {
    var coordinates = coordinatesProvider.getCoordinatesByCityName(city, country);
    var timezone = timeZoneProvider.getTimeZone(coordinates);

    var times = new PrayTime();
    var prayingTimes =
        times.getPrayerTimes(
            Calendar.getInstance(),
            coordinates.latitude(),
            coordinates.longitude(),
            timezone.utc());
    return null;
  }
}
