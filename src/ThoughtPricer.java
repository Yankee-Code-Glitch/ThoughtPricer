public class ThoughtPricer {

    public static void calculateSecondsPerCurrency() {
        final int THREE_MINUTES = 180;
        final int ONE_MINUTE = 60;
        FoodContext foodContext = InputHandler.getFoodContext();
        double costPerCalorie = MathHandler.findCalorieDensity(foodContext);
        double secondsPerCurrency;

        if (foodContext.shouldConvert()) {
            secondsPerCurrency = MathHandler.secondsPerCurrency(costPerCalorie, CurrencyAPI.getExchangeRate(foodContext));
        } else {
            secondsPerCurrency = MathHandler.secondsPerCurrency(costPerCalorie, 1);
        }

        String currencyText = foodContext.shouldConvert() ? foodContext.targetCurrency() : Main.messages.getString("text.generic.currency");
        String actionText = foodContext.isSolid() ? Main.messages.getString("text.eating") : Main.messages.getString("text.drinking");

        String secondsOutput = String.format(
                Main.messages.getString("result.seconds.full"),
                Math.round(secondsPerCurrency),
                currencyText,
                actionText
        );
        IO.println(secondsOutput);

        if (secondsPerCurrency > THREE_MINUTES) {
            int minutesPerCurrency = (int) Math.floor(secondsPerCurrency / ONE_MINUTE);
            int secondsLeft = (int) Math.round(secondsPerCurrency % ONE_MINUTE);

            String minutesOutput = String.format(
                    Main.messages.getString("result.minutes.full"),
                    minutesPerCurrency,
                    secondsLeft
            );
            IO.println(minutesOutput);

            MathHandler.comparePennyWeightToFoodWeight(foodContext);
        }
    }
}