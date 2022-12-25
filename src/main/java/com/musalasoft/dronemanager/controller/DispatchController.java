package com.musalasoft.dronemanager.controller;

import com.musalasoft.dronemanager.model.entity.Drone;
import com.musalasoft.dronemanager.model.entity.Medication;
import com.musalasoft.dronemanager.service.DroneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drones")
@RequiredArgsConstructor
@Tag(name = "Dispatcher Controller", description = "the Drone Management API")
public class DispatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchController.class);

    private final DroneService droneService;


    @PostMapping("/")
    public ResponseEntity<Drone> registerNewDrone(@RequestBody @Valid Drone drone) {
        LOGGER.info("Registering a new drone to the system : [{}]", drone);
        Drone newDrone = droneService.registerNewDrone(drone);
        return new ResponseEntity<>(newDrone, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/medications")
    public ResponseEntity<Drone> loadMedicationToTheDrone(@PathVariable(name = "id") Integer droneId,
                                                   @RequestBody List<Integer> medicationList) {
        LOGGER.info("Load the medication for drone id [{}] with medicines [{}]", droneId, medicationList);
        Drone droneWithMedicine = droneService.loadMedicationToTheDrone(droneId, medicationList);
        return new ResponseEntity<>(droneWithMedicine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedicationsByDroneId(@PathVariable(name = "id") Integer droneId) {
        LOGGER.info("Get the loaded medication list for drone id : [{}] ", droneId);
        return ResponseEntity.ok(droneService.getLoadedMedicationsByDroneId(droneId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneDetailsById(@PathVariable(name = "id") Integer droneId) {
        LOGGER.info("Get drone Details for id [{}]", droneId);
        return ResponseEntity.ok(droneService.getDroneDetailsById(droneId));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAllAvailableDrones() {
        LOGGER.info("Get all available drones for load medicine");
        List<Drone> allAvailableDrones = droneService.getAllAvailableDrones();
        return ResponseEntity.ok(allAvailableDrones);
    }

    @GetMapping("/{id}/battery-level")
    public ResponseEntity<Float> getDroneBatteryLevelById(@PathVariable(name = "id") Integer droneId) {
        LOGGER.info("Get the battery level of drone : [{}]", droneId);
        return ResponseEntity.ok(droneService.getDroneBatteryLevelById(droneId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Drone>> getAllDrones() {
        LOGGER.info("Get all drones registered in the system");
        List<Drone> allDrones = droneService.getAllDrones();
        return ResponseEntity.ok(allDrones);
    }

}
