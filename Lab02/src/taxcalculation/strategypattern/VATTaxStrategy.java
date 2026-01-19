package taxcalculation.strategypattern;

public class VATTaxStrategy implements TaxStrategy {
    @Override
    public double calculate(double price) {
        return price * 1.08;
    }
}
