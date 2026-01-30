package observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyAll(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}
