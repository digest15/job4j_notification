package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Order;
import ru.job4j.domain.OrderStatus;
import ru.job4j.repository.OrderStatusRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderStatusServiceImpl implements OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }

    @Override
    public Optional<OrderStatus> findById(int id) {
        return orderStatusRepository.findById(id);
    }

    @Override
    public List<OrderStatus> findByOrderId(int id) {
        return orderStatusRepository.findByOrderId(new Order(id));
    }

    @Override
    public Optional<OrderStatus> findLastByOrderId(int id) {
        return orderStatusRepository.findLastByOrderIdOrderByCreationDate(id);
    }

    @Override
    public Optional<OrderStatus> save(OrderStatus orderStatus) {
        try {
            orderStatus = orderStatusRepository.save(new OrderStatus(
                            orderStatus.getOrderId(),
                            orderStatus.getStatus()
                    )
            );
        } catch (Exception e) {
            log.error("Save or Update was wrong", e);
        }

        return orderStatus.getId() == 0
                ? Optional.of(orderStatus)
                : Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
        orderStatus.ifPresent(orderStatusRepository::delete);
        return orderStatus.isPresent();
    }
}
