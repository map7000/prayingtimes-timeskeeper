/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */

package ru.mfilatov.prayingtimes.timeskeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TimeskeeperApplication {

  public static void main(String[] args) {
    SpringApplication.run(TimeskeeperApplication.class, args);
  }
}
