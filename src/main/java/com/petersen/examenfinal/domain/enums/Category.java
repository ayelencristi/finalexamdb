package com.petersen.examenfinal.domain.enums;

import lombok.Getter;

@Getter
public enum Category {

    LACTEOS("Lacteos"),
    BEBIDAS("Bebidas"),
    ALMACEN("Almacen"),
    LIMPIEZA("Limpieza");

    private final String description;
    Category(String description) {
        this.description = description;
    }
}
