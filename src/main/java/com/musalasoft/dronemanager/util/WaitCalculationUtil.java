package com.musalasoft.dronemanager.util;

import com.musalasoft.dronemanager.model.entity.Drone;
import com.musalasoft.dronemanager.model.entity.Medication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WaitCalculationUtil {

    public float calculateLoadedMedicineWeight(List<Medication> medicineLoad){
        if (medicineLoad.isEmpty()){
            return 0.0F;
        }
        return medicineLoad.stream()
                .map(Medication::getWeight)
                .reduce(0.0F, Float::sum);
    }

    public float calculateAvailableMedicineWeight(Drone drone){
        float freeWeight = drone.getWeightLimit() - this.calculateLoadedMedicineWeight(drone.getMedications());
        if (freeWeight < 0){
            return 0.0F;
        }
        return freeWeight;
    }

    public boolean isLoadable(Drone drone){
        return drone.getWeightLimit() > this.calculateLoadedMedicineWeight(drone.getMedications());
    }

}
