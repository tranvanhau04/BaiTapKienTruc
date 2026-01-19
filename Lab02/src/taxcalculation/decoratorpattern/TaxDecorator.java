package taxcalculation.decoratorpattern;

public abstract class TaxDecorator implements Product {
    protected Product product;

    public TaxDecorator(Product product) {
        this.product = product;
    }
}
