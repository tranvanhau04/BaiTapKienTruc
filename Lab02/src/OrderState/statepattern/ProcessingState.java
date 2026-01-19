package OrderState.statepattern;

public class ProcessingState implements OrderState {
    public void handle() {
        System.out.println("Order processing: packing and shipping.");
    }
}
