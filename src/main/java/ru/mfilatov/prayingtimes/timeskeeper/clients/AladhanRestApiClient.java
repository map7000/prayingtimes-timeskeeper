/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */

package ru.mfilatov.prayingtimes.timeskeeper.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.AladhanTimingsByCity;

@FeignClient(value = "aladhanClient", url = "https://api.aladhan.com/")
public interface AladhanRestApiClient {
  @GetMapping(path = "v1/timingsByCity/{day})")
  AladhanTimingsByCity getTimingsByCity(
      @PathVariable("day") String day,
      @RequestParam(value = "city") String city,
      @RequestParam(value = "country") String country,
      @RequestParam(value = "method") String method);
}
