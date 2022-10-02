package com.example.testcase.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "citizens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"citizen_id"})
public class Citizen {
    @Id
    @SequenceGenerator(name = "seq_citizen", allocationSize = 1)
    @GeneratedValue(generator = "seq_citizen", strategy = GenerationType.SEQUENCE)
    @Column(name = "citizen_id", updatable = false, nullable = false)
    private int citizenId;

    @JsonProperty(namespace = "name")
    @Column(name = "name")
    private String name;

    @JsonProperty(namespace = "isCitizen")
    @Column(name = "is_citizen")
    private boolean beingCitizen;

    @JsonProperty(namespace = "hasDrivingLicense")
    @Column(name = "has_driving_license")
    private boolean hasDrivingLicense;


    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval=true)
    @JoinColumn(name = "citizen_child_id")
    private List<Children> children;

}
