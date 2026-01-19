package OrderState.strategypattern;

public class CreatedStrategy implements OrderStrategy {
    public void execute() {
        System.out.println("Created order: checking information.");
    }
}

