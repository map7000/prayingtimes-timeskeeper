/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.providers.CoordinatesProvider;
import ru.mfilatov.prayingtimes.timeskeeper.providers.PrayingTimesProvider;
import ru.mfilatov.prayingtimes.timeskeeper.providers.TimeZoneProvider;

@RestController
public class TimesRestController {
  private final CoordinatesProvider coordinatesProvider;
  private final TimeZoneProvider timeZoneProvider;
  private final PrayingTimesProvider prayingTimesProvider;

  @Autowired
  public TimesRestController(
      CoordinatesProvider coordinatesProvider, TimeZoneProvider timeZoneProvider, PrayingTimesProvider prayingTimesProvider) {
    this.coordinatesProvider = coordinatesProvider;
    this.timeZoneProvider = timeZoneProvider;
    this.prayingTimesProvider = prayingTimesProvider;
  }

  @GetMapping("/getTimesByLocation/{country}/{city}/{method}")
  PrayingTimes getTimesByLocation(
      @PathVariable String country, @PathVariable String city, @PathVariable Integer method) {
    var coordinates = coordinatesProvider.getCoordinatesByCityName(city, country);
    var timezone = timeZoneProvider.getTimeZone(coordinates);

    return prayingTimesProvider.getTimesByCoordinates(coordinates, timezone, method);
  }

  @GetMapping("/getTimesByCoordinates")
  PrayingTimes getTimesByCoordinates(
          @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Integer method) {
    var coordinates = new Coordinates(latitude, longitude);
    var timezone = timeZoneProvider.getTimeZone(coordinates);

    return prayingTimesProvider.getTimesByCoordinates(coordinates, timezone, method);
  }
}
