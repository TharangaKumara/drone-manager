package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.model.entity.DroneMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneMedicationRepository extends JpaRepository<DroneMedicine, Integer> {
}
