package taxcalculation.decoratorpattern;

public class ConsumptionTaxDecorator extends TaxDecorator {

    public ConsumptionTaxDecorator(Product product) {
        super(product);
    }

    @Override
    public double getPrice() {
        return product.getPrice() * 1.10;
    }
}
