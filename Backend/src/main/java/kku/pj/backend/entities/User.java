package kku.pj.backend.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    private String username;

    private String password;

    private String email;


    @Column(nullable = false)
    @CreationTimestamp
    private Date create_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;
}
