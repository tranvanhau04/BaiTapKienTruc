package OrderState.strategypattern;

public class DeliveredStrategy implements OrderStrategy {
    public void execute() {
        System.out.println("Delivered order: order completed.");
    }
}
