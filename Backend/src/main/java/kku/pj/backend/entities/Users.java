package kku.pj.backend.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Users {
    @Id
    private String username;
    private String password;
    private String email;
//    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
//    private Timestamp create_at;
//    @Column(columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP")
//    private Timestamp modified_at;
}
