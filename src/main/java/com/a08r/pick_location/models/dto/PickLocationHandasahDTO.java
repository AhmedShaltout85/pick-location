package com.a08r.pick_location.models.dto;


import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickLocationHandasahDTO {

    private Long id;
    private String handasahName;
    private String storeName;
    private int storeNumber;
}
