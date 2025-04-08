package com.a08r.pick_location.models.handasatTools;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "[tools_requests]")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersRequestsToolsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "handasah_name")
    private String handasahName;
    @Column(name = "tool_name")
    private String toolName;
    @Column(name = "tool_qty")
    private int toolQty;
    private String techName;
    private Date date;
    private int requestStatus;
    private int isApproved;
    private String address;

}
