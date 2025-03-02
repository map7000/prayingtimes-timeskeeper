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
import ru.mfilatov.prayingtimes.timeskeeper.quibla.QiblaDirectionCalculator;

@RestController
public class TimesRestController {
  private final CoordinatesProvider coordinatesProvider;
  private final TimeZoneProvider timeZoneProvider;
  private final PrayingTimesProvider prayingTimesProvider;
  private final QiblaDirectionCalculator qiblaDirectionCalculator;

  @Autowired
  public TimesRestController(
      CoordinatesProvider coordinatesProvider,
      TimeZoneProvider timeZoneProvider,
      PrayingTimesProvider prayingTimesProvider,
      QiblaDirectionCalculator qiblaDirectionCalculator) {
    this.coordinatesProvider = coordinatesProvider;
    this.timeZoneProvider = timeZoneProvider;
    this.prayingTimesProvider = prayingTimesProvider;
    this.qiblaDirectionCalculator = qiblaDirectionCalculator;
  }

  @GetMapping("/getTimesByLocation/{country}/{city}/{method}")
  PrayingTimes getTimesByLocation(
      @PathVariable String country, @PathVariable String city, @PathVariable String method) {
    var coordinates = coordinatesProvider.getCoordinatesByCityName(city, country);
    var timezone = timeZoneProvider.getTimeZone(coordinates);

    return prayingTimesProvider.getTimesByCoordinates(coordinates, timezone, method);
  }

  @GetMapping("/getTimesByCoordinates")
  PrayingTimes getTimesByCoordinates(
      @RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String method) {
    var coordinates = new Coordinates(latitude, longitude);
    var timezone = timeZoneProvider.getTimeZone(coordinates);

    return prayingTimesProvider.getTimesByCoordinates(coordinates, timezone, method);
  }

  @GetMapping("/getQiblaDirection")
  Double getQiblaDirection(@RequestParam Double latitude, @RequestParam Double longitude) {
    return qiblaDirectionCalculator.calculate(latitude, longitude);
  }
}
