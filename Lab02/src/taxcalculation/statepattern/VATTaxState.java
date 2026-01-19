package taxcalculation.statepattern;

public class VATTaxState implements TaxState {
    @Override
    public double applyTax(double price) {
        System.out.println("Apply VAT Tax (8%)");
        return price * 1.08;
    }
}
