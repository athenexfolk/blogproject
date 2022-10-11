package kku.pj.backend.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    @Column(nullable = true)
    private Integer imgId;


    @Column(nullable = false)
    @CreationTimestamp
    private Date create_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;


    @OneToOne
    @JoinColumn(name = "imgId",nullable = true, updatable = false, insertable = false)
    private ImageEntity imageEntity;

}
