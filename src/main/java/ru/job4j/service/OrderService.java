package ru.job4j.service;

import ru.job4j.domain.Order;
import ru.job4j.domain.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findById(int id);

    List<Order> findByCustomerId(int id);

    Optional<Order> save(Order order);

    void receiveOrder(OrderDTO orderDTO);

}
