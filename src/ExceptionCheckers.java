public class ExceptionCheckers {

    private static double getValidNumber(String userPrompt, boolean isInteger) {
        boolean isInvalid = true;
        double validNumber = 0;

        while (isInvalid) {
            IO.println(userPrompt);
            try {
                if (isInteger) {
                    validNumber = Integer.parseInt(IO.readln());
                } else {
                    validNumber = Double.parseDouble(IO.readln());
                }
                isInvalid = false;
            } catch (NumberFormatException e) {
                clearScreen();
                String numberType = isInteger ? Main.messages.getString("text.integer") : Main.messages.getString("text.decimal");
                IO.println(String.format(Main.messages.getString("error.invalid.format"), numberType));
            }
        }
        return validNumber;
    }

    public static double getValidNumberInBounds(String prompt, String tryAgainMessage, double lowerBound, double upperBound, boolean isInteger) {
        double userInput;
        while (true) {
            userInput = getValidNumber(prompt, isInteger);
            if (userInput > lowerBound && userInput <= upperBound) {
                break;
            }
            clearScreen();
            IO.println(tryAgainMessage);
        }
        clearScreen();
        return userInput;
    }

    public static String getValidStringLength(String prompt, String tryAgainMessage, int requiredLength) {
        String userInput;
        while (true) {
            IO.println(prompt);
            userInput = IO.readln();
            if (userInput.length() == requiredLength) {
                break;
            }
            clearScreen();
            IO.println(tryAgainMessage);
        }
        clearScreen();
        return userInput;
    }

    public static void clearScreen() {
        IO.println("\033[H\033[2J");
        System.out.flush();
    }
}