package fit.iuh.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class DomainEvent implements Serializable {
    private String eventId;
    private LocalDateTime timestamp;
    private String aggregateId;

    protected DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = java.util.UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }
}

