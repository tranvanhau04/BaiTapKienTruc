package fit.iuh.service;

import fit.iuh.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderIntegrationService {

    private static final String COMMAND_SERVICE_URL = "http://localhost:8081/api/orders";
    private static final String QUERY_SERVICE_URL = "http://localhost:8082/api/orders";

    private final RestTemplate restTemplate;

    public OrderIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Create order via Command Service
     */
    public OrderDTO createOrder(String product, Integer quantity) {
        try {
            log.info("Sending create order request to Command Service");
            OrderDTO orderRequest = OrderDTO.builder()
                    .product(product)
                    .quantity(quantity)
                    .build();

            OrderDTO response = restTemplate.postForObject(
                    COMMAND_SERVICE_URL,
                    orderRequest,
                    OrderDTO.class
            );
            log.info("Order created successfully: {}", response);
            return response;
        } catch (RestClientException e) {
            log.error("Error creating order via Command Service", e);
            throw new RuntimeException("Failed to create order", e);
        }
    }

    /**
     * Cancel order via Command Service
     */
    public OrderDTO cancelOrder(Long orderId, String reason) {
        try {
            log.info("Sending cancel order request to Command Service for Order ID: {}", orderId);
            OrderDTO cancelRequest = OrderDTO.builder()
                    .build();

            String url = COMMAND_SERVICE_URL + "/cancel/" + orderId;
            restTemplate.put(url, cancelRequest);

            // Fetch updated order from Query Service
            return getOrderById(orderId);
        } catch (RestClientException e) {
            log.error("Error cancelling order via Command Service", e);
            throw new RuntimeException("Failed to cancel order", e);
        }
    }

    /**
     * Get all orders from Query Service
     */
    public List<OrderDTO> getAllOrders() {
        try {
            log.info("Fetching all orders from Query Service");
            OrderDTO[] response = restTemplate.getForObject(
                    QUERY_SERVICE_URL,
                    OrderDTO[].class
            );
            return response != null ? Arrays.asList(response) : List.of();
        } catch (RestClientException e) {
            log.error("Error fetching orders from Query Service", e);
            return List.of();
        }
    }

    /**
     * Get order by ID from Query Service
     */
    public OrderDTO getOrderById(Long orderId) {
        try {
            log.info("Fetching order with ID: {} from Query Service", orderId);
            OrderDTO response = restTemplate.getForObject(
                    QUERY_SERVICE_URL + "/" + orderId,
                    OrderDTO.class
            );
            return response;
        } catch (RestClientException e) {
            log.error("Error fetching order from Query Service", e);
            return null;
        }
    }
}

