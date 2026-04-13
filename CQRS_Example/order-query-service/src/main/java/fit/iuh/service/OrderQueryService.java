package fit.iuh.service;

import fit.iuh.dto.OrderQueryResponse;
import fit.iuh.readmodel.OrderReadModel;
import fit.iuh.repository.OrderReadModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class OrderQueryService {

    private final OrderReadModelRepository readModelRepository;

    public OrderQueryService(OrderReadModelRepository readModelRepository) {
        this.readModelRepository = readModelRepository;
    }

    /**
     * Get all orders from read model
     */
    public List<OrderQueryResponse> getAllOrders() {
        log.info("Fetching all orders from read model");
        return readModelRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get order by ID from read model
     */
    public Optional<OrderQueryResponse> getOrderById(Long id) {
        log.info("Fetching order with ID: {} from read model", id);
        return readModelRepository.findById(id)
                .map(this::convertToResponse);
    }

    private OrderQueryResponse convertToResponse(OrderReadModel readModel) {
        return OrderQueryResponse.builder()
                .id(readModel.getId())
                .product(readModel.getProduct())
                .quantity(readModel.getQuantity())
                .status(readModel.getStatus())
                .createdAt(readModel.getCreatedAt())
                .updatedAt(readModel.getUpdatedAt())
                .build();
    }
}

