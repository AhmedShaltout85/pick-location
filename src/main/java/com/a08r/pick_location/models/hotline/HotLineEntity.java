package com.a08r.pick_location.models.hotline;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hot_line_status_data")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;
    @Column(name = "id")
    private Long id;
    @Column(name = "case_report_date_time")
    private String caseReportDateTime;
    @Column(name = "water_stop_d_t")
    private String waterStopingDateTime;
    @Column(name = "case_repair_d_t")
    private String caseRepairDateTime;
    @Column(name = "water_opening_d_t")
    private String waterOpeningDateTime;
    @Column(name = "notes")
    private String notes;
    @Column(name = "final_closed")
    private boolean finalClosed;
    @Column(name = "reporter_name")
    private String reporterName;
    @Column(name = "ref_no")
    private String refNo;
    @Column(name = "street")
    private String street;
    @Column(name = "main_street")
    private String mainStreet;
    @Column(name = "x")
    private String x;
    @Column(name = "y")
    private String y;
    @Column(name = "near_to")
    private String nearTo;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "area")
    private String area;
    @Column(name = "town")
    private String town;
    @Column(name = "sector")
    private String sector;
    @Column(name = "location_name")
    private String locationName;
    @Column(name = "company_acro_name")
    private String companyAcroName;
    @Column(name = "case_type")
    private String caseType;
    @Column(name = "activity_name")
    private String activityName;
    @Column(name = "valves_count")
    private int valvesCount;
    @Column(name = "network")
    private String network;
    @Column(name = "pressure")
    private double pressure;
    @Column(name = "details")
    private String details;
    @Column(name = "extra_data_notes")
    private String extraDataNotes;
    @Column(name = "pipe_diameter")
    private int pipeDiameter;
    @Column(name = "pipe_type")
    private String pipeType;
    @Column(name = "pipe_status")
    private String pipeStatus;
    @Column(name = "pipe_depth")
    private String pipeDepth;
    @Column(name = "pipe_age")
    private String pipeAge;
    @Column(name = "break_length")
    private double breakLength;
    @Column(name = "b_width")
    private double bearkWidth;
    @Column(name = "plant_name")
    private String plantName;
    @Column(name = "plant_status")
    private String plantStatus;
    @Column(name = "affected_areas")
    private String affectedAreas;
    @Column(name = "resons")
    private String resons;
    @Column(name = "repair_type")
    private String repairType;
    @Column(name = "delay_resons")
    private String delayResons;
    @Column(name = "address")
    private String address;


}
