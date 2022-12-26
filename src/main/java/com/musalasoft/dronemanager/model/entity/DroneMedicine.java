package com.musalasoft.dronemanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DRONE_MEDICATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "drone_id")
    private int droneId;

    @Column(name = "medicine_id")
    private int medicineId;

    @Column(name = "loaded_date_time")
    private Date loadedDate;

    @Column(name = "unloaded_date_time")
    private Date unloadedDate;




}
