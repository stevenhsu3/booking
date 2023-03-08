package com.javainuse.service;

import com.javainuse.Enum.RoomType;
import com.javainuse.dao.RoomDao;
import com.javainuse.model.RoomDO;
import com.javainuse.model.RoomDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoomServiceTest {

    @Mock
    private RoomDao roomDao;

    @InjectMocks
    private RoomService roomService = new RoomService();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cancelRoomWhenUserHasNotBookedARoomThenThrowException() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setUserId(1L);
        Mockito.when(roomDao.findByUserId(roomDTO.getUserId())).thenReturn(null);

        Exception exception = Assertions.assertThrows(Exception.class, () -> roomService.cancelRoom(roomDTO));

        Assert.assertEquals("You hava not book a room.", exception.getMessage());
    }

    @Test
    public void cancelRoomWhenUserHasBookedARoomThenReturnTheRoom() throws Exception {
        RoomDO roomDO = new RoomDO();
        roomDO.setId(1L);
        roomDO.setType(RoomType.AVAILABLE);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(1L);
        roomDTO.setUserId(1L);

        Mockito.when(roomDao.findByUserId(roomDTO.getUserId())).thenReturn(roomDO);
        Mockito.when(roomDao.save(roomDO)).thenReturn(roomDO);

        RoomDO result = roomService.cancelRoom(roomDTO);

        Assertions.assertEquals(result, roomDO);
    }

    @Test
    public void listRoomsWhenTypeIsBookedThenReturnAllRooms() {
        RoomDO roomDO = new RoomDO();
        roomDO.setId(1L);
        roomDO.setUserId(1L);
        roomDO.setType(RoomType.BOOKED);

        Mockito.when(roomDao.findAllByType(RoomType.BOOKED)).thenReturn(Collections.singletonList(roomDO));

        List<RoomDO> rooms = roomService.listRooms(RoomType.BOOKED);

        Assertions.assertEquals(1, rooms.size());
    }

    @Test
    public void listRoomsWhenTypeIsAvailableThenReturnAllRooms() {
        RoomDO roomDO = new RoomDO();
        roomDO.setId(1L);
        roomDO.setUserId(1L);
        roomDO.setType(RoomType.AVAILABLE);

        Mockito.when(roomDao.findAllByType(RoomType.AVAILABLE)).thenReturn(Collections.singletonList(roomDO));

        List<RoomDO> rooms = roomService.listRooms(RoomType.AVAILABLE);

        Assertions.assertEquals(1, rooms.size());
    }

    @Test
    public void bookRoomWhenTheRoomIsAlreadyBookedThenThrowException() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(1L);
        roomDTO.setUserId(1L);
        roomDTO.setType(RoomType.BOOKED);

        RoomDO roomDO = new RoomDO();
        roomDO.setId(1L);
        roomDO.setUserId(1L);
        roomDO.setType(RoomType.BOOKED);

        Mockito.when(roomDao.findById(roomDTO.getId())).thenReturn(Optional.of(roomDO));

        Exception exception = Assertions.assertThrows(Exception.class, () -> roomService.bookRoom(roomDTO));

        Assert.assertEquals("Room is booked.", exception.getMessage());
    }

    @Test
    public void bookRoomWhenUserHasAlreadyBookedARoomThenThrowException() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(1L);
        roomDTO.setUserId(1L);
        roomDTO.setType(RoomType.AVAILABLE);

        RoomDO roomDO = new RoomDO();
        roomDO.setId(1L);
        roomDO.setUserId(1L);
        roomDO.setType(RoomType.BOOKED);

        Mockito.when(roomDao.findByUserId(roomDTO.getUserId())).thenReturn(roomDO);

        Exception exception = Assertions.assertThrows(Exception.class, () -> roomService.bookRoom(roomDTO));

        Assert.assertEquals("You hava book a room.", exception.getMessage());
    }

    @Test
    public void bookRoomWhenUserHasNotBookedARoomAndTheRoomIsAvailableThenSaveTheRoom() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(1L);
        roomDTO.setUserId(1L);

        RoomDO presentRoom = new RoomDO();
        presentRoom.setId(1L);
        presentRoom.setUserId(1L);
        presentRoom.setType(RoomType.AVAILABLE);

        RoomDO roomDO = new RoomDO();
        roomDO.setId(1L);
        roomDO.setUserId(1L);
        roomDO.setType(RoomType.BOOKED);

        Mockito.when(roomDao.findByUserId(1L)).thenReturn(null);
        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.of(presentRoom));
        Mockito.when(roomDao.save(roomDO)).thenReturn(roomDO);

        RoomDO actual = roomService.bookRoom(roomDTO);

        Assert.assertEquals(roomDO, actual);
    }
}