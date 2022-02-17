package de.unaddict.backend.modules;

import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.concurrent.TimeUnit;

@Repository
public class SmokeDataAPI {

    /**
     * Get´s the saved time of registration from the UserData
     * and the time from now and calculates on this base the Difference between both.
     * In the end, we transform then into the correct value for days, hours, minutes and seconds.
     * ReturnString shows up in the Frontend.
     */

    private Duration timeSpanNonSmoked;

    public String getTimeNotSmoked(Instant userRegistrationTime) {

        timeSpanNonSmoked = Duration.between(userRegistrationTime, Instant.now());

        long days_diff = timeSpanNonSmoked.abs().toDaysPart();
        long hour_diff = timeSpanNonSmoked.abs().toHoursPart();
        long minute_diff = timeSpanNonSmoked.abs().toMinutesPart();
        long seconds_diff = timeSpanNonSmoked.abs().toSecondsPart();

        return days_diff + " Days \n" + hour_diff + " Hours " + "\n" + minute_diff + " Minutes " + "\n" + seconds_diff + " Seconds";
    }

    /**
     * Calculates the non smoked Cigarettes by getting the cigarettes that the user smoked each day last year from the Database
     * after that, we turn it into the value of "SmokedLastYearEachMillisecond" to calculate the exact amount of
     * cigarettes that was not smoked at every time the User refreshed the Dashboard.
     */

    public long getNonSmokedCigarettes(int cigarettesSmokedEachDayLastYear, Instant userRegistrationTime) {
        double cigaretteEachMillisecond = cigarettesSmokedEachDayLastYear * 1.0 / 86400000;

        timeSpanNonSmoked = Duration.between(Instant.now(), userRegistrationTime);
        long timeSpanNonSmokedInMillis = timeSpanNonSmoked.abs().toMillis();
        double nonSmoked = cigaretteEachMillisecond * timeSpanNonSmokedInMillis;
        return ((long) nonSmoked);

    }

    /**
     * The User can choose a category of cigarette branch, when he signs in.
     * Each category has its own price point:
     * 1. Premium (Marlboro, Lucky Strike, etc.) about 0.31€ per cigarette
     * 2. Discounter (Boston, Edison, Giants, etc.) about 0.25€ per cigarette
     * 3. Selfmade about 0.15€ per cigarette.
     */

    public double getMoneySavedPerCigarette(int cigarettesBranchCategory) {
        if (cigarettesBranchCategory == 1) {
            return 0.31;
        } else if (cigarettesBranchCategory == 2) {
            return 0.25;
        } else return 0.15;
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

    public String getLifetimeSaved(int ageOfUser, Instant userRegistrationTime) {
        int lost_years = 7;
        double factor = 5 * 365d;

        timeSpanNonSmoked = Duration.between(Instant.now(), userRegistrationTime);
        long timeSpanNonSmokedInMillis = timeSpanNonSmoked.abs().toMillis();

        double lifetimeGained;

        if (ageOfUser <= 30 && ageOfUser > 0) {
            double timeYouCanGetBack = (lost_years * 365) * 0.99;
            factor = timeYouCanGetBack / factor;
            lifetimeGained = timeSpanNonSmokedInMillis * factor;

            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }

        } else if (ageOfUser <= 40 && ageOfUser > 30) {
            double timeYouCanGetBack = (lost_years * 365) * 0.90;
            factor = timeYouCanGetBack / factor;
            lifetimeGained = timeSpanNonSmokedInMillis * factor;

            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }

        } else if (ageOfUser >= 45 && ageOfUser <= 54) {
            double timeYouCanGetBack = ((lost_years - 2) * 365);
            factor = timeYouCanGetBack / factor;
            lifetimeGained = timeSpanNonSmokedInMillis * factor;

            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }
        } else if (ageOfUser >= 55 && ageOfUser <= 64) {
            double timeYouCanGetBack = ((lost_years - 3) * 365);
            factor = timeYouCanGetBack / factor;
            lifetimeGained = timeSpanNonSmokedInMillis * factor;

            if (lifetimeGained >= (timeYouCanGetBack * 86400000)) {
                lifetimeGained = (timeYouCanGetBack * 86400000);
            }
        } else {
            double timeYouCanGetBack = ((lost_years - 5) * 365);
            factor = timeYouCanGetBack / factor;
            lifetimeGained = timeSpanNonSmokedInMillis * factor;

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
