package com.a08r.pick_location.models.dto;


import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickLocationUsersDTO {

    private Long id;
    private String username;
    private String password;
    private int role; //control-room = 1, handasah = 2, technical = 3
    private String controlUnit; //control-level
    private int technicalId; //technical-id= employee-number
}
