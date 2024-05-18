package com.tigasinestor.local.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locals")
@Builder
public class Local {
    @Id
    @SequenceGenerator(
            name = "local_sequence",
            sequenceName = "local_sequence"
    )
    @GeneratedValue(
            generator = "local_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long localId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "floor", nullable = false, length = 100)
    private String floor;
}
