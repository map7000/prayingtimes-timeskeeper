package ru.mfilatov.prayingtimes.timeskeeper.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.aladhan.AladhanTimingsByCity;

@Mapper(imports = {DateTimeFormatter.class, LocalDate.class})
public interface AladhanMapper {
  @Mapping(target = "date", expression = "java(LocalDate.now().format(DateTimeFormatter.ISO_DATE))")
  @Mapping(target = "timezone", source = "entity.data.meta.timezone")
  @Mapping(target = "method", source = "entity.data.meta.method.name")
  @Mapping(target = "fajr", source = "entity.data.timings.fajr")
  @Mapping(target = "sunrise", source = "entity.data.timings.sunrise")
  @Mapping(target = "dhuhr", source = "entity.data.timings.dhuhr")
  @Mapping(target = "asr", source = "entity.data.timings.asr")
  @Mapping(target = "sunset", source = "entity.data.timings.sunset")
  @Mapping(target = "maghrib", source = "entity.data.timings.maghrib")
  @Mapping(target = "isha", source = "entity.data.timings.isha")
  PrayingTimes timingsByCityPrayingTimes(AladhanTimingsByCity entity);
}
