package kku.pj.backend.entities;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    private long id;
    private String alt;
    private String name;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String username;
}
