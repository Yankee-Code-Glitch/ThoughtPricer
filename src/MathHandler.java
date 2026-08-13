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

    private static double convertToGrams(FoodContext foodContext) {
        return switch (foodContext.foodUnitOfMeasurement()) {
            case KILOS, LITERS -> foodContext.weight() * 1000;
            case POUNDS -> foodContext.weight() * 453.59237;
            case FLUID_OUNCES -> foodContext.weight() * 29.5735295625;
        };
    }

    public static void comparePennyWeightToFoodWeight(FoodContext foodContext) {
        final float WEIGHT_OF_PENNY = 2.5f;

        FoodContext toUSD = new FoodContext(0, 0, foodContext.cost(), foodContext.foodUnitOfMeasurement(), foodContext.sourceCurrency(), "USD", true);
        double exchangeRate = CurrencyAPI.getExchangeRate(toUSD);
        double costInUSD = foodContext.cost() * exchangeRate;

        // Parentheses added to calculate the 100 multiplier BEFORE truncating the decimal
        int numberOfPennies = (int) (costInUSD * 100);
        double weighOfCostInPennies = WEIGHT_OF_PENNY * numberOfPennies;

        double gramWeight = convertToGrams(foodContext);

        if (gramWeight > weighOfCostInPennies) {
            IO.println(Main.messages.getString("result.physical.food.heavier"));
        } else if (weighOfCostInPennies > gramWeight) {
            IO.println(Main.messages.getString("result.physical.money.heavier"));
        } else {
            IO.println(Main.messages.getString("result.physical.balance"));
        }
    }
}
