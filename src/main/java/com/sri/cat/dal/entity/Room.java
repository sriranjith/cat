package com.sri.cat.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "ROOM")
@Data
public class Room {

    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "ROOM_NUMBER")
    @NotNull
    private String roomNumber;

    @Column(name = "BED_INFO")
    @NotNull
    private String bedInfo;

}
