package OrderState.strategypattern;

public class ProcessingStrategy implements OrderStrategy {
    public void execute() {
        System.out.println("Processing order: packing and shipping.");
    }
}

