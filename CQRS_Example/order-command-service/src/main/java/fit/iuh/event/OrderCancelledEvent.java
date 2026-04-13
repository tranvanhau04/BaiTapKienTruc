package fit.iuh.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCancelledEvent extends DomainEvent {
    private Long orderId;
    private String reason;

    public OrderCancelledEvent(Long orderId, String reason) {
        super(orderId.toString());
        this.orderId = orderId;
        this.reason = reason;
    }
}

