public class InputHandler {
    public static FoodContext getFoodContext() {

        FoodMeasureSystem state;
        double weight;
        String sourceCurrency = "USD";
        String targetCurrency = "USD";
        boolean shouldConvert = false;

        IO.println(Main.messages.getString("greeting.intro").replace("\\n", "\n"));
        IO.readln();

        int choiceInput = (int) ExceptionCheckers.getValidNumberInBounds(
                Main.messages.getString("prompt.menu.choice"),
                Main.messages.getString("error.invalid.choice.menu"),
                0, 2, true);

        if (choiceInput == 1) {
            shouldConvert = true;

            sourceCurrency = ExceptionCheckers.getValidStringLength(
                    Main.messages.getString("prompt.currency.source"),
                    Main.messages.getString("error.invalid.currency.length"),
                    3);

            targetCurrency = ExceptionCheckers.getValidStringLength(
                    Main.messages.getString("prompt.currency.target"),
                    Main.messages.getString("error.invalid.currency.length"),
                    3);
        }

        int foodMeasureInput = (int) ExceptionCheckers.getValidNumberInBounds(
                Main.messages.getString("prompt.food.measure"),
                Main.messages.getString("error.invalid.choice.measure"),
                0, 4, true);

        state = switch (foodMeasureInput) {
            case 1 -> {
                weight = ExceptionCheckers.getValidNumberInBounds(Main.messages.getString("prompt.weight.kilos"), Main.messages.getString("error.invalid.positive.number"), 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.KILOS;
            }
            case 2 -> {
                weight = ExceptionCheckers.getValidNumberInBounds(Main.messages.getString("prompt.weight.pounds"), Main.messages.getString("error.invalid.positive.number"), 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.POUNDS;
            }
            case 3 -> {
                weight = ExceptionCheckers.getValidNumberInBounds(Main.messages.getString("prompt.weight.liters"), Main.messages.getString("error.invalid.positive.number"), 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.LITERS;
            }
            case 4 -> {
                weight = ExceptionCheckers.getValidNumberInBounds(Main.messages.getString("prompt.weight.ounces"), Main.messages.getString("error.invalid.positive.number"), 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.FLUID_OUNCES;
            }
            default -> throw new IllegalStateException(Main.messages.getString("error.invalid.measure.range"));
        };

        int calorieAmt = (int) ExceptionCheckers.getValidNumberInBounds(
                Main.messages.getString("prompt.calories"),
                Main.messages.getString("error.invalid.whole.number"),
                -1, Integer.MAX_VALUE, true);

        double cost = ExceptionCheckers.getValidNumberInBounds(
                Main.messages.getString("prompt.cost"),
                Main.messages.getString("error.invalid.positive.number"),
                0, Integer.MAX_VALUE, false);

        return new FoodContext(weight, calorieAmt, cost, state, sourceCurrency, targetCurrency, shouldConvert);
    }
}