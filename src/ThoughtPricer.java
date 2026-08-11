public class ThoughtPricer {

    public static void calculateSecondsPerCurrency() {

        FoodContext foodContext = InputHandler.getFoodContext();

        double costPerCalorie = MathHandler.findCalorieDensity(foodContext);

        double secondsPerCurrency = MathHandler.secondsPerCurrency(costPerCalorie, CurrencyAPI.getExchangeRate(foodContext));

        boolean isSolid = foodContext.foodUnitOfMeasurement() == FoodMeasureSystem.KILOS || foodContext.foodUnitOfMeasurement() == FoodMeasureSystem.POUNDS;

        String currency = foodContext.shouldConvert() ? foodContext.targetCurrency() : "generic currency";

        if (secondsPerCurrency < 1) {
            IO.println("You couldn't even think for a full second per " + currency + ". That's sad bro.");
        } else {
            IO.println("You'd get ~" + Math.round(secondsPerCurrency) + " seconds of thinking per " + currency + " by " + (isSolid ? "eating" : "drinking") + " that!");
        }
        if (secondsPerCurrency > 180) {
            int minutesPerCurrency = (int) Math.floor(secondsPerCurrency / 60);
            int secondsLeft = (int) Math.round(secondsPerCurrency % 60);

            IO.println("That's equivalent to ~" + minutesPerCurrency + " minutes" + (secondsLeft > 0 ? " and " + secondsLeft + " seconds" : "") + "!");

            MathHandler.comparePennyWeightToFoodWeight(foodContext);
        }
    }
}