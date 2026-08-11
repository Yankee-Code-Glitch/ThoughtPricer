public class MathHandler {
    public static double findCalorieDensity(FoodContext foodContext) {
        return foodContext.cost() / foodContext.calorieAmt();
    }

    public static double secondsPerCurrency(double costPerCalorie, double ConversionRate) {
        final double CALORIES_PER_HOUR_THINKING = 21.875;
        final double SECONDS_IN_HOUR = 3600;

        double sourceCurrencyPerHour = costPerCalorie * CALORIES_PER_HOUR_THINKING;
        double targetCurrencyPerHour = sourceCurrencyPerHour * ConversionRate;

        return (1 / targetCurrencyPerHour) * SECONDS_IN_HOUR;
    }
}
