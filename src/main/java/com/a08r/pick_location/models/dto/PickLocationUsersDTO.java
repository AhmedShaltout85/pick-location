package com.a08r.pick_location.models.dto;


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
//    private String role;
}
