package ru.job4j.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    public Order(int id) {
        this.id = id;
    }

    @EqualsAndHashCode.Include
    @Id
    private int id;

    @Column(name = "customer_id")
    private int customerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders_dishes",
            joinColumns = { @JoinColumn(name = "order_id") })
    @Column(name = "dish_name")
    private Set<String> dishNames = new HashSet<>();
}
