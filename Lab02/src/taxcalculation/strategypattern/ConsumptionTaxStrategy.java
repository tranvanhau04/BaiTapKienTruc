package taxcalculation.strategypattern;

public class ConsumptionTaxStrategy implements TaxStrategy {
    @Override
    public double calculate(double price) {
        return price * 1.10;
    }
}
