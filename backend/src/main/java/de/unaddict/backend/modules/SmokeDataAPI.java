package de.unaddict.backend.modules;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Repository
public class SmokeDataAPI {

    /**
     * Benötigte Infos:
     * <p>
     * Preisgruppen von Zigaretten Marken, damit man diese später abfragen kann (Malboro, LuckyStrike, selbstdrehen etc.
     * Packungsgrößen -> evtl. wichtig für "Preisoptimierung" der savedMoney Variablen.
     * Durchschnittlich gewonnene Lebenszeit pro {Zeiteinheit}, wenn mann mit dem rauchen aufhört
     * Algorithmus der vom Zeitpunkt der Anmeldung aus die Differenz zum aktuellen Zeitpunkt errechnet -> timeNotSmoked
     * Algorithmus der abhängig vom eingehend angegebenen Rauchverhalten und der zurückgelegten, timeNotSmoked die nicht gerauchten Zigaretten ermittelt.
     */

    /**
     * Get´s the saved time of registration from the UserData(in the final Product) puts it
     * and the time from now into the same format and calculates on this base the Difference between both in milliseconds.
     * TimeUnit functionality transforms them into the right form.
     * ReturnString shows up in the Frontend.
     */
    public String getTimeNotSmoked(String userRegistrationTime) throws ParseException {
        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            //gets date and time from now.
            LocalDateTime justNow = LocalDateTime.now();
            //because NOW gives you nanoseconds too, we need to Format it into the right pattern.
            Date now = obj.parse(justNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            //for now, this date already has the right Format, maybe: TODO Check format of the date from the Mongo!
            Date registrationDate = obj.parse(userRegistrationTime);

            //calculates the difference in milliseconds
            long time_diff = now.getTime() - registrationDate.getTime();

            //Transform millisecond values into the corresponding values
            long days_diff = TimeUnit.MILLISECONDS.toDays(time_diff);
            long hour_diff = TimeUnit.MILLISECONDS.toHours(time_diff)%24;
            long minute_diff = TimeUnit.MILLISECONDS.toMinutes(time_diff)%60;
            long seconds_diff = TimeUnit.MILLISECONDS.toSeconds(time_diff)%60;

            return days_diff + " Days " + hour_diff + " Hours " + minute_diff + " Minutes " + seconds_diff + " Seconds";
        }
        catch (ParseException exception){
            exception.printStackTrace();
        }
        return "Hier könnte Ihre Werbung stehen";
    }



}
