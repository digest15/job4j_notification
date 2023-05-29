package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Override
    List<Order> findAll();

    List<Order> findByCustomerId(int customerId);
}
