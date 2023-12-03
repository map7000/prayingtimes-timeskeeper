package ru.mfilatov.prayingtimes.timeskeeper.providers;

import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;

public interface PrayingTimesProvider {
    PrayingTimes getTimesByCoordinates(double latitude, double longitude, String method);
}
