package com.example.testcase.model.dto;

import com.example.testcase.model.entity.Children;
import lombok.*;

import javax.persistence.Id;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class CitizenDTO {
    @Id
    private int id;
    private String name;
    private boolean beingCitizen;
    private boolean hasDrivingLicense;
    private List<Children> children;
}
