package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Integer> {

    String FIND_DRONE_IDS = "SELECT drone_id, battery_percentage FROM DRONE";
    String REDUCE_BATTERY_LEVEL = "update DRONE d set battery_percentage = :battery_percentage where d.drone_id = :drone_id";

    @Modifying
    @Query(value = FIND_DRONE_IDS, nativeQuery = true)
    List<Object[]> getAllDroneIds();


    @Modifying
    @Transactional
    @Query(value = REDUCE_BATTERY_LEVEL,
            nativeQuery = true)
    void updateBatteryLevel(@Param("drone_id") int droneId,
                             @Param("battery_percentage") float batteryPercentage);
}
