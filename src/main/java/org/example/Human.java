package org.example;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "human")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Human {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "human")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Auto> cars;
}
