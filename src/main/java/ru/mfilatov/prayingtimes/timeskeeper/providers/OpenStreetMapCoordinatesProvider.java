package ru.mfilatov.prayingtimes.timeskeeper.providers;

import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;

public class OpenStreetMapCoordinatesProvider implements CoordinatesProvider{
    @Override
    public Coordinates getCoordinatesByCityName(String city, String country) {
        return null;
    }
}
