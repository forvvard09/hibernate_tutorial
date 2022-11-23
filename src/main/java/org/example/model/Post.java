package org.example.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NonNull
    private String text;

    @CreationTimestamp
    @Column
    private Timestamp created_at = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

}
