package com.a08r.pick_location.models.handasatTools;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "handasat_tools")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HandasatToolsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "handasahName")
    private String handasah_name;
    @Column(name = "tool_name")
    private String toolName;
    @Column(name = "tool_qty")
    private int toolQty;

}
