/*
 * Copyright 2023 Mikhail Filatov
 * SPDX-License-Identifier: Apache-2.0
 */
package ru.mfilatov.prayingtimes.timeskeeper.providers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;
import ru.mfilatov.prayingtimes.timeskeeper.calculation.PrayTime;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

@Service
public class LocalPrayingTimeProvider implements PrayingTimesProvider{

    @Override
    public PrayingTimes getTimesByCoordinates(Coordinates coordinates, TimeZone timeZone, Integer method) {
        double timezone = timeZone.utc();
        PrayTime prayers = new PrayTime();

        prayers.setTimeFormat(prayers.Time24);
        prayers.setCalcMethod(7);
        prayers.setAdjustHighLats(prayers.AngleBased);
        int[] offsets = {0, 0, 0, 0, 0, 0, 0}; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        prayers.tune(offsets);


        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal,
                coordinates.latitude(), coordinates.longitude(), timezone);

        var response = new PrayingTimes(LocalDate.now().toString(), timeZone.utc().toString(), "Spiritual Administration of Muslims of Russia",
                prayerTimes.get(0), prayerTimes.get(1), prayerTimes.get(2),prayerTimes.get(3), prayerTimes.get(4), prayerTimes.get(5), prayerTimes.get(6));
        return response;
    }
}
