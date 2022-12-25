package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.model.entity.DroneBatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneBatteryHistoryRepository extends JpaRepository<DroneBatteryHistory, Integer> {
}
