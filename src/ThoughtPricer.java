public class ThoughtPricer {

    public static void calculateSecondsPerCurrency() {

        FoodContext foodContext = InputHandler.getFoodContext();
        double costPerCalorie = MathHandler.findCalorieDensity(foodContext);
        double secondsPerCurrency = MathHandler.secondsPerCurrency(costPerCalorie, CurrencyAPI.getExchangeRate(foodContext));

        boolean isSolid = foodContext.foodUnitOfMeasurement() == FoodMeasureSystem.KILOS || foodContext.foodUnitOfMeasurement() == FoodMeasureSystem.POUNDS;

        String currencyText = foodContext.shouldConvert() ? foodContext.targetCurrency() : Main.messages.getString("text.generic.currency");
        String actionText = isSolid ? Main.messages.getString("text.eating") : Main.messages.getString("text.drinking");

        String secondsOutput = String.format(
                Main.messages.getString("result.seconds.full"),
                Math.round(secondsPerCurrency),
                currencyText,
                actionText
        );
        IO.println(secondsOutput);

        if (secondsPerCurrency > 180) {
            int minutesPerCurrency = (int) Math.floor(secondsPerCurrency / 60);
            int secondsLeft = (int) Math.round(secondsPerCurrency % 60);

            String minutesOutput = String.format(
                    Main.messages.getString("result.minutes.full"),
                    minutesPerCurrency,
                    secondsLeft
            );
            IO.println(minutesOutput);
        }
    }
}