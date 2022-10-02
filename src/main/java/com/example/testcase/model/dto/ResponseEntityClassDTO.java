package com.example.testcase.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class ResponseEntityClassDTO {
    private int id;
    private String name;
    private String citizenOrChild;
}
