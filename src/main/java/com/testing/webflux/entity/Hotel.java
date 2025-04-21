package com.testing.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("hotels")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
}