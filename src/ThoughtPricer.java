public class ThoughtPricer {

    private static double FindCalorieDensity(FoodItem foodItem) {
        return foodItem.cost() / foodItem.calorieAmt();
    }

    private static double secondsPerARS(double costPerCalorie, double ARSConversionRate) {
        final double CALORIES_PER_HOUR_THINKING = 21.875;
        final double SECONDS_IN_HOUR = 3600;

        double USDPerHour = costPerCalorie * CALORIES_PER_HOUR_THINKING;
        double ARSPerHour = USDPerHour * ARSConversionRate;

        return (1 / ARSPerHour) * SECONDS_IN_HOUR;
    }

    public static void calculateSecondsPerARS() {

        FoodItem foodItem = InputHandler.objectMeasurements();

        double costPerCalorie = ThoughtPricer.FindCalorieDensity(foodItem);

        double secondsPerARS = ThoughtPricer.secondsPerARS(costPerCalorie, CurrencyAPI.getExchangeRate());

        boolean isSolid = false;

        if (foodItem.state() == FoodMeasureSystem.KILOS || foodItem.state() == FoodMeasureSystem.POUNDS) {
            isSolid = true;
        }

        IO.println("You'd get ~" + Math.round(secondsPerARS) + " seconds of thinking per Argentinean peso by " + (isSolid ? "eating" : "drinking") + " that food!");

        if (secondsPerARS > 180) {
            int minutesPerARS = (int) Math.floor(secondsPerARS / 60);
            int secondsLeft = (int) Math.round(secondsPerARS % 60);

            IO.println("That's equivalent to ~" + minutesPerARS + " minutes" + (secondsLeft > 0? " and " + secondsLeft + " seconds" : "") + "!");
        }
    }
}