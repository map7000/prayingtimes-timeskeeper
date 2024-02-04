/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mfilatov.prayingtimes.timeskeeper.model.openstreetmap.SearchJsonV2;

import java.util.List;

@FeignClient(value = "openStreetMapSearchClient", url = "https://nominatim.openstreetmap.org/")
public interface OpenStreetMapSearchClient {
  @GetMapping(path = "search")
  List<SearchJsonV2> getCityLocation(
      @RequestParam(value = "city") String city,
      @RequestParam(value = "country") String country,
      @RequestParam(value = "format") String format,
      @RequestParam(value = "addressdetails") String addressdetails);
}
