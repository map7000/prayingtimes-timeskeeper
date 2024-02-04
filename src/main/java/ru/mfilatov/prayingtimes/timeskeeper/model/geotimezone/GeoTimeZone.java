/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.model.geotimezone;

public record GeoTimeZone(
    Double longitude,
    Double latitude,
    String location,
    String country_iso,
    String iana_timezone,
    String timezone_abbreviation,
    String dst_abbreviation,
    String offset,
    String dst_offset,
    String current_local_datetime,
    String current_utc_datetime) {}
