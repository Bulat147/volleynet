package per.khalilov.volleynet.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 64, unique = true)
    private String name;
    private String hashPassword;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Role {
        ADMIN, USER
    }

    public enum Status {
        BLOCKED, ACTIVE
    }
}
