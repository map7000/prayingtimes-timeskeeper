package ru.mfilatov.prayingtimes.timeskeeper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mfilatov.prayingtimes.calculator.model.Times;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.PrayingTimes;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;
import ru.mfilatov.prayingtimes.timeskeeper.providers.LocalPrayingTimeProvider;
import ru.mfilatov.prayingtimes.timeskeeper.providers.PrayingTimesCalculatorImpl;

class LocalPrayingTimeProviderTest {

  @InjectMocks private LocalPrayingTimeProvider prayingTimeProvider;

  @Mock private PrayingTimesCalculatorImpl calculator;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetTimesByCoordinates() {
    Coordinates coordinates = new Coordinates(40.7128, -74.0060);
    TimeZone timeZone = new TimeZone(-4);
    String method = "ISNA";

    Times times =
        new Times(
            OffsetDateTime.parse("2025-02-16T04:30:00-04:00"), // Imsak
            OffsetDateTime.parse("2025-02-16T05:00:00-04:00"), // Fajr
            OffsetDateTime.parse("2025-02-16T06:00:00-04:00"), // Sunrise
            OffsetDateTime.parse("2025-02-16T12:00:00-04:00"), // Dhuhr
            OffsetDateTime.parse("2025-02-16T15:30:00-04:00"), // Asr
            OffsetDateTime.parse("2025-02-16T18:00:00-04:00"), // Sunset
            OffsetDateTime.parse("2025-02-16T18:30:00-04:00"), // Maghrib
            OffsetDateTime.parse("2025-02-16T19:30:00-04:00"), // Isha
            OffsetDateTime.parse("2025-02-17T01:30:00-04:00") // Midnight
            );

    when(calculator.calculate(
            any(Coordinates.class), any(LocalDate.class), any(TimeZone.class), any(String.class)))
        .thenReturn(times);

    PrayingTimes result = prayingTimeProvider.getTimesByCoordinates(coordinates, timeZone, method);

    assertThat(result.date()).isEqualTo(LocalDate.now().toString());
    assertThat(result.timezone()).isEqualTo("-4");
    assertThat(result.method()).isEqualTo("Islamic Society of North America (ISNA)");

    assertThat(result.imsak()).isEqualTo("04:30");
    assertThat(result.fajr()).isEqualTo("05:00");
    assertThat(result.sunrise()).isEqualTo("06:00");
    assertThat(result.dhuhr()).isEqualTo("12:00");
    assertThat(result.asr()).isEqualTo("15:30");
    assertThat(result.sunset()).isEqualTo("18:00");
    assertThat(result.maghrib()).isEqualTo("18:30");
    assertThat(result.isha()).isEqualTo("19:30");
    assertThat(result.midnight()).isEqualTo("01:30");

    verify(calculator, times(1)).calculate(any(), any(), any(), any());
  }
}
