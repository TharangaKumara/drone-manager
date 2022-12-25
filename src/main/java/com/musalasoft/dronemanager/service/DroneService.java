package com.musalasoft.dronemanager.service;

import com.musalasoft.dronemanager.model.entity.Drone;
import com.musalasoft.dronemanager.model.entity.Medication;

import java.util.List;

public interface DroneService {


    Drone registerNewDrone(Drone drone);

    Drone loadMedicationToTheDrone(Integer droneId, List<Integer> medicationList);

    List<Medication> getLoadedMedicationsByDroneId(Integer droneId);

    Drone getDroneDetailsById(Integer droneId);

    float getDroneBatteryLevelById(Integer droneId);

    List<Drone> getAllAvailableDrones();

    List<Drone> getAllDrones();

}
