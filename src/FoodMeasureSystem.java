public enum FoodMeasureSystem {
    KILOS(1), POUNDS(2), LITERS(3), FLUID_OUNCES(3);

    private final int foodMeasureIndex;

    FoodMeasureSystem(int foodMeasureIndex) {
        this.foodMeasureIndex = foodMeasureIndex;
    }

    public int getFoodMeasureIndex() {
        return this.foodMeasureIndex;
    }
}
