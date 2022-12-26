package com.musalasoft.dronemanager.model.entity;

import com.musalasoft.dronemanager.util.DroneModel;
import com.musalasoft.dronemanager.util.DroneState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "DRONE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_id")
    private int droneId;

    @Column(name = "serial_number")
    @NotBlank(message = "Serial Number Can not be null or empty !")
    @Size(max = 100, message = "Serial Number length can not exceed 100 character limit !")
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Drone Model Can not be null or empty !")
    private DroneModel model;

    @Column(name = "weight_limit")
    @NotNull(message = "Weight limit Can not be null or empty !")
    @Max(value = 500, message = "Weight limit can not exceed 500 gramme !")
    @Min(value = 0, message = "Weight limit should be positive value !")
    private float weightLimit;

    @Column(name = "battery_percentage")
    @NotNull(message = "Battery Percentage Can not be null or empty !")
    @Max(value = 100, message = "Battery Percentage should be less than 100 !")
    @Min(value = 0, message = "Battery Percentage should be greater than 0 !")
    private float batteryPercentage;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Drone State Can not be null or empty !")
    private DroneState droneState;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "DRONE_MEDICATION",
            joinColumns = {@JoinColumn(name = "drone_id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id")}
    )
    private List<Medication> medications;
}
