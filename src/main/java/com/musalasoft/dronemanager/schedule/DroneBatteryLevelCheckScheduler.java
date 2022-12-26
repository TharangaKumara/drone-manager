package com.musalasoft.dronemanager.schedule;

import com.musalasoft.dronemanager.exception.BatteryLevelUpdateFailedException;
import com.musalasoft.dronemanager.model.entity.Drone;
import com.musalasoft.dronemanager.model.entity.DroneBatteryHistory;
import com.musalasoft.dronemanager.repository.DroneBatteryHistoryRepository;
import com.musalasoft.dronemanager.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class DroneBatteryLevelCheckScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneBatteryLevelCheckScheduler.class);

    private final DroneRepository droneRepository;
    private final DroneBatteryHistoryRepository droneBatteryHistoryRepository;



    @Scheduled(fixedDelayString = "120000")
    public void checkDroneBatteryLevelAndLog() {
        List<Object[]> droneList = droneRepository.getAllDroneIds();
        LOGGER.info("[{}] of Drones retrieved for battery level check", droneList.size());

        try {
            List<DroneBatteryHistory> droneBatteryHistories = droneList.stream()
                    .map(drone -> droneBatteryHistoryRepository.save(
                            DroneBatteryHistory
                                    .builder()
                                    .droneId((Integer) drone[0])
                                    .batteryPercentage((Float) drone[1])
                                    .loggedTime(new Date())
                                    .build()))
                    .collect(Collectors.toList());
            LOGGER.info("Successfully added the log to the Data Base for Drones : [{}] ", droneBatteryHistories);
        } catch (Exception ex) {
            LOGGER.error("Failed to add the log to the Data Base for Drones");
            throw new BatteryLevelUpdateFailedException("Failed to add the log to the Data Base for Drones !", ex.getMessage());
        }


    }

//    This is implemented to demonstrate discharging drones battery.
//    @Scheduled(fixedDelayString = "300000")
    @Scheduled(fixedDelayString = "10000")
    public void reduceDroneBatteryLevelWithTime() {
        List<Drone> droneList = droneRepository.findAll();
        droneList.stream()
                .filter(drone -> drone.getBatteryPercentage() > 0)
                .forEach(drone -> droneRepository.updateBatteryLevel(drone.getDroneId(), drone.getBatteryPercentage() - 0.5F));
    }
}
