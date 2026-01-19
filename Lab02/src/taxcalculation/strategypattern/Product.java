package taxcalculation.strategypattern;

public class Product {
    private double price;
    private TaxStrategy taxStrategy;

    public Product(double price) {
        this.price = price;
    }

    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public void calculateFinalPrice() {
        System.out.println("Final price: " +
                taxStrategy.calculate(price));
    }
}
