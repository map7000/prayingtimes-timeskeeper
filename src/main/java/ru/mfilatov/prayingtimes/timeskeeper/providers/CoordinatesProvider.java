package ru.mfilatov.prayingtimes.timeskeeper.providers;

import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;

public interface CoordinatesProvider {
    public Coordinates getCoordinatesByCityName(String city, String country);
}
