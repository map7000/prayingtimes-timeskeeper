package ru.mfilatov.prayingtimes.timeskeeper.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.AladhanTimingsByCity;

@FeignClient(value = "aladhanClient", url = "https://api.aladhan.com/")
public interface AladhanRestApiClient {
  @RequestMapping(method = RequestMethod.GET, value = "/v1/timingsByCity/{day}")
  AladhanTimingsByCity getTimingsByCity(
      @PathVariable("day") String day,
      @RequestParam(value = "city") String city,
      @RequestParam(value = "country") String country,
      @RequestParam(value = "method") String method);
}
