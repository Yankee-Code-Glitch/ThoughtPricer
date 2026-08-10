public class Main {
    static void main(String[] args) {
        calculateSecondsPerARS();
    }

    static void calculateSecondsPerARS() {
        IO.println(secondsPerARS(FindCalorieDensity(ObjectMeasurements()), 1500));
    }

    public enum measurements {

    }

    private static double[] ObjectMeasurements() {
        /*Measurement indexes:
        0: weight/fluidAmt
        1: Calorie amount
        2: Cost of product
        3: isSolid?
        4: IsMetric?
         */

        double[] measurements = new double[5];

        measurements[3] = ExceptionCheckers.getValidNumber("Is your food a solid or a liquid? Enter 1 for solid and 0 for liquid.", true);
        measurements[3] = ExceptionCheckers.checkIntegerInBounds("Is your food a solid or a liquid? Enter 1 for solid and 0 for liquid.", "Please enter only a 1 or a 0.", (int) measurements[3], -1, 1);

        measurements[4] = ExceptionCheckers.getValidNumber("Are you measuring using the metric or imperial system? Enter 1 for metric and 0 for imperial.", true);
        measurements[4] = ExceptionCheckers.checkIntegerInBounds("Are you measuring using the metric or imperial system? etner 1 for metric and 0 for imperial.", "Please enter only a 1 or a 0", (int) measurements[4], -1, 1);


        if(measurements[3] == 1) {
            double weight;

            if(measurements[4] == 1) {
                weight = ExceptionCheckers.getValidNumber("How many kilos does it weigh?", false);
            }

            else {
                weight = ExceptionCheckers.getValidNumber("How many pounds does it weigh?", false);
            }
            measurements[0] = weight;
        }

        else {
            double fluidAmt;

            if(measurements[4] == 1) {
                fluidAmt = ExceptionCheckers.getValidNumber("How many liters does it contain?", false);
            }

            else {
                fluidAmt = ExceptionCheckers.getValidNumber("How many fluid ounces does it contain?", false);
            }
            measurements[0] = fluidAmt;
        }
        measurements[1] = ExceptionCheckers.getValidNumber("How many calories does it contain?", true);
        measurements[2] = ExceptionCheckers.getValidNumber("How much does it cost in USD?", false);

        return measurements;
    } // End of ObjectMeasurements

    private static double FindCalorieDensity(double[] measurements) {
         return measurements[2] / measurements[1];
    }

    private static double secondsPerARS(double costPerCalorie, double ARSConversionRate) {
        double USDPerHour = costPerCalorie * 21.875;
        double ARSPerHour = USDPerHour * ARSConversionRate;

        return (1/ARSPerHour) * 3600;
    }
}
