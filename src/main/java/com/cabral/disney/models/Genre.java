package com.cabral.disney.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"movieAssociations"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "genreAssociations", fetch = FetchType.LAZY)
    private Set<Movie> movieAssociations = new HashSet<>();
}
