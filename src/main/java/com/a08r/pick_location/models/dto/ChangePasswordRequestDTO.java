package com.a08r.pick_location.models.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {
    private String userName;      // identifies the user
    private String oldPassword;   // must match current password
    private String newPassword;   // new password to set
}