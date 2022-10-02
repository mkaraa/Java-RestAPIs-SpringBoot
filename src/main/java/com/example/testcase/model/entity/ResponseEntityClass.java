package com.example.testcase.model.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class ResponseEntityClass {
    private int id;
    private String name;
    private String citizenOrChild;
}
