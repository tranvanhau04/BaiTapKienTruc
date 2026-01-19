package OrderState.strategypattern;

public class Order {
    private OrderStrategy strategy;

    public void setStrategy(OrderStrategy strategy) {
        this.strategy = strategy;
    }

    public void process() {
        strategy.execute();
    }
}
