package com.a08r.pick_location.models.hotline;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "hot_line_data")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotLineDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;
    @Column(name = "customer_id")
    private int CustomerID;
    @Column(name = "phone")
    private String Phone;
    @Column(name = "mobile")
    private String Mobile;
    @Column(name = "title_id")
    private int TitleID;
    @Column(name="home_number")
    private String HomeNumber;
    @Column(name = "floor_number_id")
    private int FloorNumberID;
    @Column(name = "area_id")
    private int AreaID;
    @Column(name = "side_street")
    private String SideStreet;
    @Column(name = "main_street")
    private String MainStreet;
    @Column(name = "near_to")
    private String NearTO;
    @Column(name = "name")
    private String Name;
    @Column(name = "subscribe_no")
    private String SubscribeNo;
    @Column(name = "area_number")
    private String AreaNo;
    @Column(name = "email")
    private String Email;
    @Column(name = "user_id")
    private int UserID;
    @Column(name = "source_id")
    private int SourceID;
    @Column(name = "date_time")
    private Date Date_Time;
    @Column(name = "notes")
    private String Notes;
    @Column(name = "x")
    private String X;
    @Column(name = "y")
    private String Y;
    @Column(name = "sig")
    private String Sig;
    @Column(name = "keys")
    private String Key;
}
