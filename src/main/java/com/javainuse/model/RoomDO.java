package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javainuse.Enum.RoomType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "room")
public class RoomDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomType type;
}
