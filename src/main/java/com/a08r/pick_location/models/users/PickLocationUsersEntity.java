package com.a08r.pick_location.models.users;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

@Entity
@Table(name = "pick_location_users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PickLocationUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false)

    private String userName;


    @Column(name = "user_password", nullable = false)
    private String password;


}
