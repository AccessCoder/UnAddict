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
     * Get´s the saved time of registration from the UserData puts it
     * and the time from now into the same format and calculates on this base the Difference between both in milliseconds.
     * TimeUnit functionality transforms them into the right form.
     * ReturnString shows up in the Frontend.
     */

    SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    //gets date and time from now.
    LocalDateTime justNow = LocalDateTime.now();
    private long timeSpanNonSmoked;
    private double lifetimeRegained;

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
        double cigaretteEachMillisecond = cigarettesSmokedEachDayLastYear * 1.0 / 86400000;

        Date now = obj.parse(justNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        Date userRegistrationTime = obj.parse(registrationDate);
        timeSpanNonSmoked = now.getTime() - userRegistrationTime.getTime();
        double nonSmoked = cigaretteEachMillisecond * timeSpanNonSmoked;
        return ((long) nonSmoked);

    }

    public double getMoneySavedPerCigarette(int cigarettesBranchCategory) {
        if (cigarettesBranchCategory == 1) {
            return 0.31;
        } else if (cigarettesBranchCategory == 2) {
            return 0.25;
        } else return 0.10;
    }

    /**
     * As it seems to a study published 2013 in the New England Journal of Medicine, that if you stop smoking at various ages, you can return
     * different amounts of Lifetime, relative to your age at the time.
     * <=30 years = you can nearly undo all the damage done, so like 98-99 % of average lifespan
     * <=40 years = last exit if you want to undo most of the damage, in this lifespan, there a chance to undo nearly 90% of the lost lifetime
     * 45-54 years = you can undo aprox. 6 years of Lifetime
     * 55-64 years = 4 years can be get back
     * 65+ = call yourself lucky, if you get back 2 years.
     * <p>
     * Study was done with "long time smokers", but it gives an idea of how much time you can get back (at minimum) if you stop smoking.
     * <p>
     * We will use if statements to put our customer into one of these five categories and them we will call lifetime gained each millisecond for 5 years,
     * after this time, you will most likely "finished" your journey to stop smoking and, if you where to stay strong all the time, had undo most of the damage,
     * due to your Body healing itself.
     */

    public String getLifetimeSaved(int ageOfUser, String registrationDate) throws ParseException {
        int lost_years = 7;
        double lifetimeRegainedInMilliseconds = 0;

        Date now = obj.parse(justNow.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        Date userRegistrationTime = obj.parse(registrationDate);
        timeSpanNonSmoked = now.getTime() - userRegistrationTime.getTime();

        if (ageOfUser <= 30 && ageOfUser > 0) {
            double timeYouCanGetBack = (lost_years * 365) * 0.99;
            double timeYouCanGetBackEachMillisecond = timeYouCanGetBack / 86400000;
            lifetimeRegainedInMilliseconds = timeSpanNonSmoked * timeYouCanGetBackEachMillisecond;
            if (lifetimeRegainedInMilliseconds >= (timeYouCanGetBack*86400000)){
                lifetimeRegainedInMilliseconds = (timeYouCanGetBack*86400000);
            }

        }
        else if (ageOfUser <= 40 && ageOfUser > 30) {
            double timeYouCanGetBack = (lost_years * 365) * 0.90;
            double timeYouCanGetBackEachMillisecond = timeYouCanGetBack / 86400000;
            lifetimeRegainedInMilliseconds = timeSpanNonSmoked * timeYouCanGetBackEachMillisecond;
            if (lifetimeRegainedInMilliseconds >= (timeYouCanGetBack*86400000)){
                lifetimeRegainedInMilliseconds = (timeYouCanGetBack*86400000);
            }

        }
        else if (ageOfUser >= 45 && ageOfUser <= 54) {
            double timeYouCanGetBack = ((lost_years-2) * 365);
            double timeYouCanGetBackEachMillisecond = timeYouCanGetBack / 86400000;
            lifetimeRegainedInMilliseconds = timeSpanNonSmoked * timeYouCanGetBackEachMillisecond;
            if (lifetimeRegainedInMilliseconds >= (timeYouCanGetBack*86400000)){
                lifetimeRegainedInMilliseconds = (timeYouCanGetBack*86400000);
            }
        }
        else if (ageOfUser >= 55 && ageOfUser <= 64) {
            double timeYouCanGetBack = ((lost_years-3) * 365);
            double timeYouCanGetBackEachMillisecond = timeYouCanGetBack / 86400000;
            lifetimeRegainedInMilliseconds = timeSpanNonSmoked * timeYouCanGetBackEachMillisecond;
            if (lifetimeRegainedInMilliseconds >= (timeYouCanGetBack*86400000)){
                lifetimeRegainedInMilliseconds = (timeYouCanGetBack*86400000);
            }
        }
        else {
            double timeYouCanGetBack = ((lost_years-5) * 365);
            double timeYouCanGetBackEachMillisecond = timeYouCanGetBack / 86400000;
            lifetimeRegainedInMilliseconds = timeSpanNonSmoked * timeYouCanGetBackEachMillisecond;
            if (lifetimeRegainedInMilliseconds >= (timeYouCanGetBack*86400000)){
                lifetimeRegainedInMilliseconds = (timeYouCanGetBack*86400000);
            }
        }

        long days_diff = TimeUnit.MILLISECONDS.toDays( (long)lifetimeRegainedInMilliseconds);
        long hour_diff = TimeUnit.MILLISECONDS.toHours((long)lifetimeRegainedInMilliseconds) % 24;
        long minute_diff = TimeUnit.MILLISECONDS.toMinutes((long)lifetimeRegainedInMilliseconds) % 60;
        long seconds_diff = TimeUnit.MILLISECONDS.toSeconds((long)lifetimeRegainedInMilliseconds) % 60;

        return days_diff + " Days " + hour_diff + " Hours " + minute_diff + " Minutes " + seconds_diff + " Seconds";

    }

}
