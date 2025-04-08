package com.a08r.pick_location.models.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestsToolsDTO {
    private String handasahName;
    private String toolName;
    private int toolQty;
    private String techName;
    private Date date;
    private int requestStatus;
    private int isApproved;
    private String address;
}
