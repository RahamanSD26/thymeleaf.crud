package com.ThymeleafCrud.thymeleaf.crud.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Product {
    private String id;
    private String name;
    private String price;
    private String description;
}
