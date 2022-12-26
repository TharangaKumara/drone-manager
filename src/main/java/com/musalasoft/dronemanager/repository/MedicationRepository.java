package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
