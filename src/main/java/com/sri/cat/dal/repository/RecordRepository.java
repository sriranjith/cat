package com.sri.cat.dal.repository;

import com.sri.cat.dal.entity.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CrudRepository<Room, Long> {
    Room getRoomsByRoomId(long roomId);
}
