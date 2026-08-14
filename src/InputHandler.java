import java.util.Locale;
import java.util.ResourceBundle;

public class InputHandler {
    public static FoodContext getFoodContext() {

        FoodMeasureSystem foodUnitOfMeasurement;
        double weight;
        String sourceCurrency = "USD";
        String targetCurrency = "USD";
        boolean shouldConvert = false;

        ResourceBundle englishBundle = ResourceBundle.getBundle("messages", Locale.US);
        ResourceBundle spanishBundle = ResourceBundle.getBundle("messages", new Locale("es", "MX"));

        String selectLangPrompt = String.format("%s / %s%s", englishBundle.getString("lang.select"), spanishBundle.getString("lang.select"), englishBundle.getString("lang.options"));
        String selectLangError = String.format("%s / %s", englishBundle.getString("error.invalid.choice.menu"), spanishBundle.getString("error.invalid.choice.menu"));

        int langChoice = (int) ExceptionCheckers.getValidNumberInBounds(selectLangPrompt, selectLangError, 0, 2, true);

        if (langChoice == 2) {
            Main.messages = spanishBundle;
        }

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

        foodUnitOfMeasurement = switch (foodMeasureInput) {
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

        boolean isSolid = foodUnitOfMeasurement == FoodMeasureSystem.KILOS || foodUnitOfMeasurement == FoodMeasureSystem.POUNDS;
        String caloriePrompt = (isSolid ? Main.messages.getString("prompt.calories.solid") : Main.messages.getString("prompt.calories.liquid"));

        int calorieAmt = (int) ExceptionCheckers.getValidNumberInBounds(
                caloriePrompt,
                Main.messages.getString("error.invalid.whole.number"),
                -1, Integer.MAX_VALUE, true);

        double cost = ExceptionCheckers.getValidNumberInBounds(
                Main.messages.getString("prompt.cost"),
                Main.messages.getString("error.invalid.positive.number"),
                0, Integer.MAX_VALUE, false);

        return new FoodContext(weight, calorieAmt, cost, foodUnitOfMeasurement, sourceCurrency, targetCurrency, shouldConvert, isSolid);
    }
}