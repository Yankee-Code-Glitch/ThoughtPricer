public class ThoughtPricer {

    public static void calculateSecondsPerCurrency() {

        FoodContext foodContext = InputHandler.getFoodContext();

        double costPerCalorie = MathHandler.findCalorieDensity(foodContext);

        double secondsPerCurrency = MathHandler.secondsPerCurrency(costPerCalorie, CurrencyAPI.getExchangeRate(foodContext));

        boolean isSolid = foodContext.state() == FoodMeasureSystem.KILOS || foodContext.state() == FoodMeasureSystem.POUNDS;

        IO.println("You'd get ~" + Math.round(secondsPerCurrency) + " seconds of thinking per " + (foodContext.choice() ? foodContext.targetCurrency() : "generic currency") + " by " + (isSolid ? "eating" : "drinking") + " that!");

        if (secondsPerCurrency > 180) {
            int minutesPerCurrency = (int) Math.floor(secondsPerCurrency / 60);
            int secondsLeft = (int) Math.round(secondsPerCurrency % 60);

            IO.println("That's equivalent to ~" + minutesPerCurrency + " minutes" + (secondsLeft > 0 ? " and " + secondsLeft + " seconds" : "") + "!");
        }
    }
}