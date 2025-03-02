package ru.mfilatov.prayingtimes.timeskeeper.quibla;

import static ru.mfilatov.prayingtimes.timeskeeper.quibla.Constants.MECCA_LATITUDE;
import static ru.mfilatov.prayingtimes.timeskeeper.quibla.Constants.MECCA_LONGITUDE;

import org.springframework.stereotype.Service;

@Service
public class QiblaDirectionCalculator {
  /** Function to calculate Qibla direction in degrees from real North without Magnetic Deviation:*/
  public double calculate(double latitude, double longitude) {
    // Convert latitude and longitude from degrees to radians
    double lat = Math.toRadians(latitude);
    double lon = Math.toRadians(longitude);

    // Calculate the difference in longitude
    double deltaLon = MECCA_LONGITUDE - lon;

    // Calculate the Qibla direction using the formula from
    // https://ftp.unpad.ac.id/orari/library/library-islam/knowledge/qibla_calc.html
    double y = Math.sin(deltaLon);
    double x = Math.cos(lat) * Math.tan(MECCA_LATITUDE) - Math.sin(lat) * Math.cos(deltaLon);
    double qiblaDirection = Math.atan2(y, x);

    // Convert the result from radians to degrees
    qiblaDirection = Math.toDegrees(qiblaDirection);

    // Ensure the result is within 0-360 degrees
    qiblaDirection = (qiblaDirection + 360) % 360;

    return qiblaDirection;
  }
}
