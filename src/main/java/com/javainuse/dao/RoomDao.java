package com.javainuse.dao;

import com.javainuse.Enum.RoomType;
import com.javainuse.model.RoomDO;
import com.javainuse.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends CrudRepository<RoomDO, Long> {

    List<RoomDO> findAllByType(RoomType type);

    RoomDO findByUserId(Long userId);
}
