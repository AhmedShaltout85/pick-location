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
    @Column(name = "tech_name")
    private String techName;
    @Column(name = "date")
    private Date date;
    @Column(name = "request_status")
    private int requestStatus;
    @Column(name = "is_approved")
    private int isApproved;
    @Column(name = "address")
    private String address;

}
