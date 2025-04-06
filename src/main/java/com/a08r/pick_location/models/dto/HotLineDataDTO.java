package com.a08r.pick_location.models.dto;


import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotLineDataDTO {
    private int CustomerID;
    private String Phone;
    private String Mobile;
    private int TitleID;
    private String HomeNumber;
    private int FloorNumberID;
    private int AreaID;
    private String SideStreet;
    private String MainStreet;
    private String NearTO;
    private String Name;
    private String SubscribeNo;
    private String AreaNo;
    private String Email;
    private int UserID;
    private int SourceID;
    private Date Date_Time;
    private String Notes;
    private String X;
    private String Y;
    private String Sig;
    private String Key;
}
