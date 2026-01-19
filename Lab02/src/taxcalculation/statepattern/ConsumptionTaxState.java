package taxcalculation.statepattern;

public class ConsumptionTaxState implements TaxState {
    @Override
    public double applyTax(double price) {
        System.out.println("Apply Consumption Tax (10%)");
        return price * 1.10;
    }
}
