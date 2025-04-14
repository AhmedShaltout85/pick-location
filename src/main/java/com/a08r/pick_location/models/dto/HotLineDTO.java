package com.a08r.pick_location.models.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotLineDTO {
    private Long id;
    private String caseReportDateTime;
    private boolean finalClosed;
    private String reporterName;
    private String street;
    private String mainStreet;
    private String caseType;
    private String x;
    private String y;
}
