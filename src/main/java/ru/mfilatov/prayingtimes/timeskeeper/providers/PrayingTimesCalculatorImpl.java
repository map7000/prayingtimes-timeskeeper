package ru.mfilatov.prayingtimes.timeskeeper.providers;

import org.springframework.stereotype.Component;
import ru.mfilatov.prayingtimes.calculator.PrayingTimesCalculator;
import ru.mfilatov.prayingtimes.calculator.enums.CalculationMethods;
import ru.mfilatov.prayingtimes.calculator.model.Times;
import ru.mfilatov.prayingtimes.timeskeeper.model.Coordinates;
import ru.mfilatov.prayingtimes.timeskeeper.model.TimeZone;

import java.time.LocalDate;

@Component
public class PrayingTimesCalculatorImpl {
    public Times calculate(Coordinates coordinates, LocalDate time, TimeZone timeZone, String method) {
        PrayingTimesCalculator calculator =
                new PrayingTimesCalculator(
                        time,
                        timeZone.utc(),
                        coordinates.latitude(),
                        coordinates.longitude(),
                        CalculationMethods.valueOf(method));

        return calculator.calculate();
    }
}
