/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */

package ru.mfilatov.prayingtimes.timeskeeper.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.mfilatov.prayingtimes.timeskeeper.clients.AladhanRestApiClient;
import ru.mfilatov.prayingtimes.timeskeeper.mappers.AladhanMapper;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;

@RestController
public class TimesRestController {
  private final AladhanRestApiClient client;
  private final AladhanMapper mapper = Mappers.getMapper(AladhanMapper.class);

  @Autowired
  public TimesRestController(AladhanRestApiClient client) {
    this.client = client;
  }

  @GetMapping("/getTimesByLocation/{country}/{city}/{method}")
  PrayingTimes getTimesByLocation(
      @PathVariable String country, @PathVariable String city, @PathVariable String method) {
    var aladhanTimes =
        client.getTimingsByCity(
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            city,
            country,
            method);
    return mapper.timingsByCityPrayingTimes(aladhanTimes);
  }
}
