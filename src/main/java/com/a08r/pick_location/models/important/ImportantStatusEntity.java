package com.a08r.pick_location.models.important;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "important_status")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImportantStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "important_name")
    private String importantName;
}
