package com.javainuse.model;

import com.javainuse.Enum.RoomType;
import lombok.Data;

@Data
public class RoomDTO {

    private long id;

    private Long userId;

    private RoomType type;
}
