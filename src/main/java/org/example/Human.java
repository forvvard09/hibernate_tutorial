package org.example;

import lombok.*;

import javax.persistence.*;
import java.util.List;




@Table(name = "human")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Human {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

   @Embedded
   @NonNull
   private Passport passport;

    @OneToMany(mappedBy = "human", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Auto> cars;
}
