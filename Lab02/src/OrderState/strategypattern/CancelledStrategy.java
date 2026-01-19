package OrderState.strategypattern;

public class CancelledStrategy implements OrderStrategy {
    public void execute() {
        System.out.println("Cancelled order: refund issued.");
    }
}

