public class ThoughtPricer {

    private static double FindCalorieDensity(FoodItem foodItem) {
        return foodItem.cost() / foodItem.calorieAmt();
    }

    private static double secondsPerARS(double costPerCalorie, double ARSConversionRate) {
        final double CALORIES_PER_HOUR_THINKING = 21.875;
        final double SECONDS_IN_HOUR = 3600;

        double USDPerHour = costPerCalorie * CALORIES_PER_HOUR_THINKING;
        double ARSPerHour = USDPerHour * ARSConversionRate;

        return (1 / ARSPerHour) * SECONDS_IN_HOUR;
    }

    static void calculateSecondsPerARS() {

        FoodItem foodItem = InputHandler.ObjectMeasurements();

        double costPerCalorie = ThoughtPricer.FindCalorieDensity(foodItem);

        double secondsPerARS = ThoughtPricer.secondsPerARS(costPerCalorie, 1500);

        boolean isSolid = false;

        double minutesPerARS;

        if  (foodItem.state() == FoodMeasureSystem.KILOS || foodItem.state() == FoodMeasureSystem.POUNDS) {isSolid = true;}

        IO.println("You'd get " + secondsPerARS + " seconds of thinking per Argentinean peso by "  + (isSolid ? "eating" : "drinking")  + "that food!");

        if (secondsPerARS > 300) {minutesPerARS = secondsPerARS/60; IO.println("That's equivalent to " + minutesPerARS + " minutes!");}
    }
}