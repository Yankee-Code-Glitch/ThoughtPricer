public class ThoughtPricer {

    private static double findCalorieDensity(FoodContext foodContext) {
        return foodContext.cost() / foodContext.calorieAmt();
    }

    private static double secondsPerCurrency(double costPerCalorie, double conversionRate) {
        final double CALORIES_PER_HOUR_THINKING = 21.875;
        final double SECONDS_IN_HOUR = 3600;

        double sourceCurrencyPerHour = costPerCalorie * CALORIES_PER_HOUR_THINKING;
        double targetCurrencyPerHour = sourceCurrencyPerHour * conversionRate;

        return (1 / targetCurrencyPerHour) * SECONDS_IN_HOUR;
    }

    public static void calculateSecondsPerCurrency() {

        FoodContext foodContext = InputHandler.objectMeasurements();

        double costPerCalorie = ThoughtPricer.findCalorieDensity(foodContext);

        double secondsPerCurrency = ThoughtPricer.secondsPerCurrency(costPerCalorie, CurrencyAPI.getExchangeRate(foodContext));

        boolean isSolid = false;

        if (foodContext.state() == FoodMeasureSystem.KILOS || foodContext.state() == FoodMeasureSystem.POUNDS) {
            isSolid = true;
        }

        IO.println("You'd get ~" + Math.round(secondsPerCurrency) + " seconds of thinking per " + (foodContext.choice() ? foodContext.targetCurrency() : "generic currency") + " by " + (isSolid ? "eating" : "drinking") + " that!");

        if (secondsPerCurrency > 180) {
            int minutesPerCurrency = (int) Math.floor(secondsPerCurrency / 60);
            int secondsLeft = (int) Math.round(secondsPerCurrency % 60);

            IO.println("That's equivalent to ~" + minutesPerCurrency + " minutes" + (secondsLeft > 0 ? " and " + secondsLeft + " seconds" : "") + "!");
        }
    }
}