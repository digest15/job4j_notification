package ru.job4j.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerDTO {

    public CustomerDTO(int id) {
        this.id = id;
    }

    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, unique = true)
    private String name;
}
