package org.example.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Embedded
    @NonNull
    private Passport passport;


    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false)
    @OneToMany(mappedBy = "user"/*, fetch = FetchType.LAZY*/)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Post> posts;

    @Column(nullable = false)
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;
}
