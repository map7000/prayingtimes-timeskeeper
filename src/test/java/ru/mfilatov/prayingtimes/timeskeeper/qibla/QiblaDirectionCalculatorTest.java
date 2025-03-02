package ru.mfilatov.prayingtimes.timeskeeper.qibla;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.mfilatov.prayingtimes.timeskeeper.quibla.QiblaDirectionCalculator;

import static org.assertj.core.api.Assertions.assertThat;

public class QiblaDirectionCalculatorTest {
        private final QiblaDirectionCalculator calculator = new QiblaDirectionCalculator();

        @ParameterizedTest(name = "{index}: For ({0}, {1}) expected angle is {2}")
        @CsvSource({
                "21.4225,39.8262, 0", // Makkah
                "21.4225, 90, 0",
                "90, 39.8262, 0",
                "90, 90, 0",
                "0, 0, 0",
                "55.7557,37.6146, 176.35", // Moscow
                "27.8507,33.4534, 136.60" // Kabul
        })
        void calculateTest(double latitude, double longitude, double expectedAngle) {
            double actualAngle = calculator.calculate(latitude, longitude);
            assertThat(actualAngle).isCloseTo(expectedAngle, Percentage.withPercentage(0.01));
        }
}
