package ru.mfilatov.prayingtimes.timeskeeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.mfilatov.prayingtimes.timeskeeper.clients.GeoTimeZoneClient;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.providers.GeoTimeZoneProvider;

@SpringBootTest
public class GeoTimeZoneProviderTest {
    @Autowired
    GeoTimeZoneClient timeZoneClient;

    @Test
    void test(){
        var timeZoneProvider = new GeoTimeZoneProvider(timeZoneClient);
        var tz = timeZoneProvider.getTimeZone(new Coordinates(55.7505412,37.6174782));
        assertThat(tz.utc()).isEqualTo(3);
    }
}
