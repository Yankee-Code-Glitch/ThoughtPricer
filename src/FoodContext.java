public record FoodContext(double weight, int calorieAmt, double cost, FoodMeasureSystem state,
                          String sourceCurrency, String targetCurrency, boolean choice) {
}