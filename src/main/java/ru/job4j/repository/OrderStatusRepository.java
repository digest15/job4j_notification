package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Order;
import ru.job4j.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {
    @Override
    List<OrderStatus> findAll();

    List<OrderStatus> findByOrderId(Order order);

    Optional<OrderStatus> findLastByOrderIdOrderByCreationDate(int orderId);
}
