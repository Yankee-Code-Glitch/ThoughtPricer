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

    public static double checkNumberInBounds(String userPrompt, String tryAgainMessage, double comparedNum, double lowerBound, double upperBound, boolean isInteger) {
        while (comparedNum <= lowerBound || comparedNum > upperBound) {
            clearScreen();
            IO.println(tryAgainMessage);
            comparedNum = getValidNumber(userPrompt, isInteger);
        }
        clearScreen();
        return comparedNum;
    }

    public static String checkStringLengthInBounds(String prompt, String tryAgainMessage, String comparedString, double equalityBound) {

        while (comparedString.length() != equalityBound) {
            clearScreen();
            IO.println(tryAgainMessage);
            IO.println(prompt);
            comparedString = IO.readln();
        }
        clearScreen();

        return comparedString;
    }

    public static void clearScreen() {
        IO.println("\033[H\033[2J");
        System.out.flush();
    }
}

