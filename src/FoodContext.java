public record FoodContext(double weight, int calorieAmt, double cost, FoodMeasureSystem foodUnitOfMeasurement,
                          String sourceCurrency, String targetCurrency, boolean shouldConvert, boolean isSolid) {
}