package taxcalculation.decoratorpattern;

public class VATTaxDecorator extends TaxDecorator {

    public VATTaxDecorator(Product product) {
        super(product);
    }

    @Override
    public double getPrice() {
        return product.getPrice() * 1.08;
    }
}
