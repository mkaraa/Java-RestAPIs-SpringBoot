package com.example.testcase.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "children")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"child_id"})
public class Children implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_citizen", allocationSize = 1)
    @GeneratedValue(generator = "seq_citizen", strategy = GenerationType.SEQUENCE)
    @Column(name = "child_id", updatable = false, nullable = false)
    private int childId;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "citizen_child_id")
    private Citizen citizen;
}