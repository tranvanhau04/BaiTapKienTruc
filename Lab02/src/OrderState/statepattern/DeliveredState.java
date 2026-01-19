package OrderState.statepattern;

public class DeliveredState implements OrderState {
    public void handle() {
        System.out.println("Order delivered: updating delivery status.");
    }
}
