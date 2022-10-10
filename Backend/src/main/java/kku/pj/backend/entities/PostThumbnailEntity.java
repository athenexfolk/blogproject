package kku.pj.backend.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Post")
public class PostThumbnailEntity {
    @Id
    @Column(name = "postId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = true)
    private Integer imgId;
    private String title;

    @Column(name = "short_content")
    private String shortContent;


    @Column(nullable = false)
    @CreationTimestamp
    private Date create_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;

    @ManyToOne
    @JoinColumn(name = "username",nullable = false,insertable = false,updatable = false)
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "imgId",nullable = true, insertable = false, updatable = false)
    private ImageEntity imageEntity;
}
