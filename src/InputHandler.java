import java.util.Scanner;

public class InputHandler {
    public static FoodItem objectMeasurements() {

        Scanner scanner = new Scanner(System.in);
        FoodMeasureSystem state;
        double weight;

        while (true) {
            int foodMeasureInput = (int) ExceptionCheckers.getValidNumber("Is your food measured in kilos(1), pounds(2), liters(3), or fluid ounces(4)?", true);
            foodMeasureInput = (int) ExceptionCheckers.checkIntegerInBounds("Is your food measured in kilos(1), pounds(2), liters(3), or fluid ounces(4)?", "Please only give a number 1-4.", foodMeasureInput, 0, 5, true);

            try {
                state = switch (foodMeasureInput) {
                    case 1 -> {
                        weight = ExceptionCheckers.getValidNumber("How many kilos does it weigh?", false);
                        weight = ExceptionCheckers.checkIntegerInBounds("How many kilos does it weigh?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.KILOS;
                    }
                    case 2 -> {
                        weight = ExceptionCheckers.getValidNumber("How many pounds does it weigh?", false);
                        weight = ExceptionCheckers.checkIntegerInBounds("How many pounds does it weigh?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.POUNDS;
                    }
                    case 3 -> {
                        weight = ExceptionCheckers.getValidNumber("How many liters does it contain?", false);
                        weight = ExceptionCheckers.checkIntegerInBounds("How many liters does it contain?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
                        yield FoodMeasureSystem.LITERS;
                    }
                    case 4 -> {
                        weight = ExceptionCheckers.getValidNumber("How many fluid ounces does it contain?", false);
                        weight = ExceptionCheckers.checkIntegerInBounds("How many fluid ounces does it contain?", "Please enter a positive number.", weight, 0, Integer.MAX_VALUE, false);
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
        calorieAmt = (int) ExceptionCheckers.checkIntegerInBounds("How many calories does your food item have?", "Please enter a whole number.", calorieAmt, 0, Integer.MAX_VALUE, true);

        double cost = ExceptionCheckers.getValidNumber("How much did it cost?", false);
        cost = ExceptionCheckers.checkIntegerInBounds("How much did it cost?", "Please enter a positive number.", cost, 0, Integer.MAX_VALUE, false);

        return new FoodItem(weight, calorieAmt, cost, state);
    }
}
