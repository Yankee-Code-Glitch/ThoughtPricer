import java.util.Scanner;

public class InputHandler {
    public static FoodItem ObjectMeasurements() {

        Scanner scanner = new Scanner(System.in);
        FoodMeasureSystem state;
        double weight;

        while (true)
        {
            IO.println("Is your food measured in kilos(1), pounds(2), liters(3), or fluid ounces(4)?");
            int foodMeasureInput = scanner.nextInt();
            scanner.nextLine();

            try {
                state = switch (foodMeasureInput) {
                    case 1 -> {
                        IO.println("How many kilos does it weigh");
                        weight = scanner.nextDouble();
                        scanner.nextLine();

                        yield FoodMeasureSystem.KILOS;
                    }
                    case 2 -> {
                        IO.println("How many pounds does it weigh");
                        weight = scanner.nextDouble();
                        scanner.nextLine();

                        yield FoodMeasureSystem.POUNDS;
                    }
                    case 3 -> {
                        IO.println("How many liters does it weigh");
                        weight = scanner.nextDouble();
                        scanner.nextLine();

                        yield FoodMeasureSystem.LITERS;
                    }
                    case 4 -> {
                        IO.println("How many fluid ounces does it weigh");
                        weight = scanner.nextDouble();
                        scanner.nextLine();

                        yield FoodMeasureSystem.FLUID_OUNCES;
                    }
                    default -> throw new IllegalArgumentException("Please enter only a number between 1 and 4.");
                };
                break;
            } catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
            }
        }

        int calorieAmt = scanner.nextInt();
        scanner.nextLine();

        double cost = scanner.nextDouble();
        scanner.nextLine();

        return new FoodItem(weight, calorieAmt, cost, state);
    } // End of ObjectMeasurements
}
