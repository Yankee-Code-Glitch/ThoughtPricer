public class InputHandler {
    public static FoodContext getFoodContext() {

        FoodMeasureSystem foodUnitOfMeasurement;
        double weight;
        String sourceCurrency = "USD";
        String targetCurrency = "USD";
        boolean shouldConvert = false;

        IO.println("Hello!\nThis is a program that converts money into seconds you can think given a food. You'll see what I mean.\nPress ENTER to continue.");
        IO.readln();

        int choiceInput = (int) ExceptionCheckers.getValidNumber("Do you want to convert currencies(1) or stick with normal calculations(2)?", true);
        choiceInput = (int) ExceptionCheckers.checkNumberInBounds("Do you want to convert currencies(1) or stick with normal calculations(2)?", "Please enter only a 1 or a 2.", choiceInput, 0, 2, true);

        if (choiceInput == 1) {
            shouldConvert = true;

            IO.println("What is the currency code for the currency you want to convert from?");
            sourceCurrency = IO.readln().toUpperCase();
            sourceCurrency = ExceptionCheckers.checkStringLengthInBounds("What is the currency code for the currency you want to convert from?", "The currency code should only be 3 characters long.", sourceCurrency, 3);


            IO.println("What is the currency code for the currency you want to convert to?");
            targetCurrency = IO.readln().toUpperCase();
            targetCurrency = ExceptionCheckers.checkStringLengthInBounds("What is the currency code for the currency you want to convert to?", "The currency code should only be 3 characters long.", targetCurrency, 3);
        }

        while (true) {
            int foodMeasureInput = (int) ExceptionCheckers.getValidNumber("Is your food measured in kilos(1), pounds(2), liters(3), or fluid ounces(4)?", true);
            foodMeasureInput = (int) ExceptionCheckers.checkNumberInBounds("Is your food measured in kilos(1), pounds(2), liters(3), or fluid ounces(4)?", "Please only give a number 1-4.", foodMeasureInput, 0, 5, true);

            try {
                foodUnitOfMeasurement = switch (foodMeasureInput) {
                    case 1 -> {
                        weight = ExceptionCheckers.getValidNumber("How many kilos does it weigh?", false);
                        weight = ExceptionCheckers.checkNumberInBounds("How many kilos does it weigh?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.KILOS;
                    }
                    case 2 -> {
                        weight = ExceptionCheckers.getValidNumber("How many pounds does it weigh?", false);
                        weight = ExceptionCheckers.checkNumberInBounds("How many pounds does it weigh?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.POUNDS;
                    }
                    case 3 -> {
                        weight = ExceptionCheckers.getValidNumber("How many liters does it contain?", false);
                        weight = ExceptionCheckers.checkNumberInBounds("How many liters does it contain?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.LITERS;
                    }
                    case 4 -> {
                        weight = ExceptionCheckers.getValidNumber("How many fluid ounces does it contain?", false);
                        weight = ExceptionCheckers.checkNumberInBounds("How many fluid ounces does it contain?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.FLUID_OUNCES;
                    }
                    default -> throw new IllegalArgumentException("Please enter only a number between 1 and 4.");
                };
                break;
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
        }

        int calorieAmt = (int) ExceptionCheckers.getValidNumber("How many calories does your food item have?", true);
        calorieAmt = (int) ExceptionCheckers.checkNumberInBounds("How many calories does your food item have?", "Please enter a whole number.", calorieAmt, 0, Integer.MAX_VALUE, true);

        double cost = ExceptionCheckers.getValidNumber("How much did it cost" + (shouldConvert ? " in " + sourceCurrency : "") + "?", false);
        cost = ExceptionCheckers.checkNumberInBounds("How much did it cost?", "Please enter a positive number.", cost, 0, Integer.MAX_VALUE, false);


        return new FoodContext(weight, calorieAmt, cost, foodUnitOfMeasurement, sourceCurrency, targetCurrency, shouldConvert);
    }
}
