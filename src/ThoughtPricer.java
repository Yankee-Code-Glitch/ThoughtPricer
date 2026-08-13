public class ThoughtPricer {

    public static void calculateSecondsPerCurrency() {

        FoodContext foodContext = InputHandler.getFoodContext();
        double costPerCalorie = MathHandler.findCalorieDensity(foodContext);
        double secondsPerCurrency = MathHandler.secondsPerCurrency(costPerCalorie, CurrencyAPI.getExchangeRate(foodContext));

        boolean isSolid = foodContext.foodUnitOfMeasurement() == FoodMeasureSystem.KILOS || foodContext.foodUnitOfMeasurement() == FoodMeasureSystem.POUNDS;


        IO.println(Main.messages.getString("result.seconds.part1") +
                Math.round(secondsPerCurrency) +
                Main.messages.getString("result.seconds.part2") +
                (foodContext.shouldConvert() ? foodContext.targetCurrency() : Main.messages.getString("text.generic.currency")) +
                Main.messages.getString("result.seconds.part3") +
                (isSolid ? Main.messages.getString("text.eating") : Main.messages.getString("text.drinking")) +
                Main.messages.getString("result.seconds.part4"));

        if (secondsPerCurrency > 180) {
            int minutesPerCurrency = (int) Math.floor(secondsPerCurrency / 60);
            int secondsLeft = (int) Math.round(secondsPerCurrency % 60);

            IO.println(Main.messages.getString("result.minutes.part1") +
                    minutesPerCurrency +
                    Main.messages.getString("result.minutes.part2") +
                    (secondsLeft > 0 ? Main.messages.getString("result.minutes.part3") + secondsLeft + Main.messages.getString("result.minutes.part4") : "") +
                    Main.messages.getString("result.punctuation.exclamation"));
        }
    }
}