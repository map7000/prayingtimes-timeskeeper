package ru.mfilatov.prayingtimes.timeskeeper.providers;

import ru.mfilatov.prayingtimes.timeskeeper.calculation.PrayTime;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LocalPrayingTimeProvider implements PrayingTimesProvider{

    @Override
    public PrayingTimes getTimesByCoordinates(double latitude, double longitude, String method) {
        double timezone = 10;
        // Test Prayer times here
        PrayTime prayers = new PrayTime();

        prayers.setTimeFormat(prayers.Time24);
        prayers.setCalcMethod(prayers.Jafari);
        prayers.setAsrJuristic(prayers.Shafii);
        prayers.setAdjustHighLats(prayers.AngleBased);
        int[] offsets = {0, 0, 0, 0, 0, 0, 0};
        prayers.tune(offsets);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal,
                latitude, longitude, timezone);
        ArrayList<String> prayerNames = prayers.getTimeNames();

        for (int i = 0; i < prayerTimes.size(); i++) {
            System.out.println(prayerNames.get(i) + " - " + prayerTimes.get(i));
        }
        return null;
    }
}
