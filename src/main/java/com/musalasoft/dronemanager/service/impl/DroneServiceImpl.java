package com.musalasoft.dronemanager.service.impl;

import com.musalasoft.dronemanager.exception.DataNotFoundException;
import com.musalasoft.dronemanager.exception.DroneNotAvailableException;
import com.musalasoft.dronemanager.exception.ExceedMaximumWeightLimitException;
import com.musalasoft.dronemanager.exception.MedicationLoadingFailedException;
import com.musalasoft.dronemanager.model.entity.Drone;
import com.musalasoft.dronemanager.model.entity.DroneMedicine;
import com.musalasoft.dronemanager.model.entity.Medication;
import com.musalasoft.dronemanager.repository.DroneMedicationRepository;
import com.musalasoft.dronemanager.repository.DroneRepository;
import com.musalasoft.dronemanager.repository.MedicationRepository;
import com.musalasoft.dronemanager.service.DroneService;
import com.musalasoft.dronemanager.util.DroneState;
import com.musalasoft.dronemanager.util.WaitCalculationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneServiceImpl.class);
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneMedicationRepository droneMedicationRepository;
    private final WaitCalculationUtil waitCalculationUtil;

    @Override
    public List<Drone> getAllDrones() {
        LOGGER.info("Get all drones in the system");
        List<Drone> droneList = droneRepository.findAll();
        LOGGER.info("[{}] of Drones exist in the system", droneList.size());
        return droneList;
    }

    @Override
    public Drone registerNewDrone(Drone drone) {
        if (!DroneState.IDLE.equals(drone.getDroneState())){
            throw new IllegalArgumentException("Drone status should be IDLE when you register it !");
        }
        return droneRepository.save(drone);
    }

    @Override
    @Transactional
    public Drone loadMedicationToTheDrone(Integer droneId, List<Integer> medicationIdList) {

        Drone givenDrone = getDroneDetailsById(droneId);

        checkDroneAvailabilityForLoadMedicine(givenDrone);

        /*Optional<Drone> optionalDrone = getAllAvailableDrones().stream()
                .filter(drone -> droneId.equals(drone.getDroneId()))
                .findAny();*/
        checkRequestedMedicationWeightWithAvailableWeight(medicationIdList, givenDrone);

        loadMedicineToDrone(droneId, medicationIdList);

        return getDroneDetailsById(droneId);
    }

    private void loadMedicineToDrone(Integer droneId, List<Integer> medicationIdList) {

        try {
            List<DroneMedicine> droneMedicines = medicationIdList.stream()
                    .map(medicine -> droneMedicationRepository.save(
                            DroneMedicine
                                    .builder()
                                    .droneId(droneId)
                                    .medicineId(medicine)
                                    .loadedDate(new Date())
                                    .build()))
                    .collect(Collectors.toList());
            LOGGER.info("Successfully load the medication list : [{}] to Drone : [{}]", droneMedicines, droneId);
        } catch (Exception ex) {
            LOGGER.error("Failed to load the medication list : [{}] to Drone : [{}]", medicationIdList, droneId);
            throw new MedicationLoadingFailedException("Failed to load the medication to the Drone !", ex.getMessage());
        }


    }

    @Override
    public List<Medication>  getLoadedMedicationsByDroneId(Integer droneId) {
        List<Medication> medications = getDroneDetailsById(droneId).getMedications();
        if (medications.isEmpty()) {
            return Collections.emptyList();
        }
        return medications;
    }

    @Override
    public Drone getDroneDetailsById(Integer droneId) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        if (optionalDrone.isPresent()) {
            LOGGER.info("Drone found for id - [{}]", droneId);
            return optionalDrone.get();
        } else {
            LOGGER.info("Drone not found for id - [{}]", droneId);
            throw new DataNotFoundException(String.format("Drone not found for id %s", droneId));
        }
    }

    @Override
    public float getDroneBatteryLevelById(Integer droneId) {
        Drone givenDrone = getDroneDetailsById(droneId);
        return givenDrone.getBatteryPercentage();
    }

    @Override
    public List<Drone> getAllAvailableDrones() {
        List<Drone> droneList = droneRepository.findAll();
        List<DroneState> availableStates = new ArrayList<>();
        availableStates.add(DroneState.IDLE);
        availableStates.add(DroneState.LOADING);
        availableStates.add(DroneState.RETURNING);

        List<Drone> availableDrones = droneList.stream()
                .filter(drone -> availableStates.contains(drone.getDroneState()))
                .filter(drone -> drone.getBatteryPercentage() > 25)
                .filter(drone -> drone.getWeightLimit() < 500)
                .filter(waitCalculationUtil::isLoadable)
                .collect(Collectors.toList());

        LOGGER.info("Available Drone list : [{}]", availableDrones);

        return availableDrones;
    }

    private void checkDroneAvailabilityForLoadMedicine(Drone givenDrone) {

        DroneState droneState = givenDrone.getDroneState();

        if (DroneState.LOADED.equals(droneState)||DroneState.DELIVERED.equals(droneState)||DroneState.DELIVERING.equals(droneState)){
            throw new DroneNotAvailableException("Given Drone is not in available state right now!");
        }

        if (givenDrone.getBatteryPercentage() < 25){
            LOGGER.error("Battery Level is lower than 25% in drone id [{}]", givenDrone.getDroneId());
            throw new DroneNotAvailableException("Battery Level is "
                    + givenDrone.getBatteryPercentage()
                    +" for drone id "
                    + givenDrone.getDroneId());
        }

        if (!waitCalculationUtil.isLoadable(givenDrone)){
            throw new DroneNotAvailableException("Given Drone is reached it's max weight limit right now!");
        }

    }

    private Medication getMedicationDetailsById(Integer medicationId){
        Optional<Medication> optionalMedication = medicationRepository.findById(medicationId);
        if (optionalMedication.isPresent()) {
            LOGGER.info("Medication found for id - [{}]", medicationId);
            return optionalMedication.get();
        } else {
            LOGGER.info("Medication not found for id - [{}]", medicationId);
            throw new DataNotFoundException(String.format("Medication not found for id %s", medicationId));
        }
    }

    private void checkRequestedMedicationWeightWithAvailableWeight(List<Integer> medicationIdList, Drone givenDrone) {

        float freeWeightForMedications = waitCalculationUtil.calculateAvailableMedicineWeight(givenDrone);

        List<Medication> requestedMedicationList = medicationIdList.stream()
                .map(this::getMedicationDetailsById).collect(Collectors.toList());

        float requestedMedicationWeight = waitCalculationUtil.calculateLoadedMedicineWeight(requestedMedicationList);

        if (freeWeightForMedications < requestedMedicationWeight) {
            throw new ExceedMaximumWeightLimitException("Can not proceed with this drone. Not enough weight limit.");
        }
    }

}
