package ru.job4j.service;

import ru.job4j.domain.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderStatusService {

    List<OrderStatus> findAll();

    Optional<OrderStatus> findById(int id);

    List<OrderStatus> findByOrderId(int id);

    Optional<OrderStatus> findLastByOrderId(int id);

    Optional<OrderStatus> save(OrderStatus orderStatus);

    boolean delete(int id);

}
