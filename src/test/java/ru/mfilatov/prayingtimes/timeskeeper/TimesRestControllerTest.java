/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */

package ru.mfilatov.prayingtimes.timeskeeper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.mfilatov.prayingtimes.timeskeeper.clients.AladhanRestApiClient;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.AladhanTimingsByCity;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.Data;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.Meta;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.Method;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.Timings;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimesRestControllerTest {
  @Autowired private TestRestTemplate restTemplate;
  @MockBean private AladhanRestApiClient client;

  @Test
  void testGetTimesByLocation() {
    // Set up test data
    String aladhanTime = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    String timesKeeperTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    Timings timings = new Timings("3", "4", "5", "6", "7", "8", "9");
    Method method = new Method("2");
    Meta meta = new Meta("1", method);
    Data data = new Data(timings, null, meta);
    AladhanTimingsByCity aladhanTimings = new AladhanTimingsByCity(data);

    PrayingTimes expectedPrayingTimes =
        new PrayingTimes(timesKeeperTime, "1", "2", "3", "4", "5", "6", "7", "8", "9");

    when(client.getTimingsByCity(aladhanTime, "Moscow", "Russia", "14")).thenReturn(aladhanTimings);

    // Call the endpoint using the TestRestTemplate
    ResponseEntity<PrayingTimes> response =
        restTemplate.getForEntity("/getTimesByLocation/Russia/Moscow/14", PrayingTimes.class);

    // Verify the results
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(expectedPrayingTimes);
    verify(client).getTimingsByCity(aladhanTime, "Moscow", "Russia", "14");
  }
}
