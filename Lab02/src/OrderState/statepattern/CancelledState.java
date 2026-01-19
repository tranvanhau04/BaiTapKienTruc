package OrderState.statepattern;

public class CancelledState implements OrderState {
    public void handle() {
        System.out.println("Order cancelled: refunding money.");
    }
}

