package de.unaddict.backend.modules;

import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Repository
public class SmokeDataAPI {

    SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    //gets date and time from now.
    LocalDateTime justNow = LocalDateTime.now();


    /**
     * Benötigte Infos:
     * <p>
     * Preisgruppen von Zigaretten Marken, damit man diese später abfragen kann (Malboro, LuckyStrike, selbstdrehen etc.
     * Packungsgrößen -> evtl. wichtig für "Preisoptimierung" der savedMoney Variablen.
     * Durchschnittlich gewonnene Lebenszeit pro {Zeiteinheit}, wenn mann mit dem rauchen aufhört
     * Algorithmus der vom Zeitpunkt der Anmeldung aus die Differenz zum aktuellen Zeitpunkt errechnet -> timeNotSmoked
     * Algorithmus der abhängig vom eingehend angegebenen Rauchverhalten und der zurückgelegten, timeNotSmoked die nicht gerauchten Zigaretten ermittelt.
     */

    private long timeSpanNonSmoked;
    /**
     * Get´s the saved time of registration from the UserData(in the final Product) puts it
     * and the time from now into the same format and calculates on this base the Difference between both in milliseconds.
     * TimeUnit functionality transforms them into the right form.
     * ReturnString shows up in the Frontend.
     */
    public String getTimeNotSmoked(String userRegistrationTime) throws ParseException {

        try {
            //because NOW gives you nanoseconds too, we need to Format it into the right pattern.
            Date now = obj.parse(justNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            //for now, this date already has the right Format, maybe: TODO Check format of the date from the Mongo!
            Date registrationDate = obj.parse(userRegistrationTime);

            //calculates the difference in milliseconds
            timeSpanNonSmoked = now.getTime() - registrationDate.getTime();

            //Transform millisecond values into the corresponding values
            long days_diff = TimeUnit.MILLISECONDS.toDays(timeSpanNonSmoked);
            long hour_diff = TimeUnit.MILLISECONDS.toHours(timeSpanNonSmoked) % 24;
            long minute_diff = TimeUnit.MILLISECONDS.toMinutes(timeSpanNonSmoked) % 60;
            long seconds_diff = TimeUnit.MILLISECONDS.toSeconds(timeSpanNonSmoked) % 60;

            return days_diff + " Days " + hour_diff + " Hours " + minute_diff + " Minutes " + seconds_diff + " Seconds";
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return "Hier könnte Ihre Werbung stehen";
    }

    public long getNonSmokedCigarettes(int cigarettesSmokedEachDayLastYear, String registrationDate) throws ParseException {
        double cigaretteEachMillisecond = cigarettesSmokedEachDayLastYear*1.0/86400000;

        Date now = obj.parse(justNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        Date userRegistrationTime = obj.parse(registrationDate);
        timeSpanNonSmoked = now.getTime() - userRegistrationTime.getTime();
        double nonSmoked = cigaretteEachMillisecond*timeSpanNonSmoked;
        return ((long) nonSmoked);

    }

}
