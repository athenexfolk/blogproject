package kku.pj.backend.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imgId;
    private String username;
    private String name;
    private String alt;
    private String url;

    @Column(nullable = false)
    @CreationTimestamp
    private Date create_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;
}
