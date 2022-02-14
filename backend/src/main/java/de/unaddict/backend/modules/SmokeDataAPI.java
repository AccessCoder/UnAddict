package de.unaddict.backend.modules;

import org.springframework.stereotype.Repository;

import java.time.*;
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

    private Duration timeSpanNonSmoked;

    public String getTimeNotSmoked(String userRegistrationTime) {

        //because NOW gives you nanoseconds too, we need to Format it into the right pattern.
        Instant registrationDate = Instant.parse(userRegistrationTime);

        //calculates the difference in milliseconds
        timeSpanNonSmoked = Duration.between(Instant.now(), registrationDate);

        //Transform millisecond values into the corresponding values
        long days_diff = timeSpanNonSmoked.toDaysPart();
        long hour_diff = timeSpanNonSmoked.toHoursPart();
        long minute_diff = timeSpanNonSmoked.toMinutesPart();
        long seconds_diff = timeSpanNonSmoked.toSecondsPart();

        return days_diff + " Days " + hour_diff + " Hours " + minute_diff + " Minutes " + seconds_diff + " Seconds";
    }

    public long getNonSmokedCigarettes(int cigarettesSmokedEachDayLastYear, String userRegistrationTime) {
        double cigaretteEachMillisecond = cigarettesSmokedEachDayLastYear * 1.0 / 86400000;

        Instant registrationDate = Instant.parse(userRegistrationTime);
        timeSpanNonSmoked = Duration.between(Instant.now(), registrationDate);
        long timeSpanNonSmokedInMillis= timeSpanNonSmoked.toMillis();
        double nonSmoked = cigaretteEachMillisecond * timeSpanNonSmokedInMillis;
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

    public String getLifetimeSaved(int ageOfUser, String userRegistrationTime) {
        int lost_years = 7;
        double lifetimeRegainedInMilliseconds = 0;

        Instant registrationDate = Instant.parse(userRegistrationTime);
        timeSpanNonSmoked = Duration.between(Instant.now(), registrationDate);
        long timeSpanNonSmokedInMillis= timeSpanNonSmoked.toMillis();

        double lifetimeGained;

        if (ageOfUser <= 30 && ageOfUser > 0) {
            double timeYouCanGetBack = (lost_years * 365) * 0.99;
            lifetimeGained = (timeYouCanGetBack * 86400000)*timeSpanNonSmokedInMillis;
            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }

        } else if (ageOfUser <= 40 && ageOfUser > 30) {
            double timeYouCanGetBack = (lost_years * 365) * 0.90;
            lifetimeGained = (timeYouCanGetBack * 86400000)*timeSpanNonSmokedInMillis;
            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }

        } else if (ageOfUser >= 45 && ageOfUser <= 54) {
            double timeYouCanGetBack = ((lost_years - 2) * 365);
            lifetimeGained = (timeYouCanGetBack * 86400000)*timeSpanNonSmokedInMillis;
            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }
        } else if (ageOfUser >= 55 && ageOfUser <= 64) {
            double timeYouCanGetBack = ((lost_years - 3) * 365);
            lifetimeGained = (timeYouCanGetBack * 86400000)*timeSpanNonSmokedInMillis;
            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }
        } else {
            double timeYouCanGetBack = ((lost_years - 5) * 365);
            lifetimeGained = (timeYouCanGetBack * 86400000)*timeSpanNonSmokedInMillis;
            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }
        }

        long days_diff = TimeUnit.MILLISECONDS.toDays((long) lifetimeGained);
        long hour_diff = TimeUnit.MILLISECONDS.toHours((long) lifetimeGained) % 24;
        long minute_diff = TimeUnit.MILLISECONDS.toMinutes((long) lifetimeGained) % 60;
        long seconds_diff = TimeUnit.MILLISECONDS.toSeconds((long) lifetimeGained) % 60;

        return days_diff + " Days " + hour_diff + " Hours " + minute_diff + " Minutes " + seconds_diff + " Seconds";

    }

}
