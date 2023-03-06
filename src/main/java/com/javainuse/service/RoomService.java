package com.javainuse.service;

import com.javainuse.Enum.RoomType;
import com.javainuse.dao.RoomDao;
import com.javainuse.dao.UserDao;
import com.javainuse.model.RoomDO;
import com.javainuse.model.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;

    public RoomDO bookRoom(RoomDTO roomDTO) throws Exception {
        RoomDO userBookedRoom = roomDao.findByUserId(roomDTO.getUserId());
        if (Objects.nonNull(userBookedRoom)) {
            throw new Exception("You hava book a room.");
        }
        RoomDO presentRoom = roomDao.findById(roomDTO.getId()).orElse(new RoomDO());
        if (presentRoom.getType().equals(RoomType.BOOKED)) {
            throw new Exception("Room is booked.");
        }
        RoomDO roomDO = new RoomDO();
        roomDO.setId(roomDTO.getId());
        roomDO.setUserId(roomDTO.getUserId());
        roomDO.setType(RoomType.BOOKED);
        return roomDao.save(roomDO);
    }

    public List<RoomDO> listRooms(RoomType type) {
        return roomDao.findAllByType(type);
    }

    public RoomDO cancelRoom(RoomDTO roomDTO) throws Exception {
        RoomDO userBookedRoom = roomDao.findByUserId(roomDTO.getUserId());
        if (Objects.isNull(userBookedRoom)) {
            throw new Exception("You hava not book a room.");
        }

        RoomDO roomDO = new RoomDO();
        roomDO.setId(userBookedRoom.getId());
        roomDO.setType(RoomType.AVAILABLE);
        return roomDao.save(roomDO);
    }
}
