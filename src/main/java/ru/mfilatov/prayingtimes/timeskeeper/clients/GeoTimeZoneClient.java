/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mfilatov.prayingtimes.timeskeeper.model.geotimezone.GeoTimeZone;

@FeignClient(value = "geoTimeZoneClient", url = "https://api.geotimezone.com/public/")
public interface GeoTimeZoneClient {
  @GetMapping(path = "timezone")
  GeoTimeZone getTimeZone(
      @RequestParam(value = "latitude") Double latitude,
      @RequestParam(value = "longitude") Double longitude);
}
