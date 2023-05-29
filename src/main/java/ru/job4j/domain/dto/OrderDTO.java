package ru.job4j.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.job4j.domain.Status;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDTO {

    @EqualsAndHashCode.Include
    private int id;

    private CustomerDTO customer;

    private List<DishDTO> dishes;

    private Status status;
}
