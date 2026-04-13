package fit.iuh.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent extends DomainEvent {
    private Long orderId;
    private String product;
    private Integer quantity;
    private String status;

    public OrderCreatedEvent(Long orderId, String product, Integer quantity, String status) {
        super(orderId.toString());
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }
}

