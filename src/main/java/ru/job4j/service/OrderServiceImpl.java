package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Order;
import ru.job4j.domain.OrderStatus;
import ru.job4j.domain.dto.DishDTO;
import ru.job4j.domain.dto.OrderDTO;
import ru.job4j.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private OrderStatusService orderStatusService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByCustomerId(int id) {
        return orderRepository.findByCustomerId(id);
    }

    @Override
    public Optional<Order> save(Order order) {
        Optional<Order> res = Optional.empty();
        try {
            orderRepository.save(order);
            res = Optional.of(order);
        } catch (Exception e) {
            log.error("Save or Update was wrong", e);
        }
        return res;
    }

    @Override
    @KafkaListener(topics = "job4j_orders", groupId = "notification")
    public void receiveOrder(OrderDTO orderDTO) {
        if (log.isDebugEnabled()) {
            log.debug(orderDTO.toString());
        }

        Optional<Order> saved = save(fromOrderDtoToOrder(orderDTO));
        if (saved.isPresent()) {
            orderStatusService.save(new OrderStatus(orderDTO.getId(), orderDTO.getStatus()));
        }
    }

    private Order fromOrderDtoToOrder(OrderDTO orderDTO) {
        Set<String> dishNames = orderDTO.getDishes().stream()
                .map(DishDTO::getName)
                .collect(Collectors.toSet());

        return new Order(
                orderDTO.getId(),
                orderDTO.getCustomer().getId(),
                dishNames
        );
    }
}
