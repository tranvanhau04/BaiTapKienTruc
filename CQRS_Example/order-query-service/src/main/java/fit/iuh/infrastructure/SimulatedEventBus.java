package fit.iuh.infrastructure;

import fit.iuh.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class SimulatedEventBus {

    private static final Map<Class<?>, List<EventListener>> listeners = new HashMap<>();

    @FunctionalInterface
    public interface EventListener {
        void handle(DomainEvent event);
    }

    /**
     * Subscribe to domain events
     */
    public <T extends DomainEvent> void subscribe(Class<T> eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
        log.info("Subscribed to event: {}", eventType.getSimpleName());
    }

    /**
     * Publish domain events
     */
    public void publish(DomainEvent event) {
        log.info("Publishing event: {} with ID: {}", event.getClass().getSimpleName(), event.getEventId());

        List<EventListener> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            eventListeners.forEach(listener -> {
                try {
                    listener.handle(event);
                    log.debug("Event handled successfully: {}", event.getClass().getSimpleName());
                } catch (Exception e) {
                    log.error("Error handling event: {}", event.getClass().getSimpleName(), e);
                }
            });
        } else {
            log.warn("No listeners found for event: {}", event.getClass().getSimpleName());
        }
    }

    /**
     * Get all listeners for debugging
     */
    public Map<Class<?>, List<EventListener>> getListeners() {
        return new HashMap<>(listeners);
    }
}

