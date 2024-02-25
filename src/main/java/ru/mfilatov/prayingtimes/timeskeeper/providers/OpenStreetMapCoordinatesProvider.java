/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mfilatov.prayingtimes.timeskeeper.clients.OpenStreetMapSearchClient;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;

@Service
public class OpenStreetMapCoordinatesProvider implements CoordinatesProvider {
  private final OpenStreetMapSearchClient client;

  @Autowired
  public OpenStreetMapCoordinatesProvider(OpenStreetMapSearchClient client) {
    this.client = client;
  }

  @Override
  public Coordinates getCoordinatesByCityName(String city, String country) {
    var response = client.getCityLocation(city, country, "jsonv2", "1").getFirst();
    return new Coordinates(response.lat(), response.lon());
  }
}
