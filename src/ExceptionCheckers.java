public class ExceptionCheckers {
    public static double getValidNumber(String userPrompt, boolean isInteger) {
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
                IO.println("Please enter a valid " + (isInteger ? "integer" : "decimal") + " and try again.");
            }
        }
        return validNumber;
    }

    public static double checkIntegerInBounds(String prompt, String tryAgainMessage, double comparedNum, double lowerBound, double upperBound) {
        while (comparedNum <= lowerBound || comparedNum > upperBound) {
            clearScreen();
            IO.println(tryAgainMessage);
            comparedNum = getValidNumber(prompt, true);
        }
        clearScreen();
        return comparedNum;
    }

    public static void clearScreen() {
        IO.println("\033[H\033[2J");
        System.out.flush();
    }
}

