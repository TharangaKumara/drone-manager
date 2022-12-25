package com.musalasoft.dronemanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DRONE_BATTERY_HISTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneBatteryHistory {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(name = "drone_id")
    private int droneId;

    @Column(name = "battery_percentage")
    private float batteryPercentage;

    @Column(name = "logged_date_time")
    private Date loggedTime;

}
