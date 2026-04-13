package fit.iuh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

// Lớp Event đơn giản
@Data
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private String type; // "ORDER_CREATED" hoặc "ORDER_CANCELLED"
}
