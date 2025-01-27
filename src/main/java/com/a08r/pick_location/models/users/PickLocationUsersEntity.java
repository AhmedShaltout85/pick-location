package com.a08r.pick_location.models.users;

import jakarta.persistence.*;
import lombok.*;


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
    @Column(name = "role", nullable = false)
    private int role; //control-room = 1, handasah = 2, technical = 3
    @Column(name = "control_unit", nullable = false)
    private String controlUnit; //control-level
    @Column(name = "technical_id", nullable = false)
    private int technicalId; //technical-id= employee-number



}
