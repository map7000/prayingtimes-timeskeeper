package ru.mfilatov.prayingtimes.timeskeeper.providers;

import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

public interface TimeZoneProvider {
    public TimeZone getTimeZone(Coordinates coordinates);
}
