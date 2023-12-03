package ru.mfilatov.prayingtimes.timeskeeper.providers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mfilatov.prayingtimes.timeskeeper.clients.GeoTimeZoneClient;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

public class GeoTimeZoneProvider implements TimeZoneProvider{
    private final GeoTimeZoneClient client;

    @Autowired
    public GeoTimeZoneProvider(GeoTimeZoneClient client) {
        this.client = client;
    }
    @Override
    public TimeZone getTimeZone(Coordinates coordinates) {
        var rs = client.getTimeZone(coordinates.latitude(), coordinates.longitude());
        return new TimeZone(Integer.valueOf(rs.offset().substring(3)));
    }
}
