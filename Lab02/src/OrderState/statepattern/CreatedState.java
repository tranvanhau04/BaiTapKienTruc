package OrderState.statepattern;

public class CreatedState implements OrderState {
    @Override
    public void handle() {
        System.out.println("Order created: checking order information.");
    }
}
