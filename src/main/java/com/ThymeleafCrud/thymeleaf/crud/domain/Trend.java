package com.ThymeleafCrud.thymeleaf.crud.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "trends")
public class Trend {

    @Id
    private String id;
    private String trend1;
    private String trend2;
    private String trend3;
    private String trend4;
    private String trend5;
    private LocalDateTime timestamp;
    private String proxyIp;
}
