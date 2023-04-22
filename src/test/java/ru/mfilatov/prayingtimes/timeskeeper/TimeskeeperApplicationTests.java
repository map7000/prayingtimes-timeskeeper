package ru.mfilatov.prayingtimes.timeskeeper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mfilatov.prayingtimes.timeskeeper.clients.AladhanRestApiClient;
import ru.mfilatov.prayingtimes.timeskeeper.mappers.AladhanMapper;

@Slf4j
@SpringBootTest
class TimeskeeperApplicationTests {
  @Autowired AladhanRestApiClient client;
  private AladhanMapper mapper = Mappers.getMapper(AladhanMapper.class);

  @Test
  void contextLoads() {
    var aladhanTimes =
        client.getTimingsByCity(
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            "Moscow",
            "Russia",
            "14");
    var prayingTimes = mapper.timingsByCityPrayingTimes(aladhanTimes);
    log.info(prayingTimes.toString());
  }
}
