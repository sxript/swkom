package at.fhtw.swkom.paperless.services.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class UserInfo {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private String username;
    @Column
    private String password;

}
