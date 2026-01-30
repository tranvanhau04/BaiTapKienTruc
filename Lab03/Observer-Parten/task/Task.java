import java.util.ArrayList;
import java.util.List;

public class Task implements TaskSubject {

    private String status;
    private List<TaskObserver> observers = new ArrayList<>();

    @Override
    public void register(TaskObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(TaskObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (TaskObserver o : observers) {
            o.update(status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }
}
