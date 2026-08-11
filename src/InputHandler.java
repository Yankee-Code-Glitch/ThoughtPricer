public class InputHandler {
    public static FoodContext getFoodContext() {

        FoodMeasureSystem state;
        double weight;
        String sourceCurrency = "USD";
        String targetCurrency = "USD";
        boolean shouldConvert = false;

        IO.println("Hello!\nThis is a program that converts money into seconds you can think given a food. You'll see what I mean.\nPress ENTER to continue.");
        IO.readln();

        int choiceInput = (int) ExceptionCheckers.getValidNumberInBounds(
                "Do you want to convert currencies(1) or stick with normal calculations(2)?",
                "Please enter only a 1 or a 2.",
                0, 2, true);

        if (choiceInput == 1) {
            shouldConvert = true;

            sourceCurrency = ExceptionCheckers.getValidStringLength(
                    "What is the currency code for the currency you want to convert from?",
                    "The currency code should only be exactly 3 characters long.",
                    3);

            targetCurrency = ExceptionCheckers.getValidStringLength(
                    "What is the currency code for the currency you want to convert to?",
                    "The currency code should only be exactly 3 characters long.",
                    3);
        }

        int foodMeasureInput = (int) ExceptionCheckers.getValidNumberInBounds(
                "Is your food measured in kilos(1), pounds(2), liters(3), or fluid ounces(4)?",
                "Please only give a number 1-4.",
                0, 4, true);

        state = switch (foodMeasureInput) {
            case 1 -> {
                weight = ExceptionCheckers.getValidNumberInBounds("How many kilos does it weigh?", "Please enter a positive number.", 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.KILOS;
            }
            case 2 -> {
                weight = ExceptionCheckers.getValidNumberInBounds("How many pounds does it weigh?", "Please enter a positive number.", 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.POUNDS;
            }
            case 3 -> {
                weight = ExceptionCheckers.getValidNumberInBounds("How many liters does it contain?", "Please enter a positive number.", 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.LITERS;
            }
            case 4 -> {
                weight = ExceptionCheckers.getValidNumberInBounds("How many fluid ounces does it contain?", "Please enter a positive number.", 0, Integer.MAX_VALUE, false);
                yield FoodMeasureSystem.FLUID_OUNCES;
            }
            //This default case shouldn't even be possible btw
            default -> throw new IllegalStateException("Unexpected system state: " + foodMeasureInput);
        };

        int calorieAmt = (int) ExceptionCheckers.getValidNumberInBounds(
                "How many calories does your food item have?",
                "Please enter a positive whole number.",
                -1, Integer.MAX_VALUE, true);

        double cost = ExceptionCheckers.getValidNumberInBounds(
                "How much did it cost?",
                "Please enter a positive number.",
                0, Integer.MAX_VALUE, false);


        return new FoodContext(weight, calorieAmt, cost, state, sourceCurrency, targetCurrency, shouldConvert);
    }
}